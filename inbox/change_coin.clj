
(defn -make-change-greedy [target coin-set]
  (reduce
    (fn [_ coin]
      (let [diff (- target coin)]
        (cond
          (zero? diff)
          (reduced [coin])
          (neg? diff)
          nil
          :else
          (when-let [result (-make-change-greedy diff coin-set)]
            ;; early exit here makes this approach greedy
            (reduced (conj result coin))))))
    nil
    coin-set))

(defn make-change-greedy [target coin-set]
  (frequencies (-make-change-greedy target coin-set)))

(defn -make-change-optimal [target coin-set]
  (->> (reduce
         (fn [memo coin]
           (let [diff (- target coin)]
             (cond
               (zero? diff)
               ;; early exit, b/c an exact match is guaranteed to be less coins
               (reduced [[coin]])
               (neg? diff)
               memo
               :else
               (if-let [result (-make-change-optimal diff coin-set)]
                 (conj memo (conj result coin))
                 memo))))
         []
         coin-set)
       ;; above, we keep track of all solutions, now keep only the shortest
       (sort-by count)
       first))

(defn make-change-optimal [target coin-set]
  (frequencies (-make-change-optimal target coin-set)))

(def make-change make-change-optimal)

#_(make-change-optimal 17 #{4 9 14 15 16 18 25}) ; {9 1, 4 2}
#_(make-change-greedy 17 #{4 9 14 15 16 18 25}) ; {9 1, 4 2}
#_(make-change-optimal 6 #{1 3 4}) ;  {3 2}
#_(make-change-greedy 6 #{1 3 4}) ; {1 2, 4 1}

;; could further optimize by using memoization
