{:title
 {:en-US "map transforms"
  :pt-BR "transformações de mapas"}
 :category :learning-functions
 :teaches #{assoc dissoc update}
 :uses #{-> :keywords :strings :maps inc}
 :instructions
 {:en-US ["Write a function that takes a map with the below format:"
          {:name "james" :disabled? true :points 0}
          "and returns a map with the `:disabled?` key removed, 1 added to the `:points` value, add a key called `:activated` added with the value `\"now\"`; all other keys should be left as-is."]
  :pt-BR ["Escreva uma função que receba um mapa com a seguinte estrutura:"
          {:name "james" :disabled? true :points 0}
          "e retorne um mapa com a chave `:disabled?` removida, 1 adicionado ao valor de `:points`, e uma chave chamada `:activated` adicionada com o valor `\"now\"`; todas as outras chaves devem ser mantidas como estão."]}
 :difficulty :low
 :function-template (defn activate [person])
 :test-cases
[{:input (activate {:name "james"
                    :disabled? true
                    :points 0})
  :output {:name "james"
           :points 1
           :activated "now"}}
 {:input (activate {:name "james"
                    :disabled? true
                    :points 0
                    :beep (quote boop)
                    :blaap 123})
  :output {:name "james"
           :points 1
           :activated "now"
           :beep (quote boop)
           :blaap 123}}]}

;; --- [:solution 0]

(defn activate
  [person]
  (-> person
      (dissoc :disabled?)
      (assoc :activated "now")
      (update :points inc)))
