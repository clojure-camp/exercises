{:title
 {:en-US "the almighty reduce"
  :pt-BR "o todo-poderoso reduce"}
 :difficulty :mid
 :category :learning-functions
 :instructions
 {:en-US ["Refactor the following code to use a single `reduce` instead of a `map` and `filter`:"
          (defn long-lengths
            [strings]
            (map count (filter (fn [string] (< 5 (count string))) strings)))]
  :pt-BR ["Refatore o seguinte código para usar um único `reduce` ao invés de um `map` e `filter`:"
          (defn long-lengths
            [strings]
            (map count (filter (fn [string] (< 5 (count string))) strings)))]}
 :test-cases
[{:input (long-lengths ["foo" "aoeusnth" "q" "aoeus" "abcdef" "1234567"])
  :output [8 6 7]}]
 :uses #{conj count < map filter}
 :teaches #{reduce}}

;; --- [:solution 0]

(defn long-lengths
  [strings]
  (reduce (fn [counts string]
            (let [length (count string)]
              (if (< 5 length)
                (conj counts length)
                counts)))
          [] strings))
