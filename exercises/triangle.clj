{:title
 {:en-US "triangle?"
  :pt-BR "triângulo?"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function to determine if some 3 side lengths are sufficient to make a triangle."
          "To check if 3 sides make a triangle, you need to check that every side is less than or equal to the sum of the other two sides."]
  :pt-BR ["Escreva uma função para determinar se 3 comprimentos de lados são suficientes para formar um triângulo."
          "Para verificar se 3 lados formam um triângulo, você precisa checar se cada lado é menor ou igual à soma dos outros dois lados."]}
 :related #{"classify-triangle"}
 :function-template (defn triangle? [a b c])
 :test-cases [{:input (triangle? 3 4 5)
               :output true}
              {:input (triangle? 1 1 2)
               :output true}
              {:input (triangle? 3 1 1)
               :output false}]
 :uses #{and :math-operations :math-comparisons + <}}

;; --- [:solution 0]

(defn triangle? [a b c]
  (and
    (<= a (+ b c))
    (<= b (+ a c))
    (<= c (+ a b))))
