{:title
 {:en-US "run-length encoding"
  :pt-BR "codificação run-length"}
 :difficulty :mid
 :category :synthesis
 :related #{"run-length-decode"
            "run-length-encode-reduce"}
 :instructions
 {:en-US ["Run-length-encoding is a form of lossless data compression. For example, the array `[:a :a :b :b :b :c :a :a :d :d]` could be represented as: `[[:a 2] [:b 3] [:c 1] [:a 2] [:d 2]]`."
          "Write a function that does a run-length encoding of a given sequence."]
  :pt-BR ["Codificação run-length é uma forma de compressão de dados sem perda. Por exemplo, o array `[:a :a :b :b :b :c :a :a :d :d]` poderia ser representado como: `[[:a 2] [:b 3] [:c 1] [:a 2] [:d 2]]`."
          "Escreva uma função que faz a codificação run-length dada uma sequência."]}
 :function-template (defn run-length [coll])
 :test-cases
[{:input (run-length [:a :a :b :c :c :c])
  :output [[:a 2] [:b 1] [:c 3]]}]
 :teaches #{partition-by}
 :uses #{identity map juxt first count}
 :source "https://exercism.io/tracks/clojure/exercises/run-length-encoding"}

(defn run-length [coll]
  (->> (partition-by identity coll)
       (map (juxt first count))))
