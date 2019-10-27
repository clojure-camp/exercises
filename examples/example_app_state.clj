(ns example.state.core
  (:require
   [clojure.string :as string]
   [clojure.set :as set :refer [intersection]]))

(def initial-state
  {:people {1 {:name "James"
               :birthdate #inst "1989-07-22T11:00:00-05:00"
               :interests #{:lifting :coding}}
            2 {:name "Rafal"
               :birthdate #inst "1989-06-03T12:00:00-00:00"
               :interests #{:coding :board-games}}
            3 {:name "Gleep"
               :birthdate #inst "1468-10-11T00:00:00-00:00"
               :interests #{:BLOOD}}}

   :interests {:lifting {:description "Lifting weights"}
               :coding {:description "Writing code"}
               :board-games {:description "Playing Board Games"}}})

(defn birthdate->age
  [date]
  (int (/ (- (.getTime (java.util.Date.)) (.getTime date))
          (* 1000 60 60 24 365))))

(defn person-detail
  [{:keys [birthdate interests] :as person}]
  [:div {:class "details"}
   [:h3 (person :name)]
   [:h3 "Age"]
   (birthdate->age birthdate)
   [:h3 "Interests"]
   [:div (->> interests
             (map (fn [interest]
                    [:a {:href (str "/interest/" (name interest))}
                     (name interest)]))
             (string/join "\n"))]])

(defn people-page
  [state]
  [:div.people
   (map (fn [[id {:keys [name] :as person}]]
          [:details
           [:summary [:a {:href (str "/person/" id)}
                      (str "Info for " name)]]
           (person-detail person)])
        (:people state))])

(defn interests-page
  [state]
  [:div.interests
   (map (fn [[id {desc :description}]]
          [:div.interest
           [:a {:href (str "/interest/" (name id))}
            (name id)]
           [:p desc]])
        (state :interests))])

(defn people-comparison-view
  [person-a person-b]
  (let [[age-a age-b] (->> [person-a person-b]
                          (map :birthdate)
                          (map birthdate->age))
        age-diff (Math/abs (- age-a age-b))
        common-interests (intersection (:interests person-a)
                                       (:interests person-b))]
    [:div.comparison
     [:p (str (:name person-a) " and " (:name person-b) " are "
              (if (zero? age-diff)
                "the same age"
                (str age-diff " years apart"))
              ".")]
     [:p (if-let [ex-common (first common-interests)]
           (str "Both people like " (string/capitalize (name ex-common)))
           "No common interests")]]))

(comment
  (prn (people-page initial-state))
  (people-comparison-view (get-in initial-state [:people 1])
                           (get-in initial-state [:people 2]))
  (people-comparison-view (get-in initial-state [:people 1])
                           (get-in initial-state [:people 3])))
