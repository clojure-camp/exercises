{:title
 {:en-US "regex extraction"
  :pt-BR "extração com regex"}
 :difficulty :mid
 :category :learning-functions
 :instructions
 {:en-US ["Given a string like `\"Lastname, Firstname (some title)\"`, use a regular expression to extract out the first name, last name, and title."]
  :pt-BR ["Dada uma string como `\"Sobrenome, Nome (algum título)\"`, use uma expressão regular (regex) para extrair o primeiro nome, sobrenome, e título."]}
 :function-template (defn extract-info [info-str])
 :test-cases
 [{:input (extract-info "World, Hello (Cool Person)")
   :output {:first "Hello"
            :last "World"
            :title "Cool Person"}}
  {:input (extract-info "Beep-Boop III, Garf Buzz (🐱)")
   :output {:first "Garf Buzz"
            :last "Beep-Boop III"
            :title "🐱"}}]
 :teaches #{re-matches}
 :uses #{}
 :related #{"regex-searching"}}

;; --- [:solution 0]

(defn extract-info
  [info-str]
  (let [[_ lastn firstn title] (re-matches #"([^,]+), ([^(]+) \(([^)]+)\)" info-str)]
    {:first firstn
     :last lastn
     :title title}))
