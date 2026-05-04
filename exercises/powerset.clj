{:title
 {:en-US "powerset"
  :pt-BR "conjunto das partes (powerset)"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["Write a function `powerset` that returns all possible subsets of the input set (including the empty set and the full set)."
          "Hint: the powerset of `#{1 2}` is the powerset of `#{1}` plus the powerset of `#{1}` with `2` added to each subset."]
  :pt-BR ["Escreva uma função `powerset` que retorna todos os subconjuntos possíveis do conjunto de entrada (incluindo o conjunto vazio e o conjunto completo)."
          "Dica: o powerset de `#{1 2}` é o powerset de `#{1}` mais o powerset de `#{1}` com `2` adicionado a cada subconjunto."]}
 :function-template (defn powerset [coll])
 :test-cases
 [{:input (powerset #{})
   :output #{#{}}}
  {:input (powerset #{1})
   :output #{#{} #{1}}}
  {:input (powerset #{1 2})
   :output #{#{} #{1} #{2} #{1 2}}}
  {:input (powerset #{1 2 3})
   :output #{#{} #{1} #{2} #{1 2} #{3} #{1 3} #{2 3} #{1 2 3}}}]
 :uses #{:recursion into conj empty? first rest}}

;; --- [:solution 0]

(defn powerset [input-set]
  (if (empty? input-set)
    #{#{}}
    (let [f (first input-set)
          lower-order-set (powerset (rest input-set))]
      (into lower-order-set
            (map (fn [s] (conj s f))
                 lower-order-set)))))
