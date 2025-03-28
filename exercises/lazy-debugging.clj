{:title
 {:en-US "debugging"
  :pt-BR "depuração"}
 :difficulty :high
 :category :starter
 :instructions
 {:en-US ["Debug the following code:"
          (defn process
            "For a given list, return the sum of all the
                   numbers as well as all the values as strings."
            [things]
            (let [sum (atom 0)
                  str-things (for [thing things]
                               (do (when (number? thing)
                                     (swap! sum + thing))
                                   (str thing)))]
              {:str-things str-things
               :sum (deref sum)}))]
  :pt-BR ["Depure o código a seguir:"
          (defn process
            "Para uma lista dada, retorna a soma de todos os
             números bem como todos os valores como strings"
             [things]
            (let [sum (atom 0)
                  str-things (for [thing things]
                               (do (when (number? thing)
                                     (swap! sum + thing))
                                   (str thing)))]
              {:str-things str-things
               :sum (deref sum)}))]}
 :test-cases
[{:input (process ["hello" 2 :world 1])
  :output {:str-things ["hello" "2" ":world" "1"]
           :sum 3}}
 {:input (:sum (process ["hello" 2 :world 1]))
  :output 3}]
 :uses #{when atom for do doall :lazy-evaluation}}

;; --- [:solution 0]

(defn process
  "For a given list, return the sum of all the
  numbers as well as all the values as strings."
  [things]
  (let [sum (atom 0)
        str-things (doall
                    (for [thing things]
                      (do (when (number? thing)
                            (swap! sum + thing))
                          (str thing))))]
    {:str-things str-things
     :sum (deref sum)}))
