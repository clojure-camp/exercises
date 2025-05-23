{:title
 {:en-US "modelling tic-tac-toe"
  :pt-BR "modelando jogo da velha"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Devise a data model to represent a game of tic-tac-toe."
          "For example, describe a game whose board currently looks like the below:"
          "`_|X|O`"
          "`_|_|X`"
          "`O|_|_`"
          "In your model, include a history of the moves played."
          "Write a function `moves-played` that returns how many moves have been played."]
  :pt-BR ["Crie um modelo de dados para representar o jogo da velha."
          "Por exemplo, descreva um jogo cujo tabuleiro atualmente se parece com o abaixo:"
          "`_|X|O`"
          "`_|_|X`"
          "`O|_|_`"
          "No seu modelo, inclua um histórico das jogadas feitas."
          "Escreva uma função `moves-played` que retorna quantas jogadas foram feitas."]}
 :uses #{:maps :vectors :strings :numbers count}}

;; --- [:solution 0]

(def game-state
  {:current-player "X"
   :board [nil "X" "O"
           nil nil "X"
           "O" nil nil]
   :history [{:player "X"
              :location 1}
             {:player "O"
              :location 2}
             {:player "X"
              :location 5}
             {:player "O"
              :location 6}]})

(defn moves-played [state]
  (count (state :history)))

(defn moves-played-alt [state]
  (count (remove nil? (state :board))))
