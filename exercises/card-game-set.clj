{:title
 {:en-US "card game SET"
  :pt-BR "jogo de cartas SET"}
 :difficulty :mid
 :category :synthesis
 :instructions
 {:en-US ["In the card game [SET](https://en.wikipedia.org/wiki/Set_(card_game)), each card has three attributes: color (`:red`, `:green`, `:purple`), count (`1`, `2`, `3`), and shape (`:diamond`, `:pill`, `:squiggle`)."
          "Cards are represented as vectors: `[color count shape]`."
          "A hand of 3 cards is a valid SET if, for **each** attribute, the three cards either all have the **same** value, or all have **different** values."
          "Write a function `set?` that takes a vector of 3 cards and returns `true` if they form a valid SET."]
  :pt-BR ["No jogo de cartas [SET](https://en.wikipedia.org/wiki/Set_(card_game)), cada carta tem três atributos: cor (`:red`, `:green`, `:purple`), contagem (`1`, `2`, `3`) e forma (`:diamond`, `:pill`, `:squiggle`)."
          "As cartas são representadas como vetores: `[cor contagem forma]`."
          "Uma mão de 3 cartas é um SET válido se, para **cada** atributo, as três cartas têm o **mesmo** valor ou todas têm valores **diferentes**."
          "Escreva uma função `set?` que recebe um vetor de 3 cartas e retorna `true` se elas formam um SET válido."]}
 :function-template (defn set? [hand])
 :test-cases
 [{:input (set? [[:red 1 :diamond] [:red 2 :diamond] [:red 3 :diamond]])
   :output true}
  {:input (set? [[:red 1 :diamond] [:green 2 :pill] [:purple 3 :squiggle]])
   :output true}
  {:input (set? [[:red 1 :diamond] [:red 2 :pill] [:green 3 :squiggle]])
   :output false}
  {:input (set? [[:red 1 :diamond] [:red 1 :diamond] [:red 1 :diamond]])
   :output true}
  {:input (set? [[:red 1 :diamond] [:green 1 :diamond] [:green 1 :diamond]])
   :output false}]
 :uses #{every? map get count set or =}
 :source "https://en.wikipedia.org/wiki/Set_(card_game)"}

;; --- [:solution 0]

(defn set? [hand]
  (->> [0 1 2]
       (every? (fn [attr-index]
                 (let [distinct-count (count (clojure.core/set (map #(get % attr-index) hand)))]
                   (or (= distinct-count 1)
                       (= distinct-count 3)))))))
