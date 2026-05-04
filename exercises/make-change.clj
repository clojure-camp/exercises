{:title
 {:en-US "make change"
  :pt-BR "troco"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["Write a function `make-change` that takes an `amount` and a set of coin denominations `coin-set`."
          "Return a map of `{denomination count}` representing the minimum number of coins needed to make exact change for `amount`."
          "Assume that exact change can always be made with the given denominations."]
  :pt-BR ["Escreva uma função `make-change` que recebe um `amount` e um conjunto de denominações de moedas `coin-set`."
          "Retorne um mapa de `{denominação quantidade}` representando o número mínimo de moedas necessárias para fazer o troco exato para `amount`."
          "Assuma que o troco exato sempre pode ser feito com as denominações fornecidas."]}
 :function-template (defn make-change [amount coin-set])
 :test-cases
 [{:input (make-change 6 #{1 3 4})
   :output {3 2}}
  {:input (make-change 17 #{4 9 14 15 16 18 25})
   :output {9 1 4 2}}
  {:input (make-change 10 #{1 5 10})
   :output {10 1}}
  {:input (make-change 11 #{1 5 10})
   :output {10 1 1 1}}
  {:input (make-change 157 #{1 5 10 25})
   :output {25 6 5 1 1 2}}
  {:input (make-change 10 #{7 6 4 1})
   :output {6 1 4 1}}]
 :uses #{:recursion reduce keep frequencies sort-by apply min-key}}

;; --- [:solution 0]

(defn make-change [amount coin-set]
  (let [find-change
        (fn [f remaining coins-used]
          (cond
            (zero? remaining) coins-used
            (neg? remaining) nil
            :else
            (->> coin-set
                 (keep (fn [coin]
                         (f f
                            (- remaining coin)
                            (update coins-used coin (fnil inc 0)))))
                 (apply min-key #(apply + (vals %))))))
        memoized (memoize find-change)]
    (memoized memoized amount {})))

;; --- [:solution 1]

;; (greedy, not optimal)

(defn find-change
  [remaining coins-used coin-set]
  (cond
    (zero? remaining) coins-used
    (neg? remaining) nil
    :else
    (->> coin-set
         (keep (fn [coin]
                 (find-change
                  (- remaining coin)
                  (update coins-used coin (fnil inc 0))
                  coin-set)))
         first)))

(defn make-change [amount coin-set]
  (find-change amount {} (reverse (sort coin-set))))


