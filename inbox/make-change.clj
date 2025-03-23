
;; greedy

(defn find-change
  [remaining coins-used coin-set]
  (cond
    (zero? remaining) coins-used
    (neg? remaining) nil
    :else
    (->> coin-set
         (keep (fn [coin]
                 (find-change
                  (- remaining coin)
                  (update coins-used coin (fnil inc 0))
                  coin-set)))
         first)))

(defn make-change [amount coin-set]
  (find-change amount {} (reverse (sort coin-set))))

;; optimal

(defn make-change [amount coin-set]
  (let [find-change (fn [f remaining coins-used coin-set]
                      (cond
                        (= remaining 0) coins-used
                        (< remaining 0) nil
                        :else
                        (->> coin-set
                             (keep (fn [coin]
                                     (f f
                                        (- remaining coin)
                                        (update coins-used coin (fnil inc 0))
                                        coin-set)))
                             (apply min-key #(apply + (vals %))))))
        m-find-change (memoize find-change)]
    (->> (m-find-change m-find-change amount {} coin-set))))

#_(->> (make-change 157 #{1 5 10 25})
       time)

#_(make-change 10 #{7 6 4 1})
