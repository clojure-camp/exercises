{:title
 {:en-US "numbers to english"
  :pt-BR "números em inglês"}
 :category :synthesis
 :difficulty :high
 :instructions
 {:en-US ["Write a function to convert a number into the equivalent English string."
          "To simplify things, you can assume a limited domain (e.g. under 10,000)."]
  :pt-BR ["Escreva uma função para converter um número em sua representação em inglês."
          "Para simplificar, você pode assumir um domínio limitado (por exemplo, abaixo de 10.000)."]}
 :function-template  (defn number->english [n])
 :test-cases [{:input (number->english 120)
               :output "one hundred and twenty"}
              {:input (number->english 4321)
               :output "four thousand three hundred and twenty one"}]
 :uses #{cond :math-operations :java-interop :maps :recursion}
 :source "https://exercism.io/tracks/clojure/exercises/say"}

;; --- [:solution 0]

(def number->name
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   10 "ten"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})

(def powers-of-ten
  {1 "ten"
   2 "hundred"
   3 "thousand"})

(defn number->english
  [n]
  (cond
    (<= n 20) (number->name n)
    (<= n 100) (str (number->english (* 10 (quot n 10)))
                    " "
                    (number->english (rem n 10)))
    :else
    (let [pow-ten (-> n Math/log10 Math/floor int)
          exp (Math/pow 10 pow-ten)
          most-sig (int (quot n exp))
          rest (int (rem n exp))]
      (str (number->english most-sig)
           " "
           (powers-of-ten pow-ten)
           " "
           (when (< rest 100)
             "and ")
           (number->english rest)))))
