{:title
 {:en-US "summing a list (naive recursion)"
  :pt-BR "somando uma lista (recursão ingênua)"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function to sum a list of numbers, using recursion."]
  :pt-BR ["Escreva uma função para somar uma lista de números, usando recursão."]}
 :related #{"summing-a-list-reduce"
            "summing-a-list-apply"
            "summing-a-list-atom"
            "summing-a-list-safe-recursion"}
 :function-template (defn sum [values])
 :test-cases
[{:input (sum [1 2 3 4])
  :output 10}
 {:input (sum [])
  :output 0}
 {:input (sum [1 -1 1 -1 1 -1 0])
  :output 0}]
 :uses #{:recursion first rest empty? :math-operations if +}}

;; --- [:solution 0]

(defn sum [values]
  (if (empty? values)
    0
    (+ (first values) (sum (rest values)))))
