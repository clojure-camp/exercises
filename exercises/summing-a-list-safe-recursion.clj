{:title
 {:en-US "summing a list (safe recursion)"
  :pt-BR "somando uma lista (recursão segura)"}
 :difficulty :mid
 :category :learning-functions
 :instructions
 {:en-US ["Write a function to sum a list of numbers. Use recursion, but in a way that doesn't blow the stack."]
  :pt-BR ["Escreva uma função para somar uma lista de números. Use recursão, mas de uma forma que não estoure a pilha."]}
 :related #{"summing-a-list-reduce"
            "summing-a-list-apply"
            "summing-a-list-atom"
            "summing-a-list-naive-recursion"}
 :function-template (defn sum [values])
 :test-cases [{:input (sum [1 2 3 4]) :output 10}
              {:input (sum []) :output 0}
              {:input (sum [1 -1 1 -1 1 -1 0]) :output 0}]
 :teaches #{recur}
 :uses #{:recursion first rest empty? :math-operations if +}}

;; --- [:solution 0]

(defn sum
  ([values] (sum values 0))
  ([values accumulator]
   (if (empty? values)
     accumulator
     (recur (rest values) (+ (first values) accumulator)))))
