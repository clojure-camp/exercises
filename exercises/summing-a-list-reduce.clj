{:title
 {:en-US "summing a list (reduce)"
  :pt-BR "somando uma lista (reduce)"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Write a function to sum a list of numbers, using `reduce`."]
  :pt-BR ["Escreva uma função para somar uma lista de números, usando `reduce`."]}
 :related #{"summing-a-list-apply"
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
 :teaches #{reduce}
 :uses #{:math-operations +}}

;; --- [:solution 0]

(defn sum [values]
  (reduce + values))
