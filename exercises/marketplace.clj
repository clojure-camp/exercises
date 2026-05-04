{:title
 {:en-US "marketplace unit conversion"
  :pt-BR "conversão de unidades no mercado"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["A marketplace allows trading between different commodities. The exchange rates are stored in a nested map:"
          [:code "(def lookup
  {:bananas {:eagles [4 2]    ;; 4 bananas trade for 2 eagles
             :apples [1 2]}   ;; 1 banana trades for 2 apples
   :apples  {:zebras  [2 4]   ;; 2 apples trade for 4 zebras
             :monkeys [1 5]}  ;; 1 apple trades for 5 monkeys
   :eagles  {}
   :zebras  {}
   :turtles {}
   :monkeys {:turtles [2 4]}})"]  ;; 2 monkeys trade for 4 turtles
          "Write two functions:"
          "1. `find-path` — given a starting commodity and a target commodity, returns a vector of commodity keywords representing a path from start to target through the marketplace."
          "2. `convert` — given a quantity and source commodity as `[n :unit]`, and a target commodity, returns `[quantity :target]` with the converted quantity (as a double)."]
  :pt-BR ["Um mercado permite trocar entre diferentes commodities. As taxas de câmbio são armazenadas em um mapa aninhado:"
          [:code "(def lookup
  {:bananas {:eagles [4 2]    ;; 4 bananas trocam por 2 eagles
             :apples [1 2]}   ;; 1 banana troca por 2 maçãs
   :apples  {:zebras  [2 4]   ;; 2 maçãs trocam por 4 zebras
             :monkeys [1 5]}  ;; 1 maçã troca por 5 macacos
   :eagles  {}
   :zebras  {}
   :turtles {}
   :monkeys {:turtles [2 4]}})"]  ;; 2 macacos trocam por 4 tartarugas
          "Escreva duas funções:"
          "1. `find-path` — dado uma commodity inicial e uma commodity alvo, retorna um vetor de keywords de commodities representando um caminho do início ao alvo pelo mercado."
          "2. `convert` — dado uma quantidade e commodity fonte como `[n :unit]`, e uma commodity alvo, retorna `[quantidade :alvo]` com a quantidade convertida (como double)."]}
 :function-template [(defn find-path [lookup start target])
                     (defn convert [lookup [quantity source] target])]
 :test-cases
 [{:input (find-path {:bananas {:eagles [4 2] :apples [1 2]}
                      :apples {:zebras [2 4] :monkeys [1 5]}
                      :eagles {} :zebras {} :turtles {}
                      :monkeys {:turtles [2 4]}}
                     :bananas :eagles)
   :output [:bananas :eagles]}
  {:input (find-path {:bananas {:eagles [4 2] :apples [1 2]}
                      :apples {:zebras [2 4] :monkeys [1 5]}
                      :eagles {} :zebras {} :turtles {}
                      :monkeys {:turtles [2 4]}}
                     :bananas :zebras)
   :output [:bananas :apples :zebras]}
  {:input (convert {:bananas {:eagles [4 2] :apples [1 2]}
                    :apples {:zebras [2 4] :monkeys [1 5]}
                    :eagles {} :zebras {} :turtles {}
                    :monkeys {:turtles [2 4]}}
                   [4 :bananas] :eagles)
   :output [2.0 :eagles]}
  {:input (convert {:bananas {:eagles [4 2] :apples [1 2]}
                    :apples {:zebras [2 4] :monkeys [1 5]}
                    :eagles {} :zebras {} :turtles {}
                    :monkeys {:turtles [2 4]}}
                   [2 :apples] :zebras)
   :output [4.0 :zebras]}]
 :uses #{:recursion reduce map keys remove first ->>}}

;; --- [:solution 0]

(defn find-path [lookup start target]
  (if (= start target)
    [start]
    (->> (keys (get lookup start))
         (map (fn [next-unit]
                (when-let [rest-of-path (find-path lookup next-unit target)]
                  (into [start] rest-of-path))))
         (remove nil?)
         first)))

(defn convert [lookup [quantity source] target]
  (let [path (find-path lookup source target)
        final-qty
        (reduce (fn [qty [from to]]
                  (let [[from-amount to-amount] (get-in lookup [from to])]
                    (* qty (double (/ to-amount from-amount)))))
                quantity
                (partition 2 1 path))]
    [final-qty target]))
