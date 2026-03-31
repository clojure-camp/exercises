(ns exercises.20241219
  (:require
   [clojure.string :as str]
   [hyperfiddle.rcf :as rcf]))

(rcf/enable!)

;; we have a bunch of connections between nodes
;; they will form 1 or more cycles
;; every node points to just one other node
;; every node is pointed to by just one other node
;; (all nodes are part of only one cycle)
;; (a node does not point to itself)
;; cycles include minimum 2 nodes

;; ex.
;; a -> b
;; b -> c
;; c -> d
;; d -> a
;; e -> f
;; f -> e

;; #{}  => #{ #{a b} } => #{ #{ a b }  #{ c d }}  => #{ #{ a b c d }}

;; for each pair [a b]
;;   find existing partial-cycles where either of the values of that pair are in
;;   1) if there is one matching set => add to it
;;   2) if there are two matching set => merge the sets
;;   3) if there are no matching sets => create new set

;; output
;; #{ #{a b c} #{d e}}

(defn step [acc [src dest]]
  (let [[cycle-1 cycle-2 :as matching-partial-cycles]
        (->> acc
             (filter (fn [partial-cycle]
                       (or (partial-cycle src)
                           (partial-cycle dest)))))]
    (case (count matching-partial-cycles)
      0 (conj acc #{src dest})
      1 (-> acc
            (disj set cycle-1)
            (conj (conj cycle-1 src dest)))
      2 (-> acc
            (disj cycle-1 cycle-2)
            (conj (apply conj cycle-1 cycle-2))))))

(defn find-cycles [input]
  (->> (str/split input #" ")
       (map seq)
        ;; '('(a b) '(c d) ...)
       (reduce step #{})))

(rcf/tests
 "adding new"
 (step #{} [\a \b])
 := #{#{\a \b}}

 "adding to existing"
 (step #{#{\a \b} #{\d \e}} [\b \c])
 := #{#{\a \b \c } #{\d \e}}

 "adding to existing"
 (step #{#{\a \b} #{\d \e}} [\f \a])
 := #{#{\a \b \f} #{\d \e}}

 "merging 2 cycles"
 (step #{#{\a \b} #{\d \e}} [\b \d])
 := #{#{\a \b \d \e}}

 "test end to end"
 (find-cycles "da cd fe bc ef ab")
 := #{#{\a \b \c \d} #{\e \f}}

 (find-cycles "ab de ha gh fg bc ef cd")
 := #{#{\a \b \c \d \e \f \g \h}})

;; homework:
;; instead of returning cycles as sets, return vectors which represent the nodes in the correct order
;; ex. #{[\a \b \c \d] [\e \f]}

