{:title
 {:en-US "grouping things continued"
  :pt-BR "agrupando coisas - continuação"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Given a list of strings, categorize them by their starting character and within those groupings, categorize them by length."]
  :pt-BR ["Dada uma lista de strings, categorize-as pelo seu caractere inicial e dentro desses agrupamentos, categorize-as pelo tamanho."]}
 :function-template (defn categorize [strings])
 :test-cases [{:input (categorize
                        ["beep boop" "abc" "apple" "two things"
                         "toffee" "clojure camp" "so much data" "clojure"])
               :output
               {1 {3 ["abc"]
                   5 ["apple"]
                   6 ["toffee"]
                   7 ["clojure"]}
                2 {9 ["beep boop"]
                   10 ["two things"]
                   12 ["clojure camp"]}
                3 {12 ["so much data"]}}}]
 :teaches #{group-by}
 :uses #{map first into count string/split}
 :related #{"group-by"}}

;; --- [:solution 0]

(defn categorize
  [strings]
  (->> strings
       (group-by (fn [string]
                   (count (clojure.string/split string #" "))))
       (map (fn [[word-count grouped-strings]]
              [word-count (group-by count grouped-strings)]))
       (into {})))
