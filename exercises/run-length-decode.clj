{:title
 {:en-US "run length decode"
  :pt-BR "decodificação de run-length"}
 :difficulty :low
 :category :learning-functions
 :related #{"run-length-encode"}
 :instructions
 {:en-US ["Run-length-encoding is a form of lossless data compression. For example, the array `[:a :a :b :b :b :c :a :a :d :d]` could be represented as: `[[:a 2] [:b 3] [:c 1] [:a 2] [:d 2]]`."
          "Write a function to decode a run-length encoded list."]
  :pt-BR ["Codificação run-length é uma forma de compressão de dados sem perda. Por exemplo, o array `[:a :a :b :b :b :c :a :a :d :d]` poderia ser representado como: `[[:a 2] [:b 3] [:c 1] [:a 2] [:d 2]]`."
          "Escreva uma função para decodificar uma lista codificada em run-length."]}
 :function-template (defn run-length-decode [runs])
 :test-cases [{:input (run-length-decode
                        [[:a 3] [:b 2] [:c 1] [:d 4]])
               :output [:a :a :a :b :b :c :d :d :d :d]}]
 :teaches #{mapcat repeat}
 :uses #{:destructuring}
 :source "https://exercism.io/tracks/clojure/exercises/run-length-encoding"}

;; --- [:solution 0]

(defn run-length-decode
  [runs]
  (mapcat (fn [[val len]] (repeat len val)) runs))
