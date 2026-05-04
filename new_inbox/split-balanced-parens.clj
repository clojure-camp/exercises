{:title
 {:en-US "split balanced parentheses"
  :pt-BR "dividir parênteses balanceados"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["Write a function `split-balanced-parens` that takes a string containing one or more groups of balanced parentheses separated by spaces, and returns a vector of strings, one per group (without extra spaces)."
          "For example, `\"( ) (( ))\"` should return `[\"()\" \"(())\"]`."]
  :pt-BR ["Escreva uma função `split-balanced-parens` que recebe uma string contendo um ou mais grupos de parênteses balanceados separados por espaços, e retorna um vetor de strings, um por grupo (sem espaços extras)."
          "Por exemplo, `\"( ) (( ))\"` deve retornar `[\"()\" \"(())\"]`."]}
 :function-template (defn split-balanced-parens [s])
 :test-cases
 [{:input (split-balanced-parens "( )")
   :output ["()"]}
  {:input (split-balanced-parens "( ) (( ))")
   :output ["()" "(())"]}
  {:input (split-balanced-parens "( ) (( )) (( ) ( ) )")
   :output ["()" "(())" "(()())"]}
  {:input (split-balanced-parens "(( (()) ))")
   :output ["(((())))"]}]
 :uses #{loop recur conj apply str case}}

;; --- [:solution 0]

(defn split-balanced-parens  [s]
  (->> s
       (reductions
        (fn [memo s]
          (case s
            \space memo
            \( (update memo :count inc)
            \) (update memo :count dec)))
        {:out []
         :count 0})))

;; --- [:solution 1]

(defn split-balanced-parens [s]
  (loop [chars s
         depth 0
         current []
         result []]
    (if-let [c (first chars)]
      (case c
        \space (recur (rest chars) depth current result)
        \( (recur (rest chars) (inc depth) (conj current c) result)
        \) (let [new-current (conj current c)
                 new-depth (dec depth)]
             (if (zero? new-depth)
               (recur (rest chars) 0 [] (conj result (apply str new-current)))
               (recur (rest chars) new-depth new-current result))))
      result)))
