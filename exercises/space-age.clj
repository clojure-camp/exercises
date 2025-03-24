{:title
 {:en-US "space age"
  :pt-BR "idade espacial"}
 :category :starter
 :difficulty :low
 :instructions
 {:en-US ["Implement a function that lets someone convert their age on one planet to their age on another."
          "For example:"
          "30 years on Earth is about 1 year on Saturn (a year being a single revolution around the sun)."
          "10 years on Mars is 78 years on Mercury."
          "You will need to look up the relevant data on planets yourself."]
  :pt-BR ["Escreva uma função que permita a alguém converter sua idade em um planeta para sua idade em outro."
          "Por exemplo:"
          "30 anos na Terra são aproximadamente 1 ano em Saturno (um ano sendo uma única revolução ao redor do sol)."
          "10 anos em Marte são 78 anos em Mercúrio."
          "Você precisará procurar os dados relevantes sobre os planetas por conta própria."]}
 :function-template (defn convert-space-age [age source-planet target-planet])
 :test-cases [{:input (convert-space-age 30 :earth :saturn)
               :output 1}
              {:input (convert-space-age 10 :mars :mercury)
               :output 78}]
 :uses #{int :maps :math-operations * /}
 :source "https://exercism.io/tracks/clojure/exercises/space-age"}

;; --- [:solution 0]

(def lookup
  {:mercury 0.2408467
   :venus 0.61519726
   :earth 1
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

(defn convert-space-age [age source-planet target-planet]
  (int (* age (/ (lookup source-planet)
                 (lookup target-planet)))))
