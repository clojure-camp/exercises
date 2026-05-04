{:title
 {:en-US "make unique generator"
  :pt-BR "criar gerador de valores únicos"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write a function `make-unique-generator` that takes an initial set of \"already seen\" values `init-set` and a zero-argument function `f`."
          "It returns a new zero-argument function that, each time it is called, invokes `f` until it produces a value not seen before, records that value, and returns it."
          "If no unique value can be found after 10,000 attempts, throw an exception with the message `\"too many attempts\"`."]
  :pt-BR ["Escreva uma função `make-unique-generator` que recebe um conjunto inicial de valores \"já vistos\" `init-set` e uma função de zero argumentos `f`."
          "Ela retorna uma nova função de zero argumentos que, a cada chamada, invoca `f` até produzir um valor ainda não visto, registra esse valor e o retorna."
          "Se nenhum valor único puder ser encontrado após 10.000 tentativas, lance uma exceção com a mensagem `\"too many attempts\"`."]}
 :function-template (defn make-unique-generator [init-set f])
 :test-cases
 [{:input (let [gen (make-unique-generator #{} (fn [] (rand-nth [1 2 3 4 5])))]
            (count (distinct (repeatedly 5 gen))))
   :output 5}
  {:input (let [gen (make-unique-generator #{1 2} (fn [] (rand-nth [1 2 3])))]
            (gen))
   :output 3}
  {:input (try
            (let [gen (make-unique-generator #{1 2 3} (fn [] (rand-nth [1 2 3])))]
              (gen)
              false)
            (catch Exception e
              (= "too many attempts" (ex-message e))))
   :output true}]
 :uses #{atom swap! contains? loop recur ex-info}}

;; --- [:solution 0]

(defn make-unique-generator [init-set f]
  (let [seen (atom init-set)]
    (fn []
      (loop [attempts 0]
        (if (>= attempts 10000)
          (throw (ex-info "too many attempts" {}))
          (let [v (f)]
            (if (contains? @seen v)
              (recur (inc attempts))
              (do (swap! seen conj v)
                  v))))))))
