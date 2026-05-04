{:title
 {:en-US "vector distance and nearest neighbor"
  :pt-BR "distância vetorial e vizinho mais próximo"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write two functions:"
          "1. `euclidean-distance` — takes two numeric vectors of equal length and returns the Euclidean distance between them: the square root of the sum of squared differences."
          "2. `nearest-neighbor` — takes a query vector and a map of `{label vector}`, and returns the label of the entry whose vector is closest to the query."]
  :pt-BR ["Escreva duas funções:"
          "1. `euclidean-distance` — recebe dois vetores numéricos de igual comprimento e retorna a distância euclidiana entre eles: a raiz quadrada da soma dos quadrados das diferenças."
          "2. `nearest-neighbor` — recebe um vetor de consulta e um mapa de `{rótulo vetor}`, e retorna o rótulo da entrada cujo vetor é mais próximo da consulta."]}
 :function-template [(defn euclidean-distance [va vb])
                     (defn nearest-neighbor [query index])]
 :test-cases
 [{:input (euclidean-distance [0 0] [3 4])
   :output 5.0}
  {:input (euclidean-distance [1 1] [1 1])
   :output 0.0}
  {:input (nearest-neighbor [1 0]
                            {"cat" [0 1]
                             "dog" [2 0]
                             "fish" [0 0]})
   :output "dog"}
  {:input (nearest-neighbor [5 5]
                            {"a" [4 4]
                             "b" [10 10]
                             "c" [5 6]})
   :output "c"}]
 :uses #{map Math/sqrt apply + min-key}}

;; --- [:solution 0]

(defn euclidean-distance [va vb]
  (->> (map - va vb)
       (map (fn [d] (* d d)))
       (apply +)
       Math/sqrt))

(defn nearest-neighbor [query index]
  (->> index
       (apply min-key (fn [[_label v]] (euclidean-distance query v)))
       first))
