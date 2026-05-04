{:title
 {:en-US "random integer in range"
  :pt-BR "inteiro aleatório em intervalo"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function `rand-in-range` that returns a random integer between `start` and `end`, inclusive on both ends."
          "Hint: `rand-int` returns a random integer from 0 (inclusive) to n (exclusive)."]
  :pt-BR ["Escreva uma função `rand-in-range` que retorna um inteiro aleatório entre `start` e `end`, inclusive nos dois extremos."
          "Dica: `rand-int` retorna um inteiro aleatório de 0 (inclusivo) até n (exclusivo)."]}
 :function-template (defn rand-in-range [start end])
 :test-cases
 [{:input (let [r (rand-in-range 1 5)]
            (and (int? r) (>= r 1) (<= r 5)))
   :output true}
  {:input (let [r (rand-in-range 10 10)]
            (= r 10))
   :output true}
  {:input (count (distinct (repeatedly 200 (fn [] (rand-in-range 1 5)))))
   :output 5}]
 :uses #{rand-int + - int?}}

;; --- [:solution 0]

(defn rand-in-range [start end]
  (+ (rand-int (inc (- end start)))
     start))
