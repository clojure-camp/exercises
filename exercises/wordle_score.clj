{:title
 {:en-US "wordle score"
  :pt-BR "pontuação do wordle"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["Implement a function `score` that takes a 5-letter `guess` and a 5-letter `solution` and returns a vector of 5 scoring values, one per letter:"
          "- `:green` — correct letter, correct position"
          "- `:yellow` — correct letter, wrong position (and not already accounted for)"
          "- `nil` — letter not in solution, or already used up"
          "Duplicate letters should be handled correctly: if the solution has only one `e`, a guess with two `e`s should score at most one green or yellow."]
  :pt-BR ["Implemente uma função `score` que recebe um `guess` de 5 letras e uma `solution` de 5 letras e retorna um vetor de 5 valores de pontuação, um por letra:"
          "- `:green` — letra correta, posição correta"
          "- `:yellow` — letra correta, posição errada (e ainda não contabilizada)"
          "- `nil` — letra não está na solução, ou já foi usada"
          "Letras duplicadas devem ser tratadas corretamente: se a solução tem apenas um `e`, um chute com dois `e`s deve pontuar no máximo um green ou yellow."]}
 :function-template (defn score [guess solution])
 :test-cases
 [{:input (score "found" "conch")
   :output [nil :green nil :yellow nil]}
  {:input (score "worry" "rebel")
   :output [nil nil :yellow nil nil]}
  {:input (score "e12ee" "ee345")
   :output [:green nil nil :yellow nil]}]
 :uses #{reduce frequencies vec cond update}}

;; --- [:solution 0]

(defn score [guess solution]
  (let [guess-v (vec guess)
        solution-v (vec solution)]
    (->> (range 5)
         (reduce
          (fn [acc index]
            (cond
              (= (solution-v index) (guess-v index))
              (-> acc
                  (update-in [:frequencies (guess-v index)] dec)
                  (update :output conj :green))
              (pos? ((:frequencies acc) (guess-v index) 0))
              (-> acc
                  (update-in [:frequencies (guess-v index)] dec)
                  (update :output conj :yellow))
              :else
              (update acc :output conj nil)))
          {:frequencies (frequencies solution)
           :output []})
         :output)))
