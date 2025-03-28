{:title
 {:en-US "phone number sanitization"
  :pt-BR "sanitização de número de telefone"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Implement a function to clean up phone numbers so that they can be stored in a standarized way: a string of 10 digits."
          "Make use of `clojure.string/replace`."]
  :pt-BR ["Implemente uma função para limpar números de telefone de modo que eles possam ser armazenados de forma padronizada: uma string de 10 dígitos."
          "Faça uso de `clojure.string/replace`."]}
 :related #{"phone-number-sanitization-filter"}
 :function-template (defn sanitize [phone-number])
 :test-cases
[{:input (sanitize "+1 (613)-995-0253")
  :output "6139950253"}
 {:input (sanitize "613-995-0253")
  :output "6139950253"}
 {:input (sanitize "1 613 995 0253")
  :output "6139950253"}
 {:input (sanitize "613.995.0253")
  :output "6139950253"}]
 :teaches #{clojure.string/replace}
 :uses #{->> take-last apply str}
 :source "https://exercism.io/tracks/clojure/exercises/phone-number"}

;; --- [:solution 0]
(require '[clojure.string :as string])

(defn sanitize [phone-number]
  (->> (string/replace phone-number #"[^0-9]" "")
       (take-last 10)
       (apply str)))
