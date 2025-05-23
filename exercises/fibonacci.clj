{:title
 {:en-US "fibonacci"
  :pt-BR "fibonacci"}
 :instructions
 {:en-US ["Write a recursive function to calculate the nth Fibonacci number."
          "Use `time` to measure its performance, then use `memoize` & compare."]
  :pt-BR ["Escreva uma função recursiva para calcular o enésimo número de Fibonacci."
          "Use `time` para medir a performance, depois use `memoize` e compare."]}
 :category :synthesis
 :difficulty :low
 :teaches #{memoize}
 :uses #{:recursion time with-redefs}}

;; --- [:solution 0]

(defn fib
  [n]
  (cond
    (zero? n) 1
    (= 1 n) 1
    :else (+ (fib (- n 1))
             (fib (- n 2)))))

#_(time (fib 30))

#_(let [memo-fib (memoize fib)]
    (with-redefs [fib memo-fib]
      (time (fib 30))))
