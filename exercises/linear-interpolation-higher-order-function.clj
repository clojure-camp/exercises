{:title
 {:en-US "linear interpolation (higher-order-function)"
  :pt-BR "interpolação linear (função de ordem superior)"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Like before (see related exercise), but this time, instead of having to pass the input and output ranges each time, create a function that will return an interpolation function that can be reused."
          "In other words: create a function `make-interpolator`, which, given two ranges, returns a function; this generated function, when given a single value, returns the interpolated result."]
  :pt-BR ["Como antes (veja exercício relacionado), mas desta vez, ao invés de ter que passar os intervalos de entrada e saída cada vez, crie uma função que retornará uma função de interpolação que pode ser reutilizada."
          "Em outras palavras: crie uma função `make-interpolator`, que, dadas duas faixas, retorna uma função; esta função gerada, quando dada um único valor, retorna o resultado interpolado."]}
 :related #{"linear-interpolation"}
 :function-template (defn make-interpolator [[x0 x1] [y0 y1]])
 :test-cases
 [{:input nil
   :output (make-interpolator [0 10] [0 100])}
  {:input nil
   :output (make-interpolator [10 20] [0 100])}]
 :uses #{let :destructuring :math-operations :closure :higher-order-functions + * - /}}

;; --- [:solution 0]

(defn make-interpolator [[x0 x1] [y0 y1]]
  (let [m (/ (- y1 y0)
             (- x1 x0))
        b (- y0 (* m x0))]
    (fn [x]
      (+ (* m x) b))))
