{:title
 {:en-US "linear interpolation"
  :pt-BR "interpolação linear"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Say you have some data that goes from 0 to 100, but you need to display it on a chart that's only 50px wide. To draw the chart, you need to be able to transform values from the input range (0 - 100) to the output range (0 - 50), and for that, you'll need to do some linear interpolation."
          "Write a function that takes a number and two ranges and transforms the number from one range to the other."
          "The given ranges shouldn't need to start at 0 and the given value shouldn't have to fall inside the input range."]
  :pt-BR ["Digamos que você tem alguns dados que vão de 0 a 100, mas você precisa exibi-los em um gráfico que tem apenas 50px de largura. Para desenhar o gráfico, você precisa ser capaz de transformar valores do intervalo de entrada (0 - 100) para o intervalo de saída (0 - 50), e para isso, você precisará fazer uma interpolação linear."
          "Escreva uma função que recebe um número e dois intervalos e transforma o número de um intervalo para o outro."
          "Os intervalos dados não precisam começar em 0 e o valor dado não precisa estar dentro do intervalo de entrada."]}
 :function-template (defn transform [x [x0 x1] [y0 y1]])
 :test-cases
[{:input (transform 5 [0 10] [0 100])
  :output 50}
 {:input (transform 7 [0 10] [0 100])
  :output 70}
 {:input (transform 11 [0 10] [0 100])
  :output 110}
 {:input (transform 15 [10 20] [0 100])
  :output 50}]
:related #{"linear-interpolation-higher-order-function"}
 :uses #{let :destructuring :math-operations + * / -}}

;; --- [:solution 0]

(defn transform
  [x [x0 x1] [y0 y1]]
  (let [m (/ (- y1 y0) (- x1 x0))
        b (- y0 (* m x0))]
    (+ (* m x) b)))
