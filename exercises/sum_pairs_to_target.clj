{:title
 {:en-US "Find a pair that add up to target"}
 :category :starter
 :difficulty :low
 :instructions
 {:en-US ["Given a list of integers and a target integer, return a pair of numbers from the list that add up to the target integer(if such a pair exists). The input list is unsorted, may contain duplicates, and may be empty."]}
 :function-template (defn find-sum-pair [coll target])
 :test-cases
 [{:input (find-sum-pair [1 2 5 6 7] 11)
   :output [5 6]}
  {:input (find-sum-pair [4 1 5 2] 3)
   :output [1 2]}
  {:input (find-sum-pair [3 4 3] 6)
   :output [3 3]}
  {:input (find-sum-pair [] 6)
   :output nil}
  {:input (find-sum-pair [3] 6)
   :output nil}
  {:input (find-sum-pair [3 4] 6)
   :output nil}]
 :teaches #{for loop and}
 :uses #{for :when recur loop case and compare}}

;; --- [:function-template]
(defn find-sum-pair
  [coll target])

;; --- [:solution 0]

(defn find-sum-pair
  [coll target]
  (loop [seen #{}
         s (seq coll)]
    (when s
      (let [x (first s)
            y (- target x)]
        (if (contains? seen y)
          ;; returning in ascending order
          [(min x y) (max x y)]
          (recur (conj seen x) (next s)))))))

;; --- [:solution 1]

(defn find-sum-pair
  [coll target]
  (-> (for [[index-a a] (map-indexed vector coll)
            [index-b b] (map-indexed vector coll)
            :when (and
                   (= target (+ a b))
                   (< index-a index-b))]
        [a b])
      first))

;; --- [:solution 2]

(defn find-sum-pair
  [coll target]
  (loop [coll (sort coll)]
    (when (<= 2 (count coll))
      (case (compare target
                     (+ (first coll) (last coll)))
        0 [(first coll) (last coll)]
        1 (recur (rest coll))
        -1 (recur (butlast coll))))))

;; --- [:solution 3]

(defn find-sum-pair
  [coll target]
  (let [coll (vec (sort coll))]
    (loop [l 0
           r (dec (count coll))]
      (when (< l r)
        (case (compare target
                       (+ (coll l) (coll r)))
          0 [(coll l) (coll r)]
          1 (recur (inc l) r)
          -1 (recur l (dec r)))))))
