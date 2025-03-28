{:title
 {:en-US "run-length encoding (with reduce)"
  :pt-BR "codificação run-length (com reduce)"}
 :difficulty :mid
 :category :synthesis
 :related #{"run-length-decode"}
 :instructions
 {:en-US ["As before, write a function that does a run-length encoding of a given sequence, but this time, use `reduce`."]
  :pt-BR ["Como antes, escreva uma função que faz a codificação run-length dada uma sequência, mas desta vez, use `reduce`."]}
 :function-template (defn run-length [xs])
 :test-cases
[{:input (run-length [:a :a :b :c :c :c])
  :output [[:a 2] [:b 1] [:c 3]]}]
 :teaches #{reduce}
 :uses #{conj vec butlast inc peek}
 :source "https://exercism.io/tracks/clojure/exercises/run-length-encoding"}

;; --- [:solution 0]

(defn run-length [xs]
  (reduce (fn [vs x]
            (let [[v c] (peek vs)]
              (if (= v x)
                (conj (vec (butlast vs)) [v (inc c)])
                (conj vs [x 1]))))
          []
          xs))
