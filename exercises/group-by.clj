{:title
 {:en-US "grouping things"
  :pt-BR "agrupando coisas"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Given a list of strings, categorize them by the number of characters."]
  :pt-BR ["Dada uma lista de strings, categorize-as pelo número de caracteres."]}
 :function-template (defn categorize [strings])
 :test-cases [{:input
               (categorize ["beep" "abc" "apple" "things"
                            "toffee" "camp" "data" "clojure"])
               :output
               {4 ["beep" "camp" "data"]
                3 ["abc"]
                5 ["apple"]
                6 ["things" "toffee"]
                7 ["clojure"]}}]
 :teaches #{group-by}
 :uses #{count}
 :related #{"grouping-and-counting" "grouping-part2"}}

;; --- [:solution 0]

(defn categorize [strings]
  (group-by count strings))
