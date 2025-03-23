(ns exercises.20230906
  (:require [hyperfiddle.rcf :as rcf]))

(rcf/enable!)

;; given a list of numbers (haystack), and a number (needle)
;; find the N (count) closest numbers in haystack to needles



;; for every item, calculate dist {10 => 8, 9 => 7...}
;; order by dist
;; take until satisfied



(defn searchx [haystack n needle]
  (->> (map (fn [val] [val (Math/abs (- needle val))]) haystack)
       (sort-by second)
       (take n)
       (map first)))

(defn searchy [haystack n needle]
  (->> haystack
       (sort-by (fn [val] (Math/abs (- needle val))))
       (take n)))

(defn distance [a b]
  (Math/abs (- a b)))

(defn search [haystack n needle]
  (->> haystack
       (sort-by (partial distance needle))
       (take n)))

;; sort
;; split around the number
;; take from the two lists <- tricky part

(defn search [haystack n needle]
  (let [[a b] (->> haystack
                   sort
                   (split-with (partial > needle)))]
   (loop [list-a (reverse a)
          list-b b
          result-list []]
     (if (= n (count result-list)) ;; what if not enough items in list?
       result-list
       (cond
         (empty? list-a)
         (recur list-a (rest list-b) (conj result-list (first list-b)))
         (empty? list-b)
         (recur (rest list-a) list-b (conj result-list (first list-a)))
         :else
         (let [dist-a (distance needle (first list-a))
               dist-b (distance needle (first list-b))]
           (if (< dist-a dist-b)
             (recur (rest list-a) list-b (conj result-list (first list-a)))
             (recur list-a (rest list-b) (conj result-list (first list-b))))))))))

;; go through unsorted list, maintaining N items with lowest dist


;; fun bonus next time: use modified version of above to implement merge-sort

;; TODO

(rcf/tests
  (search [10 1 3 4 8] 1 7) := [8]
  (search [11 1 3 4 8] 2 7) := [8 4]
  (search [10 1 3 4 8] 3 1) := [1 3 4])


;; alt-enter  -> top level form
;; ctrl-enter ->
