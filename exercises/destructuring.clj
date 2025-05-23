{:title
 {:en-US "destructuring"
  :pt-BR "desestruturação (destructuring)"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Refactor the below function to use destructuring instead of unpacking values"
          (defn process-person
            [arg]
            (let [name (first arg)
                  info (second arg)
                  age (:age info)
                  interests (:interests info)
                  interest-1 (first interests)
                  other-interests (rest interests)]
              {:description (str name " is " age " years old.\n"
                                 "They like " interest-1 " and " (count other-interests) " other things.")
               :name name
               :info info}))]
  :pt-BR ["Refatore a seguinte função para usar desestruturação (destructuring) ao invés de acessar cada valor"
          (defn process-person
            [arg]
            (let [name (first arg)
                  info (second arg)
                  age (:age info)
                  interests (:interests info)
                  interest-1 (first interests)
                  other-interests (rest interests)]
              {:description (str name " is " age " years old.\n"
                                 "They like " interest-1 " and " (count other-interests) " other things.")
               :name name
               :info info}))]}
 :function-template (defn process-person [arg])
 :test-cases
[{:input (process-person ["James"
                          {:age 30
                           :interests ["highland games" "code" "reading"]}])
  :output
  {:name "James"
   :info {:age 30
          :interests ["highland games" "code" "reading"]}
   :description
   "James is 30 years old.\nThey like highland games and 2 other things."}}]
 :teaches #{:destructuring}
 :uses #{:maps str first second rest}}

;; --- [:solution 0]

(defn process-person
  [[name {:keys [age] [interest-1 & other-interests] :interests :as info}]]
  {:description (str name " is " age " years old.\n"
                     "They like " interest-1 " and " (count other-interests) " other things.")
   :name name
   :info info})

(defn process-person-2
  "Alternate solution that split things up a bit more"
  [[name {:keys [age interests] :as info}]]
  (let [[interest-1 & other-interests] interests]
    {:description (str name " is " age " years old.\n"
                       "They like " interest-1 " and " (count other-interests) " other things.")
     :name name
     :info info}))

