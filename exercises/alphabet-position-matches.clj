{:title
 {:en-US "alphabet position matches"
  :pt-BR "correspondências de posição no alfabeto"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function `alphabet-position-matches` that takes a word (string) and counts how many of its characters are at the same position as in the alphabet."
          "Comparison is case-insensitive."
          "For example: `\"abode\"` → 4, because `a` is at position 0 (matching `a`), `b` is at position 1 (matching `b`), `o` is at position 2 (but `c` is there, no match), `d` is at position 3 (matching `d`), `e` is at position 4 (matching `e`)."]
  :pt-BR ["Escreva uma função `alphabet-position-matches` que recebe uma palavra (string) e conta quantos de seus caracteres estão na mesma posição que no alfabeto."
          "A comparação é insensível a maiúsculas/minúsculas."
          "Por exemplo: `\"abode\"` → 4, porque `a` está na posição 0 (corresponde a `a`), `b` está na posição 1 (corresponde a `b`), `o` está na posição 2 (mas `c` está lá, sem correspondência), `d` está na posição 3 (corresponde a `d`), `e` está na posição 4 (corresponde a `e`)."]}
 :function-template (defn alphabet-position-matches [word])
 :test-cases
 [{:input (alphabet-position-matches "abode")
   :output 4}
  {:input (alphabet-position-matches "ABC")
   :output 3}
  {:input (alphabet-position-matches "xyz")
   :output 0}
  {:input (alphabet-position-matches "aXc")
   :output 2}
  {:input (alphabet-position-matches "")
   :output 0}]
 :uses #{map clojure.string/lower-case filter count =}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn alphabet-position-matches [word]
  (->> (map = alphabet (string/lower-case word))
       (filter true?)
       count))
