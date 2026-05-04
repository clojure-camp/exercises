{:title
 {:en-US "merge sorted lists"
  :pt-BR "mesclar listas ordenadas"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Write a function `merge-lists` that takes two sorted lists and returns a single sorted list containing all elements from both."
          "You do not have access to a sort function."
          "You should be able to do this in O(n)."
          "This is the key subroutine of merge sort."]
  :pt-BR ["Escreva uma função `merge-lists` que recebe duas listas ordenadas e retorna uma única lista ordenada contendo todos os elementos de ambas."
          "Esta é a subrotina principal do merge sort."]}
 :function-template (defn merge-lists [list-a list-b])
 :test-cases
 [{:input (merge-lists [1 2 5 6 9] [3 7])
   :output [1 2 3 5 6 7 9]}
  {:input (merge-lists [] [1 2 3])
   :output [1 2 3]}
  {:input (merge-lists [1 2 3] [])
   :output [1 2 3]}
  {:input (merge-lists [1 3 5] [2 4 6])
   :output [1 2 3 4 5 6]}
  {:input (merge-lists [1 1 2] [1 3])
   :output [1 1 1 2 3]}]
 :uses #{loop recur cond concat conj first rest empty?}
 :related #{"merge-sort-1"}}

;; --- [:solution 0]

(defn merge-lists [list-a list-b]
  (loop [acc []
         a list-a
         b list-b]
    (cond
      (empty? a) (into acc b)
      (empty? b) (into acc a)
      (< (first a) (first b))
      (recur (conj acc (first a)) (rest a) b)
      :else
      (recur (conj acc (first b)) a (rest b)))))
