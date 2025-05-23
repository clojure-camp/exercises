{:title
 {:en-US "number deltas"
  :pt-BR "diferenças entre números"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Given a list of numbers, return a list of the differences between the adjacent numbers."]
  :pt-BR ["Dada uma lista de números, retorne uma lista com as diferenças entre os números adjacentes."]}
 :function-template (defn deltas [nums])
 :test-cases [{:input (deltas [1 2 3 5 -3 10])
               :output [1 1 2 -8 13]}]
 :uses #{- map rest partition}}

;; --- [:solution 0]

(defn deltas
  [nums]
  (map - (rest nums) nums))

(defn deltas-v2
  [nums]
  (->> nums
       (partition 2 1)
       (map (fn [[a b]]
              (- b a)))))
