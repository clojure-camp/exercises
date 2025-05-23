{:title
 {:en-US "summing a list (atom)"
  :pt-BR "somando uma lista (atom)"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Write a function to sum a list of numbers, using an `atom` and `doseq`."]
  :pt-BR ["Escreva uma função para somar uma lista de números, usando um `atom` e `doseq`."]}
 :related #{"summing-a-list-reduce"
            "summing-a-list-apply"
            "summing-a-list-naive-recursion"
            "summing-a-list-safe-recursion"}
 :function-template (defn sum [numbers])
 :test-cases [{:input (sum [1 2 3 4])
               :output 10}
              {:input (sum [])
               :output 0}
              {:input (sum [1 -1 1 -1 1 -1 0])
               :output 0}]
 :teaches #{atom doseq "@" deref swap! :working-with-atoms}
 :uses #{let :math-operations +}}

;; --- [:solution 0]

(defn sum [values]
  (let [total (atom 0)]
    (doseq [v values]
      (swap! total + v))
    @total))
