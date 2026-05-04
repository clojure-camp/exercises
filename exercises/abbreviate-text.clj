{:title
 {:en-US "abbreviate text"
  :pt-BR "abreviar texto"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Write a function `abbreviate` that truncates a string to at most `n` characters, but never breaks in the middle of a word."
          "Trailing whitespace should be trimmed from the result."]
  :pt-BR ["Escreva uma função `abbreviate` que trunca uma string para no máximo `n` caracteres, mas nunca quebra no meio de uma palavra."
          "Espaços em branco no final devem ser removidos do resultado."]}
 :function-template (defn abbreviate [s n])
 :test-cases
 [{:input (abbreviate "Hello World" 5)
   :output "Hello"}
  {:input (abbreviate "Hello World" 100)
   :output "Hello World"}
  {:input (abbreviate "One, two, three" 9)
   :output "One, two,"}
  {:input (abbreviate "One,      two, you're a shoe" 9)
   :output "One,"}
  {:input (abbreviate "One,      two, you're a shoe" 14)
   :output "One,      two,"}
  {:input (abbreviate "word1 word2 word3" 12)
   :output "word1 word2"}]
 :uses #{clojure.string/split clojure.string/join clojure.string/trim reduce reduced subs count}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn abbreviate [s n]
  (cond
    (<= (count s) n)
    s

    (or (= \space (nth s n))
        (= \space (nth s (dec n))))
    (string/trim (subs s 0 n))

    :else
    (recur s (dec n))))
