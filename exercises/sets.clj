{:title
 {:en-US "using sets"
  :pt-BR "usando conjuntos (sets)"}
 :instructions
 {:en-US ["Given two sets of user interests, return sets indicating: (1) their common interests, (2) each of their unique interests, and (3) all of their interests."]
  :pt-BR ["Dados dois conjuntos (sets) de interesses de usuários, retorne conjuntos indicando: (1) seus interesses em comum, (2) os interesses únicos de cada um, e (3) todos os seus interesses."]}
 :function-template (defn interests-info [a b])
 :test-cases
[{:input (interests-info #{"reading" "lifting" "programming" "boardgames"}
                         #{"sculpting" "programming" "movies" "boardgames"})
  :output {:shared #{"boardgames" "programming"}
           :all #{"boardgames" "lifting" "movies" "programming" "reading"
                  "sculpting"}
           :unique-a #{"lifting" "reading"}
           :unique-b #{"movies" "sculpting"}}}]
 :teaches #{:sets clojure.set/intersection clojure.set/difference clojure.set/union}
 :uses #{:maps :strings :keywords}
 :category :learning-functions
 :difficulty :low}

;; --- [:solution 0]

(require '[clojure.set :as set])

(defn interests-info
  [a b]
  {:shared (set/intersection a b)
   :all (set/union a b)
   :unique-a (set/difference a b)
   :unique-b (set/difference b a)})
