{:title
 {:en-US "Find a pair that add up to target"}
 :category :starter
 :difficulty :low
 :instructions
 {:en-US ["Given a list and a target #, return a pair of numbers from the list that add up to the target #"]}
 :function-template (defn find-sum-pair [coll target])
 :test-cases
 [{:input (find-sum-pair [1 2 5 6 7] 11)
   :output [5 6]}]
 :teaches #{for "recursion" and}
 :uses #{for :when recur loop case and compare}}

;; --- [:function-template]
(defn find-sum-pair
  [coll target])

;; --- [:solution 0]

(defn find-sum-pair
  [coll target]
  (-> (for [a coll
            b coll
            :when (and
                   (= target (+ a b))
                   (< a b))]
        [a b])
      first))

;; --- [:solution 1]

(defn find-sum-pair
  [coll target]
  (loop [coll coll]
    (case (compare target
                   (+ (first coll) (last coll)))
      0 [(first coll) (last coll)]
      1 (recur (rest coll))
      -1 (recur (butlast coll)))))

;; --- [:solution 2]

(defn find-sum-pair
  [coll target]
  (loop [l 0
         r (dec (count coll))]
    (case (compare target
                   (+ (coll l) (coll r)))
      0 [(coll l) (coll r)]
      1 (recur (inc l) r)
      -1 (recur l (dec r)))))

;; --- test-cases
(require '[clojure.test :refer [is]])

(is (= [5 6]
       (find-sum-pair [1 2 5 6 7] 11)))
