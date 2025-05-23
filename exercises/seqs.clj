{:title
 {:en-US "sequences"
  :pt-BR "sequências (sequences)"}
 :instructions
 {:en-US ["Write a function that takes some sequence and a value and adds that value to the sequence, regardless of what type it is."]
  :pt-BR ["Escreva uma função que recebe uma sequência e um valor e adiciona esse valor à sequência, independentemente do tipo dela."]}
 :function-template (defn add-element [x col])
 :test-cases
[{:input (add-element :a (list 2 3 4))
  :output (quote (:a 2 3 4))}
 {:input (add-element :a [2 3 4])
  :output [2 3 4 :a]}
 {:input (add-element :a #{4 3 2})
  :output #{2 3 4 :a}}]
:difficulty :low
 :category :learning-functions
 :teaches #{conj}
 :uses #{:lists :vectors :sets}}

;; --- [:solution 0]

(defn add-element
  [x col]
  (conj col x))
