{:title
 {:en-US "regex searching"
  :pt-BR "busca com regex"}
 :difficulty :mid
 :category :learning-functions
 :instructions
 {:en-US ["Given a string of text, find all the substrings inside of it that are phone numbers."]
  :pt-BR ["Dada uma string de texto, encontre todas as substrings dentro dela que são números de telefone."]}
 :function-template (defn find-phone-numbers [text])
 :test-cases [{:input (find-phone-numbers "Here's a bunch of text call (416) 333 4444 and +1 647 123 4578 gurf 1- 416)123-4567 some more garbage 123_123_4567 but don't call from 1-800-888-9999 and why not? here's 905 777-1111")
               :output ["(416) 333 4444"
                        "+1 647 123 4578"
                        "1-800-888-9999"
                        "905 777-1111"]}]
 :teaches #{re-seq}
 :uses #{}
 :related #{"regex-extraction"}}

;; --- [:solution 0]

(defn find-phone-numbers
  [text]
  (->> text
       (re-seq #"(?:\+?\d[- ])?(?:\(\d{3}\)|\d{3})[- ]\d{3}[- ]\d{4}")))
