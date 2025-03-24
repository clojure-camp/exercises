{:title
 {:en-US "summing a list (apply)"
  :pt-BR "somando uma lista (apply)"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Write a function to sum a list of numbers, using `apply`."]
  :pt-BR ["Escreva uma função para somar uma lista de números, usando `apply`."]}
 :related #{"summing-a-list-reduce"
            "summing-a-list-atom"
            "summing-a-list-naive-recursion"
            "summing-a-list-safe-recursion"}
 :function-template (defn sum [values])
 :test-cases
[{:input (sum [1 2 3 4])
  :output 10}
 {:input (sum [])
  :output 0}
 {:input (sum [1 -1 1 -1 1 -1 0])
  :output 0}]
 :teaches #{apply}
 :uses #{:math-operations +}}

;; --- [:solution 0]

(defn sum [values]
  (apply + values))
