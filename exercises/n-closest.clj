{:title
 {:en-US "N closest"
  :pt-BR "N mais próximos"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write a function `n-closest` that takes a `haystack` (collection of numbers), a count `n`, and a `needle` (number)."
          "Return a collection of the `n` numbers from `haystack` that are closest in value to `needle`."
          "The result should be ordered from closest to furthest."]
  :pt-BR ["Escreva uma função `n-closest` que recebe um `haystack` (coleção de números), uma contagem `n`, e um `needle` (número)."
          "Retorne uma coleção dos `n` números do `haystack` que são mais próximos em valor ao `needle`."
          "O resultado deve ser ordenado do mais próximo ao mais distante."]}
 :function-template (defn n-closest [haystack n needle])
 :test-cases
 [{:input (n-closest [10 1 3 4 8] 1 7)
   :output [8]}
  {:input (n-closest [11 1 3 4 8] 2 7)
   :output [8 4]}
  {:input (n-closest [10 1 3 4 8] 3 1)
   :output [1 3 4]}]
 :uses #{sort-by take Math/abs}}

;; --- [:solution 0]

(defn n-closest [haystack n needle]
  (->> haystack
       (sort-by (fn [val] (Math/abs (- needle val))))
       (take n)))
