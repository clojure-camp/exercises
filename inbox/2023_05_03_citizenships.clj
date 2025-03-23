
(ns exercises.may3
 (:require [clojure.set :as set]
           [clojure.test :as t]))

;; ctrl-s

;; alt-enter

;; figure out which two countries citizenships
;; results in the most countries you can visit

(def source-data
  {:USA #{:Canada :Mexico :Jamaica}
   :Canada #{:Mexico :Trinidad :India :Jamaica}
   :Poland #{:Germany :France :USA}})

(defn union [c1 c2]
  (set/union (c1 source-data) (c2 source-data) #{c1 c2}))

(count (union :USA :Canada))
(apply union #{:USA :Canada})

(keys source-data)
#_(set/union #{1 2 3} #{3 4 5})

(defn t [dat]
  (set (for [k1 (keys dat)
             k2 (rest (keys dat))
             :when (not= k1 k2)]
         #{k1 k2})))

(map (fn [country-pair]
       [country-pair (count (apply union country-pair))])
     (t source-data))

(apply max-key last (map (fn [country-pair]
                          [country-pair (count (apply union country-pair))])
                         (t source-data)))


(defn tee [x] (println x) x)

(->> source-data
     t
     (map (fn [country-pair]
            [country-pair (count (apply union country-pair))]))
     (apply max-key last))

;; TODO: if there is a tie, return more than one result


(apply max-key (fn [country-pair]
                 (count (apply union country-pair)))
               (t source-data))

(->> source-data
     t
     (apply max-key (fn [country-pair]
                      (count (apply union country-pair)))))


