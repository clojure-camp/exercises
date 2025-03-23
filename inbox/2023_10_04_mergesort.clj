(ns exercises.20231004
 (:require [hyperfiddle.rcf :as rcf]))

(rcf/enable!)
;; implement merge-sort

;; merge - given two sorted lists, combines them (case-by-case interleaving)
;; merge-sort

;; [1 2 5 6 9]   [3 7]   => [1 2 3 5 6 7 9]

#_(map vector [1 2 3] [4 5])



;; reduce over one list, inserting into the other
  ;;  inserting by linear search   O(n2)
  ;;  inserting by binary search   O(n log n)

(defn insert [coll x]
  (let [[a b] (split-with (partial >= x) coll)]
    (concat a (list x) b)))

(rcf/tests
 (insert [1 2 5 6 9] 3) := [1 2 3 5 6 9])

(defn merge-lists [list-a list-b]
  (prn "M" list-a list-b)
  (reduce (fn [acc itm]
            (insert acc itm))
          list-a
          list-b))

;; keep track of two "pointers" into each list, conj and advance   O(n)
    ;; ^ do this recursively

(defn merge-lists-2 [list-a list-b]
  (loop [acc []
         a list-a
         b list-b]
   (cond
    (empty? a)
    (concat acc b)

    (empty? b)
    (concat acc a)

    (< (first a) (first b))
    (recur (conj acc (first a))
           (rest a)
           b)

    :else
    (recur (conj acc (first b))
           a
           (rest b)))))




(rcf/tests
  (merge-lists-2 [1 2 5 6 9] [3 7]) := [1 2 3 5 6 7 9])

;; [1]    [2 3 5 6 7 9]


;;      [1 2 9 3 7 5 6]
;;       /            \
;;  [1 2 9 3]        [7 5 6]
;;   /     \         /     \
;; [1 2]   [9 3]    [7 5]  [6]
;; /  \     / \      / \
;; [1][2]  [9] [3]  [7] [5]

;; janky insertion-merge-sort hybrid monstrosity
(defn merge-sort [coll]
  (prn "S" coll)
  (if (= (count coll) 1)
    coll
    (merge-lists
     [(first coll)]
     (merge-sort (rest coll)))))


(defn merge-sort [coll]
  (prn "S" coll)
  (if (= (count coll) 1)
    coll
    (merge-lists
     (merge-sort (take (quot (count coll) 2) coll))
     (merge-sort (drop (quot (count coll) 2) coll)))))


(rcf/tests
 (merge-sort [1 2 9 3 7 5 6]) := [1 2 3 5 6 7 9])
;; bonus, modify slight to make be insertion sort
