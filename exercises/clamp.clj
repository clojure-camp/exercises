{:title
 {:en-US "clamp"
  :pt-BR "restringir (clamp)"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function `clamp` to restrict a value to a given range."]
  :pt-BR ["Escreva uma função `clamp` para restringir um valor a um intervalo dado."]}
 :function-template (defn clamp [x min max])
 :test-cases [{:input (clamp 2 1 4)
               :output 2}
              {:input (clamp 0 1 4)
               :output 1}
              {:input (clamp 5 1 4)
               :output 4}]
 :uses #{cond :math-comparisons <= <}}

;; --- [:solution 0]

(defn clamp [x min max]
  (cond
    (<= min x max) x
    (< x min) min
    :else max))
