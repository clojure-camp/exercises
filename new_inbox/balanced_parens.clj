;; given a string containing multiple
;; groups of nested parens, seperate into seperate strings
;; of balanced parens

(defn split-paren-groups [s]
  (->> s
       (reductions
        (fn [memo s]
           (case s
                  \space memo
                  \( (update memo :count inc)
                  \) (update memo :count dec)))
        {:out []
         :count 0})))

;; TODO split


(rcf/tests
 (split-paren-groups "( ) (( )) (( ) ( ) )")
 := ["()" "(())" "(()())"])
