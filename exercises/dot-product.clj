{:title
 {:en-US "dot products"
  :pt-BR "produtos escalares"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function to compute the dot product of two vectors."]
  :pt-BR ["Escreva uma função para calcular o produto escalar de dois vetores."]}
 :function-template (defn dot [v1 v2])
 :test-cases [{:input (dot [1 0] [0 1])
               :output 0}
              {:input (dot [1 1 1] [1 1 1])
               :output 3}
              {:input (dot [1 2 3] [4 5 6])
               :output 32}]
 :uses #{->> map reduce + *}}

;; --- [:solution 0]

(defn dot
  [v1 v2]
  (->> (map * v1 v2)
       (reduce + 0)))
