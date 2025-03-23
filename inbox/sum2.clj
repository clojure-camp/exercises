;; given a list and a target #, return a pair of numbers from the list that add up to the #

(let [coll [1 2 5 6 7]
      target 11]
  (first (for [a coll
               b coll
               :when (and
                       (= target (+ a b))
                       (< a b))]
           [a b])))

(let [coll [1 2 5 6 7]
      target 11]
  (loop [coll coll]
    (case (compare target
                   (+ (first coll) (last coll)))
      0 [(first coll) (last coll)]
      1 (recur (rest coll))
      -1 (recur (butlast coll)))))

(let [coll [1 2 5 6 7]
      target 11]
  (loop [l 0
         r (dec (count coll))]
    (case (compare target
                   (+ (coll l) (coll r)))
      0 [(coll l) (coll r)]
      1 (recur (inc l) r)
      -1 (recur l (dec r)))))

