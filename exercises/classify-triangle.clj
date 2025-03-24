{:title
 {:en-US "classify triangle"
  :pt-BR "classificar triângulo"}
 :category :starter
 :difficulty :low
 :related #{"triangle"}
 :instructions
 {:en-US ["Write a function `classify-triangle`, which, given 3 numbers, returns whether a triangle is equilateral (3 equal sides), isosceles (2 equal sides), scalene, or, not a triangle."]
  :pt-BR ["Escreva uma função `classify-triangle`, que, dado 3 números, retorna se um triângulo é equilátero (3 lados iguais), isósceles (2 lados iguais), escaleno, ou, não é um triângulo."]}
 :function-template (defn classify-triangle [a b c])
 :test-cases [{:input (classify-triangle 1 1 1)
               :output :equilateral }
              {:input (classify-triangle 0 0 0)
               :output :invalid}
              {:input (classify-triangle 1 1 2)
               :output :invalid}
              {:input (classify-triangle 4 4 5)
               :output :isosceles}
              {:input (classify-triangle 3 4 5)
               :output :scalene}]
 :uses #{:math-operations :math-comparisons + < cond not = or :keywords}
 :source "https://exercism.io/tracks/clojure/exercises/triangle"}

;; --- [:solution 0]

(defn triangle? [a b c]
  (and
    (< a (+ b c))
    (< b (+ a c))
    (< c (+ a b))))

(defn classify-triangle [a b c]
  (cond
    (not (triangle? a b c))
    :invalid
    (= a b c)
    :equilateral
    (or (= a b) (= b c) (= c a))
    :isosceles
    :else
    :scalene))
