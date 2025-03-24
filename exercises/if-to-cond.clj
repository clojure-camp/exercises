{:title
 {:en-US "refactor ifs"
  :pt-BR "refatore ifs"}
 :difficulty :low
 :category :learning-functions
 :uses #{cond}
 :instructions
 {:en-US ["Refactor the below code to use a single `cond` instead of multiple `if`s."
          "Also, consider re-ordering the conditionals to simplify the logic."
          (defn process-value
            [value]
            (if (and (number? value)
                     (> value 10))
              :pretty-big
              (if (and (number? value)
                       (< value 0))
                :negative
                (if (and (number? value) (zero? value))
                  :zero
                  (if (number? value)
                    :small-number
                    (if (string? value)
                      :a-string
                      :something-else))))))]
  :pt-BR ["Refatore o código abaixo para usar um único `cond` ao invés de múltiplos `if`s."
          "Também considere reordenar as condicionais para simplificar a lógica."
          (defn process-value
            [value]
            (if (and (number? value)
                     (> value 10))
              :pretty-big
              (if (and (number? value)
                       (< value 0))
                :negative
                (if (and (number? value) (zero? value))
                  :zero
                  (if (number? value)
                    :small-number
                    (if (string? value)
                      :a-string
                      :something-else))))))]}}

;; --- [:solution 0]

(defn process-value
  [value]
  (cond
    (string? value) :a-string
    (not (number? value)) :something-else
    (zero? value) :zero
    (> value 10) :pretty-big
    (< value 0) :negative
    :else :small-number))
