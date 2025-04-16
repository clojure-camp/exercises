{:title
 {:en-US "teenth"}
 :category :synthesis
 :difficulty :mid
 :related #{}
 :instructions
 {:en-US ["Someone figured out that you can meet on the teenth Tuesday of a month - meaning, on the Tuesday of each month that ends in -teenth (13,14,15,16,17,18,19...) ... "
          "Write a function that determines if a given day is a -teenth day"
          "Write a function that for a month, and :tuesday, returns the teenth tuesday of that month"
          "Note that 'Monteenth', 'Tuesteenth' , etc are all made up words. There was a meetup whose members realised that there are exactly 7 days that end in '-teenth'."
          "Therefore, one is guaranteed that each day of the week (Monday, Tuesday, ...) will have exactly one date that is named with '-teenth' in every month."
          #_"https://github.com/exercism/problem-specifications/blob/306c038fcabe3f7c75e4dd7f0684493096301da2/meetup.md"]}
 :teaches #{:dates java.time.LocalDate map filter range first apply case}
 :uses #{:dates}}

;; --- [:function-template]
(defn teenth [year month day-of-week])
(defn next-teenth [date day-of-week])
;; --- [:solution 0]

(defn teenth
  [year month day-of-week]
  (->> (range 13 (inc 19))
       (map (fn [day] (java.time.LocalDate/of year month day)))
       (filter (fn [date] (= (.getDayOfWeek date) day-of-week)))
       first))

(defn next-teenth [date day-of-week]
  (let [teenth-candidate (teenth (.getYear date) (.getMonth date) day-of-week)]
    (if (.isBefore date teenth-candidate)
      teenth-candidate
      (teenth (.getYear date)
              (.plus (.getMonth date) 1)
              day-of-week))))

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

(defn x-of-the-month
  [year month day-of-week sequence-type]
  ((case sequence-type
     :teenth teenth-of-the-month
     :first first-of-the-month)
   year month day-of-week))

;; --- test-cases
(require '[clojure.test :refer [is]])

(is (= "2024-04-15"
       (-> (next-teenth (java.time.LocalDate/parse "2024-04-01")
                        java.time.DayOfWeek/MONDAY)
           str)))

(is (= "2024-05-13"
       (-> (next-teenth (java.time.LocalDate/parse "2024-04-16")
                        java.time.DayOfWeek/MONDAY)
           str)))

(is (= "2024-05-06"
       (-> (first-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY)
           str)))

(is (= "2024-05-13"
       (-> (teenth-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY)
           str)))

(is (= "2024-05-13"
       (-> (x-of-the-month 2024 java.time.Month/MAY java.time.DayOfWeek/MONDAY :teenth)
           str)))
