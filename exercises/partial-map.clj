{:title
 {:en-US "partial-map"
  :pt-BR "mapeamento parcial"}
 :difficulty :low
 :instructions
 {:en-US ["Write a function that takes two functions and a list."
          "Transform the given list by applying the first function to all the values in the list that return true for the second function; other values should remain as is."]
  :pt-BR ["Escreva uma função que receba duas funções e uma lista."
          "Transforme a lista dada aplicando a primeira função a todos os valores na lista que retornam verdadeiro para a segunda função; outros valores devem permanecer como estão."]}
 :function-template (defn partial-map [f pred? values])
 :test-cases
[{:input (partial-map (fn [x]
                        (* x 10))
                      even?
                      [1 2 3 4 5 6])
  :output [1 20 3 40 5 60]}]
 :teaches #{map}
 :uses #{if fn :higher-order-functions}
 :category :learning-functions}

;; --- [:solution 0]

(defn partial-map [f pred? values]
  (map (fn [x]
         (if (pred? x)
           (f x)
           x))
       values))
