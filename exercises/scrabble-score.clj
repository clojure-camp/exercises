{:title
 {:en-US "scrabble score"
  :pt-BR "pontuação do scrabble (jogo)"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Implement a function `score` that calculates the scrabble score for a given word."
          "Use google to find out how much each letter is worth."]
  :pt-BR ["Implemente uma função `score` que calcula a pontuação do scrabble para uma palavra dada."
          "Use o google para descobrir quanto cada letra vale."]}
 :function-template (defn score [s])
 :test-cases
[{:input (score "hello")
  :output 8}
 {:input (score "question")
  :output 17}
 {:input (score "quizzed")
  :output 35}]
 :uses #{map apply +}
 :source "https://exercism.io/tracks/clojure/exercises/scrabble-score"}

;; --- [:solution 0]

(def points
  {\a 1 \b  3 \c 3 \d 2 \e 1
   \f 4 \g  2 \h 4 \i 1 \j 8
   \k 5 \l  1 \m 3 \n 1 \o 1
   \p 3 \q 10 \r 1 \s 1 \t 1
   \u 1 \v  4 \w 4 \x 8 \y 4
   \z 10})

(defn score [s]
  (->> s
       (map points)
       (apply +)))
