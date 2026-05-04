{:title
 {:en-US "match and merge"
  :pt-BR "combinar e mesclar"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write a function `match-and-merge` that takes a key function `k`, and two collections of maps `coll-a` and `coll-b`."
          "It should return a collection of maps where items from both collections that share the same value for key `k` are merged together."
          "Items that exist in only one collection should still appear in the output."
          "The order of the result does not matter."]
  :pt-BR ["Escreva uma função `match-and-merge` que recebe uma função de chave `k` e duas coleções de mapas `coll-a` e `coll-b`."
          "Ela deve retornar uma coleção de mapas onde os itens de ambas as coleções que compartilham o mesmo valor para a chave `k` são mesclados."
          "Itens que existem em apenas uma coleção ainda devem aparecer no resultado."
          "A ordem do resultado não importa."]}
 :function-template (defn match-and-merge [k coll-a coll-b])
 :test-cases
 [{:input (set (match-and-merge :id
                                [{:id 1 :name "Alice"}
                                 {:id 2 :name "Bob"}
                                 {:id 3 :name "Cathy"}
                                 {:id 4 :name "Donald"}]
                                [{:id 5 :email "evelyn@example.com"}
                                 {:id 1 :email "alice@example.com"}
                                 {:id 9 :email "x@example.com"}
                                 {:id 3 :email "cathy@example.com"}]))
   :output #{{:id 1 :name "Alice" :email "alice@example.com"}
             {:id 2 :name "Bob"}
             {:id 3 :name "Cathy" :email "cathy@example.com"}
             {:id 4 :name "Donald"}
             {:id 5 :email "evelyn@example.com"}
             {:id 9 :email "x@example.com"}}}]
 :uses #{concat group-by vals map merge}}

;; --- [:solution 0]

(defn match-and-merge [k coll-a coll-b]
  (->> (concat coll-a coll-b)
       (group-by k)
       vals
       (map (fn [group] (apply merge group)))))
