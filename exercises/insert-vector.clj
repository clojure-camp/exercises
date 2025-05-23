{:title
 {:en-US "insert in vector"
  :pt-BR "inserção em vetor"}
 :difficulty :mid
 :category :learning-functions
 :related #{"dissoc-at"}
 :instructions
 {:en-US ["Write a function that inserts a value into a vector at a given index (moving all subsequent values)."]
  :pt-BR ["Escreva uma função que insere um valor em um vetor em um índice dado (movendo todos os valores subsequentes)."]}
 :function-template (defn insert-at [v index value])
 :test-cases
[{:input (insert-at [1 3 4] 1 2)
  :output [1 2 3 4]}]
 :uses #{into}
 :teaches #{conj subvec}}

;; --- [:solution 0]

(defn insert-at
  [v index value]
  (into
   (conj (subvec v 0 index) value)
   (subvec v index)))
