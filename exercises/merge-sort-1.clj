{:title
 {:en-US "merge sort"
  :pt-BR "merge sort (ordenação por mistura)"}
 :category :synthesis
 :difficulty :high
 :instructions
 {:en-US ["Implement merge sort."]
  :pt-BR ["Implemente o merge sort."]}
 :function-template (defn merge-sort [coll])
 :test-cases
 [{:input (merge-sort [5 6 3 1 2 4])
   :output [1 2 3 4 5 6]}
  {:input (merge-sort [9 1 2 3 5 7 2 6 4 8])
   :output [1 2 3 4 5 6 7 8 9]}]
 :uses #{loop subvec count / :math-operations recur cond
        compare pos?}}

;; --- [:solution 0]

(defn merge*
  [a b]
  (loop [out []
         a a
         b b]
    (cond
      (empty? a) (into out b)
      (empty? b) (into out a)
      :else
      (let [[a1 & a-rest] a
            [b1 & b-rest] b]
        (if (> a1 b1)
          (recur (conj out b1) a b-rest)
          (recur (conj out a1) b a-rest))))))

(defn merge-sort
  [col]
  (if (= 1 (count col))
    col
    (let [n (quot (count col) 2)]
      (merge* (merge-sort (take n a))
              (merge-sort (drop n a))))))
