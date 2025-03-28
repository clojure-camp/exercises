{:title
 {:en-US "grouping and counting"
  :pt-BR "agrupando e contando"}
 :related #{"group-by"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Given a list of strings, count how many words have each length."]
  :pt-BR ["Dada uma lista de strings, conte quantas palavras têm de cada tamanho."]}
 :function-template (defn count-by-length [strings])
 :test-cases
[{:input
  (count-by-length
    ["beep" "abc" "apple" "things"
     "toffee" "camp" "data" "clojure"])
  :output {6 2
           7 1
           3 1
           5 1
           4 3}}]
:teaches #{}
 :uses #{map group-by count frequencies into}}

;; --- [:solution 0]

(defn count-by-length [strings]
  (->> (group-by count strings)
       (map (fn [[k v]]
              [k (count v)]))
       (into {})))

(defn count-by-length-alternate [strings]
  (->> (map count strings)
       (frequencies)))
