{:title
 {:en-US "word count"
  :pt-BR "contagem de palavras"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Given a phrase, count the occurrences of each word in that phrase. Ignore punctuation and case."]
  :pt-BR ["Dada uma frase, conte as ocorrências de cada palavra nessa frase. Ignore pontuação e maiúscula/minúscula."]}
 :function-template (defn word-count [text])
 :test-cases
[{:input (word-count "one fish two fish red fish blue fish")
  :output {"one" 1
           "fish" 4
           "two" 1
           "red" 1
           "blue" 1}}
 {:input (word-count "Foo! Bar, baz 2.")
  :output {"foo" 1
           "bar" 1
           "baz" 1
           "2" 1}}]
 :uses #{clojure.string/lower-case re-seq frequencies}
 :source "https://exercism.io/tracks/clojure/exercises/word-count"}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn word-count [text]
  (->> text
       clojure.string/lower-case
       (re-seq #"\w+")
       frequencies))
