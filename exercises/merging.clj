{:teaches #{merge}
 :title
 {:en-US "merging maps"
  :pt-BR "combinando mapas"}
 :uses #{:maps map}
 :category :learning-functions
 :difficulty :mid
 :instructions
 {:en-US ["Write a function that takes two lists of maps and combines the corresponding pairs."]
  :pt-BR ["Escreva uma função que recebe duas listas de mapas e combina os pares correspondentes."]}
 :function-template (defn join-maps [col1 col2])
 :test-cases
[{:input (join-maps [{:a 1} {:a 2} {:a 3}] [{:b 2} {:b 3} {:b 4}])
  :output [{:a 1
            :b 2}
           {:a 2
            :b 3}
           {:a 3
            :b 4}]}]}

;; --- [:solution 0]

(defn join-maps
  [col1 col2]
  (map merge col1 col2))
