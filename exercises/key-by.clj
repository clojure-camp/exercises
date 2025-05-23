{:title
 {:en-US "key-by"
  :pt-BR "agrupa por chave (key-by)"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Implement `key-by`, a function that given a a list of items, converts it to a map, with each item keyed by a given function"]
  :pt-BR ["Implemente `key-by`, uma função que, dada uma lista de itens, a converte em um mapa, com cada item indexado pela função dada"]}
 :function-template (defn key-by [f values])
 :test-cases
[{:input (key-by :id
                 [{:id 123
                   :name "Alice"}
                  {:id 456
                   :name "Bob"}])
  :output {123 {:id 123
                :name "Alice"}
           456 {:id 456
                :name "Bob"}}}
 {:input (key-by hash ["Alice" "Bob"])
  :output {-213842911 "Alice"
           665036798 "Bob"}}]
 :uses #{reduce assoc :higher-order-functions}}

;; --- [:solution 0]

(defn key-by [f values]
  (reduce (fn [memo x]
            (assoc memo (f x) x)) {} values))
