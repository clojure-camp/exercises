{:title
 {:en-US "measuring sort comparisons"
  :pt-BR "medindo comparações em ordenação"}
 :category :synthesis
 :difficulty :mid
 :instructions
 {:en-US ["Explore how many comparisons it typically takes to sort a list."
          "Step 1: Create a function `sort-count`, that, given a list, returns the number of comparisons performed when sorting that list."
          "Step 2: Create a function `random-list` that returns a shuffled a list of values from 0 to N."
          "Step 3: Create a function `sort-frequencies` that uses the above two functions to run a number of trials, and then reports the relative frequencies of the number of comparisons needed."
          "For example, `(sort-frequencies 8 1000)` (1000 trials of 8 item list) gave the result:"
          [[12 0.001] [13 0.007] [14 0.026] [15 0.15] [16 0.342] [17 0.361] [18 0.113]]]
  :pt-BR ["Explore quantas comparações são necessárias para ordenar uma lista."
          "Passo 1: Crie uma função `sort-count`, que, dada uma lista, retorna o número de comparações realizadas ao ordenar essa lista."
          "Passo 2: Crie uma função `random-list` que retorna uma lista embaralhada de valores de 0 a N."
          "Passo 3: Crie uma função `sort-frequencies` que usa as duas funções acima para executar um número de testes e, em seguida, relata as frequências relativas do número de comparações necessárias."
          "Por exemplo, `(sort-frequencies 8 1000)` (1000 testes de uma lista de 8 itens) deu o resultado:"
          [[12 0.001] [13 0.007] [14 0.026] [15 0.15] [16 0.342] [17 0.361] [18 0.113]]]}
 :uses #{:working-with-atoms atom swap! compare sort shuffle frequencies :destructuring ->> double count :math-operations /}}

;; --- [:solution 0]

(defn sort-count [values]
  (let [comparisons (atom 0)]
    (sort (fn [x y]
            (swap! comparisons inc)
            (compare x y)) values)
    @comparisons))

(defn shuffled-list [n]
  (shuffle (range n)))

(defn sort-frequencies [list-length trial-count]
  (let [results (atom [])]
    (dotimes [n trial-count]
      (swap! results conj (sort-count (shuffled-list list-length))))
    (->> (frequencies @results)
         sort
         (map (fn [[comparisons count]]
                [comparisons (double (/ count trial-count))])))))
