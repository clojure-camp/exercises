(ns exercises.jun7
  (:require
   [clojure.string :as string]
   [hyperfiddle.rcf :as rcf]))

(rcf/enable!)
;; trim some text up to a target length reached, but do not break up words

;; input
;; "Hello World"
;; 9
;; output
;; "Hello"

;; "Hello Wor" xxxxx

;; "Hello World Other"
;; 11
;; output
;; "Hello World"

;; clojure.string/split
;; count

(defn abbreviate
  [input num]
  (string/join " "
               (reduce (fn [acc word]
                         (if (<= (count (string/join " " (conj acc word))) num)
                           (conj acc word)
                           (reduced acc)))
                       []
                       (string/split input #" +"))))


#_(abbreviate
   "One,      two, you're a shoe"
   10)

#_(take 3 "asdasd")

;; (more-than (length-of (concatenate acc word)) value)

(string/join " "
 (reduce (fn [acc word]
          (if (<= (count (string/join " " (conj acc word))) 10)
           (conj acc word)
           (reduced acc)))
        []
        ["word1" "word2" "word3"]))

#_(re-find #"^.{0,9}\b" "word1 word2 word3")
;; reduce
;; while + atom
;; recursion
;; loop

(defn abbreviate
  [input num]
  (string/join " "
               (reduce (fn [acc word]
                         (if (<= (count (string/join " " (conj acc word))) num)
                           (conj acc word)
                           (reduced acc)))
                       []
                       (string/split input #" +"))))

#_(abbreviate
   "One,      two, you're a shoe"
   10)
(map (fn [chars] (apply str chars)) (split-at 10 "One,      two, you're a shoe"))

(subs "One,      two, you're a shoe" 0 14)
(subs "One,      two, you're a shoe" 14)

(defn whitespace? [char]
  ;; omg so bad
  (re-matches #"\W" (str char)))

(defn abbreviate [s num]
 (if (<= (count s) num)
   s
   (let [part1 (subs s 0 num)
         part2 (subs s num)]
     (string/trim
      (cond
       (whitespace? (first part2))
       part1
       (whitespace? (last part1))
       part1
       :else
        ;; monstrous
       (apply str (reverse (drop-while (complement whitespace?)   (reverse part1)))))))))


(defn abbreviate [s num]
  (cond
    (<= (count s) num)
    s
    (or (whitespace? (nth s num))
        (whitespace? (nth s (dec num))))
    (string/trim (subs s 0 num))
    :else
    (recur s (dec num))))


(rcf/tests
  (abbreviate "One,      two, you're a shoe" 100)
  := "One,      two, you're a shoe"
  (abbreviate "One,      two, you're a shoe" 9)
  := "One,"
  (abbreviate "One,      two, you're a shoe" 10)
  := "One,"
  (abbreviate "One,      two, you're a shoe" 11)
  := "One,"
  (abbreviate "One,      two, you're a shoe" 14))






"One,      tw" "o, you're a shoe"

"One,      two  " " you're a shoe"
