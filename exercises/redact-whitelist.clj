{:title
 {:en-US "redact"
  :pt-BR "redigir"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write a function `redact`, which, given some text and an allow-list of words, returns the text, with non-allow-listed words replaced with ████ (being as long as the word being replaced)."
          "How much work does it take to make this function use a deny-list instead?"]
  :pt-BR ["Escreva uma função `redact`, que, dado um texto e uma lista de palavras permitidas (allow-list), retorna o texto, com as palavras não permitidas substituídas por ████ (tendo o mesmo tamanho da palavra substituída)."
          "Quanto trabalho seria necessário para fazer essa função usar uma lista de palavras proibidas (deny-list) ao invés de permitidas?"]}
 :function-template (defn redact [text allow-list])
 :test-cases
[{:input (redact "hello world foo" ["hello" "world"])
  :output "hello world ███"}]
:uses #{->> clojure.string/split repeat count set map clojure.string/join}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn redact-word [word]
  (apply str (repeat (count word) "█")))

(defn redact [text allow-list]
  (let [allow-list? (set allow-list)]
    (->> (string/split text #" ")
         (map (fn [word]
                (if (allow-list? word)
                  word
                  (redact-word word))) )
         (string/join " "))))
