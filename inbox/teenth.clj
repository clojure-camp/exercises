(ns exercises.20240411)

;; teenth

#_(.getDayOfWeek (java.time.LocalDate/now))
#_(java.time.LocalDate/of 2024 java.time.Month/MAY 4)

(defn teenth
  [year month day-of-week]
  (->> (range 13 (inc 19))
       (map (fn [day] (java.time.LocalDate/of year month day)))
       (filter (fn [date] (= (.getDayOfWeek date) day-of-week)))
       first))

#_(teenth 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY)

;; next teenth

#_(.getYear (java.time.LocalDate/now))

#_(.getMonth (java.time.LocalDate/now))

#_(.isBefore (java.time.LocalDate/now)
             (java.time.LocalDate/parse "2025-01-01"))

#_(.plus java.time.Month/DECEMBER 1)

(defn next-teenth [date day-of-week]
  (let [teenth-candidate (teenth (.getYear date) (.getMonth date) day-of-week)]
   (if (.isBefore date teenth-candidate)
     teenth-candidate
     (teenth (.getYear date)
             (.plus (.getMonth date) 1)
             day-of-week))))

#_(next-teenth (java.time.LocalDate/parse "2024-04-01")
               java.time.DayOfWeek/MONDAY)

#_(next-teenth (java.time.LocalDate/parse "2024-04-16")
               java.time.DayOfWeek/MONDAY)


;; can we do better than filter first? (exit early?)
;; is some+when better?

(first (filter even? [1 2 3 4 4 5]))

(some #(when (even? %) %) [1 2 3 4 4 5])

(let [evals-count (atom 0)]
  (->> (repeatedly (fn [] (swap! evals-count inc) (rand-int 1000)))
       (take 100)
       doall)
  @evals-count)

(let [evals-count (atom 0)]
  (->> (repeatedly (fn [] (swap! evals-count inc) (rand-int 1000)))
       (take 100)
       (filter even?)
       first)
  @evals-count)

(let [evals-count (atom 0)]
  (->> (repeatedly (fn [] (swap! evals-count inc) (rand-int 1000)))
       (take 100)
       (some #(when (even? %) %)))
  @evals-count)

;; conclusion: filter+first takes advantage of laziness
;; so don't need to use some+when


;; handle other date sequence types?

:first :monday ;; 1-7
:second :monday ;; 8-14
:third :monday  ;; 15-21
:fourth :monday ;; 22-28

:teenth :monday ;; 13-19
:last :monday ;; (max-for-month 7)
:second-last :monday

(defn x-of-the-month-fn
  [n m]
  (fn [year month day-of-week]
    (->> (range n (inc m))
         (map (fn [day] (java.time.LocalDate/of year month day)))
         (filter (fn [date] (= (.getDayOfWeek date) day-of-week)))
         first)))

(def first-of-the-month
  (x-of-the-month-fn 1 7))

(def teenth-of-the-month
  (x-of-the-month-fn 13 19))

#_(first-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY)
#_(teenth-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY)

(defn x-of-the-month
  [year month day-of-week sequence-type]
  ((apply x-of-the-month-fn
          (case sequence-type
            :teenth [13 19]
            :first [1 7]))
   year month day-of-week))

(defn x-of-the-month
  [year month day-of-week sequence-type]
  ((case sequence-type
     :teenth teenth-of-the-month
     :first first-of-the-month)
   year month day-of-week))

#_(x-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY :teenth)

;; multi methods?

(defmulti x-of-the-month-multi (fn [& args] (last args)))

(defmethod x-of-the-month-multi :first
  [year month day-of-week _]
  ((x-of-the-month-fn 1 7) year month day-of-week))

#_(x-of-the-month-multix 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY :first)

#_(ns-unmap *ns* 'x-of-the-month-multi)

