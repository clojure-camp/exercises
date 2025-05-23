{:title
 {:en-US "count-if"
  :pt-BR "conte se (count-if)"}
 :instructions
 {:en-US ["Write a function `count-if` that counts the number of items in a collection that pass a certain predicate."]
  :pt-BR ["Escreva uma função `count-if` que conta o número de itens em uma coleção que passam por um certo predicado."]}
 :difficulty :low
 :category :starter
 :function-template (defn count-if [pred? coll])
 :test-cases
 [{:input (count-if even? [1 2 3 4 5 6])
   :output 3}
  {:input (count-if keyword? ["foo" :bar (quote baz)])
   :output 1}
  {:input (count-if (fn [x]
                      (= x (reverse x)))
                    [[1 2 1] [1 2 3 4] [1]])
   :output 2}]
 :uses #{count filter}}

;; --- [:solution 0]

(defn count-if [pred? coll]
  (count (filter pred? coll)))
