{:title
 {:en-US "sum of range (closed form)"
  :pt-BR "soma de um intervalo (fórmula fechada)"}
 :category :starter
 :difficulty :low
 :related #{"sum-of-range"}
 :instructions
 {:en-US ["Write a function that, given a number N, returns the sum of numbers from 1 to N, inclusive."
          "Note, this can be done with a formula: https://en.wikipedia.org/wiki/Arithmetic_progression"]
  :pt-BR ["Escreva uma função que, dado um número N, retorna a soma dos números de 1 a N, inclusive."
          "Observação, isso pode ser feito com uma fórmula: https://pt.wikipedia.org/wiki/Progress%C3%A3o_aritm%C3%A9tica"]}
 :function-template (defn sum-to [n])
 :test-cases
[{:input (sum-to 5)
  :output 15}]
 :uses #{+ / *}}

;; --- [:solution 0]

(defn sum-to [n]
  (/ (* n (+ n 1))
     2))
