{:title
 {:en-US "deep update"
  :pt-BR "atualização profunda"}
 :related #{"deep-add"}
 :instructions
 {:en-US ["Write a function that takes a map with this structure:"
          {:people {1 {:name "james"
                       :points 1}
                    2 {:name "rafd"
                       :points 5}}}
          "as well as an id and a number."
          "Return the same map but with the person with the given id having their name converted to upper-case and the given number added to their points."]
  :pt-BR ["Escreva uma função que receba um mapa com a seguinte estrutura:"
          {:people {1 {:name "james"
                       :points 1}
                    2 {:name "rafd"
                       :points 5}}}
          "bem como um id e um número."
          "Retorne o mesmo mapa, mas com a pessoa com o id dado tendo seu nome convertido para maiúsculas e o número dado adicionado aos seus pontos."]}
 :function-template (defn update-info [info id points])
 :test-cases
 [{:input (update-info {:people {1 {:name "james"
                                    :points 1}
                                 2 {:name "rafd"
                                    :points 5}}}
                       2
                       3)
   :output {:people {1 {:name "james"
                        :points 1}
                     2 {:name "RAFD"
                        :points 8}}}}]
 :category :learning-functions
 :difficulty :low
 :teaches #{update-in}
 :uses #{+ clojure.string/upper-case -> :maps :vectors :keywords}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn update-info
  [info id points]
  (-> info
      (update-in [:people id :name] string/upper-case)
      (update-in [:people id :points] + points)))
