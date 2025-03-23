(ns exercises.aoc3
  (:require
   [com.rpl.specter :as s]))


;; choose rand between 2015-2022




;; alt - enter



(defn rand1 [start end]
  (rand-nth (range start (inc end))))

(defn rand2 [start end]
  (+ (rand-int (inc (- end start))) start))

(defn rand3 [start end]
  (let [x (rand-int end)]
   (if (< x start)
     (recur start end)
     x)))

(def recur-count (atom 0))

(defn rand5 [start end]
  (swap! recur-count inc)
  (let [x (rand-int end)]
    (if true
      (rand5 start end)
      x)))


(defn rand6 [start end iterations]
  (let [x (rand-int end)]
    (try
     (rand6 start end (inc iterations))
     (catch StackOverflowError e
       iterations))))

#_(deref recur-count)

(defn rand4 [lower upper]
  (int (+ (* (rand) (- (inc upper) lower)) lower)))

#_(rand1 2015 2022)

(defn stat [total col]
  (into {} (map (fn [[k v]] [k (double (/ v total))]) col)))

(defn stat2 [total col]
  (->> col
       (map (fn [[k v]]
              [k (double (/ v total))]))
       (into {})))

(defn stat3 [n f]
  (->> (repeatedly f)
       (take n)
       (frequencies)
       (map (fn [[k v]]
              [k (double (/ v n))]))
       (sort)))



(get {:a 1} :a)    ;; deref
(assoc {:a 1} :a 2)   ;; reset!
(update {:a 1} :a inc) ;; swap!

(get-in {:a [4 5]} [:a 1])
(assoc-in {:a [4 5]} [:a 1] 2)
(update-in {:a [4 5]} [:a 1] inc)


(update-in {:a [4 5] :b [6 7]} [:a 1] inc)

(->> {:a [4 5] :b [6 7]}
     (map (fn [[k v]]
            [k (update v 1 inc)]))
     (into {}))

(s/select-one [:a 1] {:a [4 5]})
(s/setval [:a 1] 2 {:a [4 5]})
(s/transform [:a 1] inc {:a [4 5]})

(s/select [s/MAP-VALS (s/nthpath 1)] {:a [4 5] :b [6 7]})
(s/transform [s/MAP-VALS (s/nthpath 1)] inc {:a [4 5] :b [6 7]})


#_(stat3 500 #(rand4 2015 2022))

#_(stat 500 (frequencies (take 500 (repeatedly #(rand1 2015 2022)))))

#_(-> (frequencies (take 50 (repeatedly #(rand-between 2015 2023))))
      (update-vals #(double (/ % 50))))

{:a 1  :b 2}  50

{:a 1/50 :b 2/50}

(*
 (/ 1 3) 3)

(*
 (/ 2 3) 3.0)
