{:title
 {:en-US "tic-tac-toe winner"
  :pt-BR "vencedor do jogo da velha"}
 :category :starter
 :difficulty :mid
 :instructions
 {:en-US ["Given a structure representing a tic-tac-toe game (see tests), return the winner, `:tie` in the case of a tie, or `nil` if there is no winner yet."]
  :pt-BR ["Dada uma estrutura representando um jogo da velha (veja os testes), retorne o vencedor, `:tie` no caso de empate, ou `nil` se ainda não houver vencedor."]}
 :function-template (defn winner [board])
 :test-cases
[{:input (winner [["X" nil "O"] [nil "X" "O"] [nil nil "X"]])
  :output "X"}
 {:input (winner [["X" nil "O"] [nil "X" "O"] [nil nil "O"]])
  :output "O"}
 {:input (winner [["X" "X" "O"] ["O" "O" "O"] ["X" "X" nil]])
  :output "O"}
 {:input (winner [["X" "X" "O"] ["O" "O" "X"] ["X" "X" "O"]])
  :output :tie}
 {:input (winner [[nil nil nil] [nil "X" "O"] [nil "X" "O"]])
  :output nil}]
:uses #{or filter first apply = map vector :destructuring}}

;; --- [:solution 0]

(defn winner-row
  [board]
  (first (first (filter (fn [row] (apply = row)) board))))

(defn transpose
  [board]
  (apply map vector board))

(defn winner-column
  [board]
  (->> board transpose winner-row))

(defn winner-diagonal-r-to-l
  [[[_ _ a] [_ b _] [c _ _]]]
  (when (= a b c) a))

(defn winner-diagonal-l-to-r
  [[[a _ _] [_ b _] [_ _ c]]]
  (when (= a b c) a))

(defn winner-diagonal
  [board]
  (or (winner-diagonal-l-to-r board)
      (winner-diagonal-r-to-l board)))

(defn winner
  [board]
  (if-let [w (or (winner-row board)
                 (winner-column board)
                 (winner-diagonal board))]
    w
    (if (every? (fn [row] (every? some? row)) board)
      :tie
      nil)))
