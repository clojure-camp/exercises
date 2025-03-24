{:title
 {:en-US "poker-deal"
  :pt-BR "distribuição de cartas"}
 :category :synthesis
 :uses #{partition take shuffle}
 :difficulty :low
 :related #{"poker-rank"}
 :instructions
 {:en-US ["Create a function that deals a certain number of cards to some given number of players."
          "It's up to you to decide how to represent the cards."]
  :pt-BR ["Crie uma função que distribui um certo número de cartas para um dado número de jogadores."
          "Cabe a você decidir como representar as cartas."]}}

;; --- [:solution 0]

(def suits [:hearts :spades :clubs :diamonds])

(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])

(def deck
  (for [suit suits
        rank ranks]
    {:card/suit suit
     :card/rank rank}))

(defn deal [deck player-count card-count]
  (->> deck
       (partition card-count)
       (take player-count)))

;; This solution assumes that in practice, we would pass deal a shuffle deck, ie.:
;; (deal (shuffle deck) 3 5)
;; This makes deal easier to test, because it is pure.
;; We could have had deal do the shuffling, but then it would not be pure.

(is (= [[1 2 3 4 5]
        [6 7 8 9 10]]
       (deal [1 2 3 4 5 6 7 8 9 10] 2 5)))
