{:title
 {:en-US "map indexed"
  :pt-BR "map indexado"}
 :category :learning-functions
 :difficulty :mid
 :instructions
 {:en-US ["Given a list, repeat each element in the list multiple times (the number of times being equal to the item's position in the list)."]
  :pt-BR ["Dada uma lista, repita cada elemento da lista várias vezes (o número de vezes sendo igual à posição do item na lista)."]}
 :function-template (defn increasing-repeat [coll])
 :test-cases [{:input (increasing-repeat [:a :b :c :d :e])
               :output [:a :b :b :c :c :c :d :d :d :d :e :e :e :e :e]} ]
 :teaches #{map-indexed}
 :uses #{repeat fn flatten}}

;; --- [:solution 0]

(defn increasing-repeat
  [coll]
  (flatten (map-indexed (fn [i v] (repeat (inc i) v)) coll)))
