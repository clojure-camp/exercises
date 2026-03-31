(ns exercises.wordle)

(defn score [guess solution]
  (let [guess-v (vec guess)
        solution-v (vec solution)]
    (->> (range 5)
         (reduce
          (fn [acc index]
            (cond
              (= (solution-v index) (guess-v index))
              (-> acc
                  (update-in [:frequencies (guess-v index)] dec)
                  (update :output conj :green))
              (pos? ((:frequencies acc) (guess-v index) 0))
              (-> acc
                  (update-in [:frequencies (guess-v index)] dec)
                  (update :output conj :yellow))
              :else
              (update acc :output conj nil)))
          {:frequencies (frequencies solution)
           :output []})
         :output)))

(= (score "found" "conch") [nil :green nil :yellow nil])
(= (score "worry" "rebel") [nil nil :yellow nil nil])

(= (score "e12ee" "ee345") [:green nil nil :yellow nil])

