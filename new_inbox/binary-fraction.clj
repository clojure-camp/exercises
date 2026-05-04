{:title
 {:en-US "binary fraction"
  :pt-BR "fração binária"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["In binary, each digit after the decimal point represents a negative power of 2: the first digit is 1/2, the second is 1/4, the third is 1/8, and so on."
          "So `.101` in binary equals 1/2 + 0/4 + 1/8 = 5/8."
          "Write a function `binary-fraction` that takes a string like `\".101\"` and returns the rational number it represents."]
  :pt-BR ["Em binário, cada dígito após o ponto decimal representa uma potência negativa de 2: o primeiro dígito é 1/2, o segundo é 1/4, o terceiro é 1/8, e assim por diante."
          "Então `.101` em binário é igual a 1/2 + 0/4 + 1/8 = 5/8."
          "Escreva uma função `binary-fraction` que recebe uma string como `\".101\"` e retorna o número racional que ela representa."]}
 :function-template (defn binary-fraction [s])
 :test-cases
 [{:input (binary-fraction ".1")
   :output 1/2}
  {:input (binary-fraction ".01")
   :output "1/4"}
  {:input (binary-fraction ".101")
   :output "5/8"}
  {:input (binary-fraction ".1111")
   :output "15/16"}
  {:input (binary-fraction ".0")
   :output 0}]
 :uses #{map-indexed filter reduce + / parse-long subs}}

;; --- [:solution 0]

(require '[clojure.math :as math])

(defn binary-fraction [input]
  (let [digits (-> input
                   (subs 1)
                   (->> (map str)
                        (map parse-long)))]
    (->> digits
         ;; [1 0 1]
         (map-indexed (fn [index digit]
                        (when (= 1 digit)
                          (+ 1 index))))
         ;; [1 nil 3]
         (filter some?)
         ;; [1 3]
         (map (fn [x] (int (math/pow 2 x))))
         ;; [2 8]
         (map /)
         ;; [1/2 1/8]
         (reduce +))))

;; --- [:solution 1]

(require '[clojure.math :as math])

(defn binary-fraction [s]
  (let [digits (->> (subs s 1)
                    (map str)
                    (mapv parse-long))]
    (->> digits
         (map-indexed (fn [index digit]
                        (when (= 1 digit)
                          (/ 1 (int (math/pow 2 (inc index)))))))
         (filter some?)
         (reduce + 0))))

;; --- [:solution 2]

(defn binary-fraction [s]
  (loop [n 1/2
         sum 0
         digits (->> (subs s 1)
                     (map str)
                     (mapv parse-long))]
    (if-let [d (first digits)]
      (case d
        0 (recur (/ n 2) sum (next digits))
        1 (recur (/ n 2) (+ sum n) (next digits)))
      sum)))

;; --- [:solution 3]

(defn binary-fraction [s]
  (map (fn [a b]
         (* a b))
       (->> (subs s 1)
            (map str)
            (mapv parse-long))
       (iterate (fn [x] (/ x 2)) 1/2)))
