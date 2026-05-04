{:title
 {:en-US "best pair of citizenships"
  :pt-BR "melhor par de cidadanias"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["You have a map where each key is a country, and the value is a set of countries that citizens of that country can visit without a visa."
          "Write a function `best-citizenships` that returns the pair of citizenships (as a set of two country names) that gives access to the greatest number of countries in total (including the two home countries themselves)."]
  :pt-BR ["Você tem um mapa onde cada chave é um país, e o valor é um conjunto de países que cidadãos daquele país podem visitar sem visto."
          "Escreva uma função `best-citizenships` que retorna o par de cidadanias (como um conjunto de dois nomes de países) que dá acesso ao maior número total de países (incluindo os dois países de origem)."]}
 :function-template (defn best-citizenships [passport-data])
 :test-cases
 [{:input (best-citizenships
           {:USA #{:Canada :Mexico :Jamaica}
            :Canada #{:Mexico :Trinidad :India :Jamaica}
            :Poland #{:Germany :France :USA}})
   :output #{:USA :Canada}}
  {:input (best-citizenships
           {:A #{:B :C}
            :B #{:D :E}
            :C #{:F}})
   :output #{:A :B}}]
 :uses #{for keys clojure.set/union apply max-key count set}}

;; --- [:solution 0]

(require '[clojure.set :as set])

(defn best-citizenships [passport-data]
  (let [combined-access
        (fn [c1 c2]
          (set/union (get passport-data c1)
                     (get passport-data c2)
                     #{c1 c2}))
        all-pairs
        (for [k1 (keys passport-data)
              k2 (keys passport-data)
              :when (not= k1 k2)]
          #{k1 k2})
        unique-pairs (set all-pairs)]
    (->> unique-pairs
         (apply max-key (fn [pair]
                          (count (apply combined-access pair)))))))
