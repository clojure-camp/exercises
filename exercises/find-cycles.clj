{:title
 {:en-US "find cycles"
  :pt-BR "encontrar ciclos"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["You have a set of directed connections between nodes where every node points to exactly one other node and every node is pointed to by exactly one other node. These connections form one or more cycles."
          "Write a function `find-cycles` that takes a string of space-separated two-character pairs (e.g. `\"ab cd\"`) and returns a set of sets, where each inner set contains all the nodes in one cycle."
          "For example, `\"ab bc ca\"` represents the cycle a→b→c→a, and should return `#{#{\\a \\b \\c}}`."
          "Hint: process each pair one at a time, maintaining a set of partial cycles. For each pair, check if either node already belongs to a partial cycle."]
  :pt-BR ["Você tem um conjunto de conexões direcionadas entre nós onde cada nó aponta para exatamente um outro nó e cada nó é apontado por exatamente um outro nó. Essas conexões formam um ou mais ciclos."
          "Escreva uma função `find-cycles` que recebe uma string de pares de dois caracteres separados por espaços (ex: `\"ab cd\"`) e retorna um conjunto de conjuntos, onde cada conjunto interno contém todos os nós em um ciclo."
          "Por exemplo, `\"ab bc ca\"` representa o ciclo a→b→c→a, e deve retornar `#{#{\\a \\b \\c}}`."
          "Dica: processe cada par um de cada vez, mantendo um conjunto de ciclos parciais. Para cada par, verifique se algum dos nós já pertence a um ciclo parcial."]}
 :function-template (defn find-cycles [input])
 :test-cases
 [{:input (find-cycles "ab ba")
   :output #{#{\a \b}}}
  {:input (find-cycles "da cd fe bc ef ab")
   :output #{#{\a \b \c \d} #{\e \f}}}
  {:input (find-cycles "ab de ha gh fg bc ef cd")
   :output #{#{\a \b \c \d \e \f \g \h}}}]
 :uses #{clojure.string/split reduce conj disj case set}}

;; --- [:solution 0]

(require '[clojure.string :as str])

(defn- step [acc [src dest]]
  (let [[cycle-1 cycle-2 :as matching]
        (->> acc
             (filter (fn [partial-cycle]
                       (or (partial-cycle src)
                           (partial-cycle dest)))))]
    (case (count matching)
      0 (conj acc #{src dest})
      1 (-> acc
            (disj cycle-1)
            (conj (conj cycle-1 src dest)))
      2 (-> acc
            (disj cycle-1 cycle-2)
            (conj (into cycle-1 cycle-2))))))

(defn find-cycles [input]
  (->> (str/split input #" ")
       (map seq)
       (reduce step #{})))
