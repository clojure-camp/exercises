{:title
 {:en-US "associative collections"
  :pt-BR "coleções associativas"}
 :instructions
 {:en-US ["Write a function `get-and-set` that makes a change to a collection at some location, and return the original value at that location and the changed collection."
                "`get-and-set` should take 3 arguments: a key, a value, and a collection (a vector or map)."
                "It should return a vector of two things: (1) the value that was at that key in the original collection, and (2) the updated collection."]
  :pt-BR ["Escreva a função `get-and-set` que altera uma coleção em uma posição, e retorna o valor original daquele ponto e a coleção alterada."
          "`get-and-set` deve ter 3 argumentos: uma chave (key), o valor, e a coleção (que pode ser um vetor ou mapa)."
          "Deve retornar um vetor com duas coisas: (1) o valor referente a chave na coleção original, e (2) a coleção atualizada."]}
 :function-template (defn get-and-set [k v col])
 :test-cases
[{:input (get-and-set 0 2 [1 3 4])
  :output [1 [2 3 4]]}
 {:input (get-and-set :a
                      "baz"
                      {:a "foo"
                       :b "quux"})
  :output ["foo"
           {:a "baz"
            :b "quux"}]}]
 :teaches #{get assoc}
 :uses #{:vectors :maps}
 :category :learning-functions
 :difficulty :low}

;; --- [:solution 0]

(defn get-and-set
  [k v col]
  [(get col k) (assoc col k v)])
