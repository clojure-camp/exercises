{:title
 {:en-US "fizzbuzz"
  :pt-BR "fizzbuzz"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Implement `fizzbuzz`, which prints the numbers from 1 to 100, replacing multiples of 3 with \"fizz\", multiples of 5 with \"buzz\" and multiples of 3 and 5 with \"fizzbuzz\"."]
  :pt-BR ["Implemente `fizzbuzz`, que imprime os números de 1 a 100, substituindo múltiplos de 3 por \"fizz\", múltiplos de 5 por \"buzz\" e múltiplos de 3 e 5 por \"fizzbuzz\"."]}
 :uses #{mod println doseq range =}}

;; --- [:solution 0]

(defn div? [n factor]
  (= 0 (mod n factor)))

(defn fizzbuzz []
  (doseq [n (range 1 101)]
    (println (cond
               (div? n (* 3 5)) "fizzbuzz"
               (div? n 3) "fizz"
               (div? n 5) "buzz"
               :else n))))
