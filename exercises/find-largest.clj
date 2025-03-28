{:title
 {:en-US "find largest"
  :pt-BR "encontrar o maior"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Write a function `largest` to find the largest number in a list, making use of `max` and either `apply` or `reduce`."
          "Note that `max` takes a variable number of arguments, but does not work with arrays, ie. `(max 1 2 3)` works as expected, but `(max [1 2 3])` does not."]
  :pt-BR ["Escreva uma função `largest` para achar o maior número em uma lista, fazendo uso de `max` e `apply` ou `reduce`."
          "Note que `max` aceita um número variável de argumentos, mas não funciona com arrays, ou seja, `(max 1 2 3)` funciona como esperado, mas `(max [1 2 3])` não."]}
 :function-template (defn largest [nums])
 :test-cases
[{:input (largest [1 2 -1 3 3 2 1 0])
  :output 3}]
 :uses #{max}
 :teaches #{apply reduce}}

;; --- [:solution 0]

(defn largest
  [nums]
  (apply max nums))

(defn largest-reduce
  [nums]
  (reduce max nums))
