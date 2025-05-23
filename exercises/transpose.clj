{:title
 {:en-US "2d transpose"
  :pt-BR "Transposição 2d"}
 :difficulty :low
 :instructions
 {:en-US ["Write a function that takes a matrix as a vector of vectors and inverts it."]
  :pt-BR ["Escreva uma função que recebe uma matriz como um vetor de vetores e a inverte."]}
 :function-template (defn transpose [matrix])
 :test-cases
[{:input (transpose [[1 4] [2 5] [3 6]])
  :output [[1 2 3] [4 5 6]]}]
:category :starter
 :uses #{apply mapv vector}}

;; --- [:solution 0]

(defn transpose
  [matrix]
  (apply mapv vector matrix))
