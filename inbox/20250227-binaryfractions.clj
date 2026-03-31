(ns exercises.20250227
  (:require
   [clojure.math :as math]
   [clojure.string :as str]))


;; binary fractions

;; normal binary numbers
;;  1 0 1
;;  4 2 1

;; binary fractions
;; . 1    0     1 ...
;; . 1/2  1/4  1/8 ...

;; 1/2 + 1/8 = 5/8

;; plan:
;;   parse into a vector of integers
;;   map (???)
;;      maybe map-indexed
;;      map with two lists (one of which is inifnite list of 1/2 1/4 1/8...)
;;   reduce to sum up

#_(map-indexed (fn [i x] [i x]) [:a :b :c :d])

#_(map (fn [a b] [a b]) [:a :b :c :d] (range))

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
         (map #(int (math/pow 2 %)))
         ;; [2 8]
         (map /)
         ;; [1/2 1/8]
         (reduce +))))

(comment
  (= (binary-fraction ".101")
     5/8)

          ;; . 1    0     1 ...
          ;; . 1/2  1/4  1/8 ...
  )

(defn binary-parse [input]
  (let [[whole-digits fractional-digits] (->> (str/split input #"\.")
                                              (map (fn [s]
                                                     (->> s
                                                          (map str)
                                                          (map parse-long)))))
        convert-wh  (fn [whole-digits]
                      (->> whole-digits
                           (reverse)
                           ;; [0 1 0 1]
                           (map-indexed (fn [index digit]
                                          (when (= 1 digit)
                                            index)))
                           ;; [1 nil 3]
                           (filter some?)
                           ;; [1 3]
                           (map #(int (math/pow 2 %)))
                           ;; [2 8]
                           (reduce +)))
        convert-fr (fn [fractional-digits]
                     (->> fractional-digits
                              ;; [1 0 1]
                          (map-indexed (fn [index digit]
                                         (when (= 1 digit)
                                           (+ 1 index))))
                              ;; [1 nil 3]
                          (filter some?)
                              ;; [1 3]
                          (map #(int (math/pow 2 %)))
                              ;; [2 8]
                          (map /)
                              ;; [1/2 1/8]
                          (reduce +)))]
    (+ (convert-wh whole-digits)
       (convert-fr fractional-digits))))


(comment

  (= (binary-parse "1010.101")
     85/8)

  ;; 10 + 5/8 = 85/8
  )

;; homework
;;   refactor to use the map-with-two-input-lists strategy
;;     maybe make one of the lists be (1/2 1/4 1/8 ...)

#_(map (fn [a b]
         (* a b))
       [1 0 1 0]
       (map #(math/pow 2 %) (range)))

#_(apply + (concat
            ;; whole
            (map (fn [a b]
                   (* a b))
                 (reverse [1 0 1 0])
                 (iterate #(* % 2) 1))
            ;; fraction
            (map (fn [a b]
                   (* a b))
                 [1 0 1]
                 (iterate #(/ % 2) 1/2))))

;; loop

#_(loop [n 1/2
         sum 0
         digits [1 0 1]]
    (if-let [d (first digits)]
      (case d
        0 (recur (/ n 2) sum (next digits))
        1 (recur (/ n 2) (+ sum n) (next digits)))
      sum))
