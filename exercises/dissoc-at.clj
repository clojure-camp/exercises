{:title
 {:en-US "remove from vector"
  :pt-BR "remoção de vetor"}
 :category :learning-functions
 :difficulty :mid
 :related #{"insert-vector"}
 :instructions
 {:en-US ["Write a function that removes a value at a given index from a vector, moving everything else up."]
  :pt-BR ["Escreva uma função que remove um valor em um índice dado de um vetor, movendo todos os demais."]}
 :function-template (defn dissoc-at [v index])
 :test-cases
 [{:input (dissoc-at [1 2 3 4] 1)
   :output [1 3 4]}]
 :uses #{into inc}
 :teaches #{subvec}}

; --- [:solution 0]

(defn dissoc-at
  [v index]
  (into
   (subvec v 0 index)
   (subvec v (inc index))))
