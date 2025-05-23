{:title
 {:en-US "sum of range"
  :pt-BR "soma de um intervalo"}
 :category :starter
 :difficulty :low
 :related #{"sum-of-range-closed-form"}
 :instructions
 {:en-US ["Write a function that, given a number N, returns the sum of numbers from 1 to N, inclusive."
          "Use sequences"]
  :pt-BR ["Escreva uma função que, dado um número N, retorna a soma dos números de 1 a N, inclusive."
          "Use sequências (sequences)"]}
 :function-template (defn sum-to [n])
 :test-cases
[{:input (sum-to 5)
  :output 15}]
 :uses #{range + apply reduce inc}}

;; --- [:solution 0]

(defn sum-to [n]
  (apply + (range (inc n))))
