{:title
 {:en-US "numbers within threshold"
  :pt-BR "números dentro do limiar"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function `nums-within-threshold?` that takes a list of numbers and a threshold value."
          "Return `true` if any two numbers in the list are within `threshold` of each other (i.e., their difference is less than or equal to `threshold`)."]
  :pt-BR ["Escreva uma função `nums-within-threshold?` que recebe uma lista de números e um valor de limiar."
          "Retorne `true` se quaisquer dois números na lista estiverem dentro do `threshold` um do outro (ou seja, a diferença entre eles é menor ou igual ao `threshold`)."]}
 :function-template (defn nums-within-threshold? [numbers threshold])
 :test-cases
 [{:input (nums-within-threshold? [1 2 5 75 13] 1)
   :output true}
  {:input (nums-within-threshold? [1 3 5 75 13] 1)
   :output false}
  {:input (nums-within-threshold? [1 3 5 75 13] 2)
   :output true}
  {:input (nums-within-threshold? [100] 5)
   :output false}
  {:input (nums-within-threshold? [10 10] 0)
   :output true}]
 :uses #{sort partition map some boolean -}}

;; --- [:solution 0]

(defn nums-within-threshold? [numbers threshold]
  (->> numbers
       sort
       (partition 2 1)
       (map (fn [[a b]] (- b a)))
       (some (fn [diff] (<= diff threshold)))))

;; --- [:solution 1]

(defn nums-within-threshold? [numbers threshold]
  (let [sorted-numbers (sort numbers)]
    (->> (map - sorted-numbers (rest sorted-numbers))
         (some (fn [diff] (<= diff threshold))))))

