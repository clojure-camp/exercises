{:title
 {:en-US "deep add"
  :pt-BR "adição profunda"}
 :related #{"deep-update"}
 :instructions
 {:en-US ["Write a function that takes a map with this structure:"
          {:people {1 {:name "james"
                       :points 1}
                    2 {:name "rafd"
                       :points 5}}}
          "as well as an id, a keyword, and some value."
          "Return the same map but with the person with the given id having the given keyword & value added to their data (see tests for an example)."]
  :pt-BR ["Escreva uma função que receba um mapa com a seguinte estrutura:"
          {:people {1 {:name "james"
                       :points 1}
                    2 {:name "rafd"
                       :points 5}}}
          "bem como um id, uma chave (keyword), e um valor."
          "Retorne o mesmo mapa, mas com a pessoa com o id dado tendo a chave (keyword) e valor passados adicionados aos seus dados (veja os testes para um exemplo)."]}
 :function-template (defn add-info [info id key value])
 :test-cases
 [{:input (add-info {:people {1 {:name "james"
                                 :points 1}
                              2 {:name "rafd"
                                 :points 5}}}
            1
            :stuff
            "beep boop")
   :output {:people {1 {:name "james"
                        :points 1
                        :stuff "beep boop"}
                     2 {:name "rafd"
                        :points 5}}}}]
 :category :learning-functions
 :difficulty :low
 :teaches #{assoc-in}
 :uses #{:maps :vectors :keywords}}

;; --- [:solution 0]

(defn add-info
  [info id key value]
  (assoc-in info [:people id key] value))
