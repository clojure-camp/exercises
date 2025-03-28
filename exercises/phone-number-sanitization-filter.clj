{:title
 {:en-US "phone number sanitization (filter)"
  :pt-BR "sanitização de número de telefone (filter)"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Implement a function to clean-up phone numbers so that they can be stored in a standardized way: a string of 10 digits."
          "Make use of `filter`."]
  :pt-BR ["Implemente uma função para limpar números de telefone de modo que eles possam ser armazenados de forma padronizada: uma string de 10 dígitos."
          "Faça uso de `filter`."]}
 :related #{"phone-number-sanitization"}
 :function-template (defn sanitize [phone-number])
 :test-cases [{:input (sanitize "+1 (613)-995-0253")
               :output "6139950253"}
              {:input (sanitize "613-995-0253")
               :output "6139950253"}
              {:input (sanitize "1 613 995 0253")
               :output "6139950253"}
              {:input (sanitize "613.995.0253")
               :output "6139950253"}]
 :teaches #{filter}
 :uses #{take-last}
 :source "https://exercism.io/tracks/clojure/exercises/phone-number"}

;; --- [:solution 0]

(defn sanitize [phone-number]
  (->> phone-number
       (filter #{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9})
       (take-last 10)
       (apply str)))
