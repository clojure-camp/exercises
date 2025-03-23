(ns exercises.apr13
  (:require
   [hyperfiddle.rcf :as rcf]
   [com.rpl.specter :as s]))

(rcf/enable!)

;; given a list of numbers
;; any numbers closer to each other than a given threshold

(defn any-close? [threshold list]
  (->> list
       sort
       (partition 2 1)
       (map (fn [[first last]]
              (- last first)))
       (some (fn [diff]
              (<= diff threshold)))
       boolean))

(defn numbers-closer-than-threshold? [numbers threshold]
  (let [sorted-numbers (sort numbers)]
    (some #(< % threshold)
          (map - sorted-numbers (rest sorted-numbers)))))


(rcf/tests
 (any-close? 1 [1 2 5 75 13]) := true
 (any-close? 1 [1 3 5 75 13]) := false
 nil)


;; given a string containing multiple
;; groups of nested parens, seperate into seperate strings
;; of balanced parens

(defn split-paren-groups [s]
  (->> s
       (reductions
        (fn [memo s]
           (case s
                  \space memo
                  \( (update memo :count inc)
                  \) (update memo :count dec)))
        {:out []
         :count 0})))

;; TODO split


(rcf/tests
 (split-paren-groups "( ) (( )) (( ) ( ) )")
 := ["()" "(())" "(()())"])
