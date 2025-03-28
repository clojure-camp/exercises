{:title
 {:en-US "number stats"
  :pt-BR "estatísticas de números"}
 :instructions
 {:en-US ["Read a bunch of integers from a string and return the mean, median, mode and standard-deviation. Round each value to two decimal places."]
  :pt-BR ["Leia uma série de inteiros de uma string e retorne a média, mediana, moda e desvio padrão. Arredonde cada valor para duas casas decimais."]}
 :category :synthesis
 :difficulty :mid
 :uses #{slurp clojure.string/split map Integer/parseInt / Math/pow Math/sqrt clojure.set/map-invert
         sort nth Math/round quot count double}
 :function-template (defn stats [s])
 :test-cases [{:input (stats "1 1 2 3 4 5 6 7 8 9 10")
               :output {:mean 5.09 :median 5.0 :mode 1.0 :stddev 3.18}}]}

;; --- [:solution 0]

(require '[clojure.string :as string])
(require '[clojure.set :refer [map-invert]])

(defn round [n]
  (double (/ (Math/round (double (* n 100))) 100)))

(defn mean [nums]
  (/ (reduce + nums) (count nums)))

(defn stddev [nums]
  (-> (reduce (fn [s v] (+ s (Math/pow (- v (mean nums)) 2)))
              0 nums)
      (/ (dec (count nums)))
      (Math/sqrt)))

(defn median [nums]
  (let [sorted (sort nums)
        mid (quot (count nums) 2)]
    (if (odd? (count nums))
      (nth sorted mid)
      (/ (+ (nth sorted mid) (nth sorted (inc mid)))
         2))))

(defn mode [nums]
  (->> nums frequencies map-invert
       sort last second))

(defn stats [s]
  (let [nums (->> (string/split s #"\s+")
                  (map (fn [num-str] (Integer/parseInt num-str))))]
    {:mean (round (mean nums))
     :stddev (round (stddev nums))
     :median (round (median nums))
     :mode (round (mode nums))}))
