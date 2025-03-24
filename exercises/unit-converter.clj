{:title
 {:en-US "unit converter"
  :pt-BR "conversor de unidades"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Write a function that can convert between many different types of units."]
  :pt-BR ["Escreva uma função que pode converter entre muitos tipos diferentes de unidades."]}
 :function-template (defn convert [num from-unit to-unit])
 :test-cases [{:input (convert 220 :lb :kg)
               :output 100}
              {:input (convert 20.45 :kg :lb)
               :output 45}
              {:input (convert 0 :fahrenheit :celsius)
               :output -18}
              {:input (convert -40 :celsius :fahrenheit)
               :output -40}
              {:input (convert 15 :meters :feet)
               :output 49}
              {:input (convert 15 :meters :inches)
               :output 590}]
 :uses #{update :math-operations :maps fn}}

;; --- [:solution 0]

(def conv-fn
   {:kg {:lb (fn [k] (* k 2.2))}
    :lb {:kg (fn [l] (/ l 2.2))}
    :celsius {:fahrenheit (fn [c] (+ (* 1.8 c) 32))}
    :fahrenheit {:celsius (fn [f] (/ (- f 32) 1.8))}
    :meters {:feet (fn [m] (* 3.28 m))
             :inches (fn [m] (* 12 3.28 m))}
    :feet {:meters (fn [f] (/ 3.28 f))
           :inches (fn [f] (* 12 f))}})

(defn convert
  [num from-unit to-unit]
  (-> ((get-in conv-fn [from-unit to-unit]) num)
      (Math/round)))
