

(defn- join
  "Lazily concatenates a collection of collections into a flat sequence,
  because Clojure's `apply concat` is insufficiently lazy."
  [colls]
  (lazy-seq
   (when-let [s (seq colls)]
     (concat (first s) (join (rest s))))))

(defn- all-different?
  "Annoyingly, the built-in distinct? doesn't handle 0 args, so we need
  to write our own version that considers the empty-list to be distinct"
  [s]
  (if (seq s)
    (apply distinct? s)
    true))

(defn- index-combinations
  [n cnt]
  (lazy-seq
    (let [c (vec (cons nil (for [j (range 1 (inc n))] (+ j cnt (- (inc n)))))),
          iter-comb
          (fn iter-comb [c j]
            (if (> j n) nil
              (let [c (assoc c j (dec (c j)))]
                (if (< (c j) j) [c (inc j)]
                  (loop [c c, j j]
                    (if (= j 1) [c j]
                      (recur (assoc c (dec j) (dec (c j))) (dec j)))))))),
          step
          (fn step [c j]
            (cons (rseq (subvec c 1 (inc n)))
                  (lazy-seq (let [next-step (iter-comb c j)]
                              (when next-step (step (next-step 0) (next-step 1)))))))]
      (step c 1))))

;; Helper function for bounded-distributions
(defn- distribute [m index total distribution already-distributed]
  (loop [distribution distribution
         index index
         already-distributed already-distributed]
    (if (>= index (count m)) nil
      (let [quantity-to-distribute (- total already-distributed)
            mi (m index)]
        (if (<= quantity-to-distribute mi)
          (conj distribution [index quantity-to-distribute total])
          (recur (conj distribution [index mi (+ already-distributed mi)])
                 (inc index)
                 (+ already-distributed mi)))))))

;; Helper function for bounded-distributions
(defn- next-distribution [m total distribution]
  (let [[index this-bucket this-and-to-the-left] (peek distribution)]
    (cond
      (< index (dec (count m)))
      (if (= this-bucket 1)
        (conj (pop distribution) [(inc index) 1 this-and-to-the-left])
        (conj (pop distribution)
              [index (dec this-bucket) (dec this-and-to-the-left)]
              [(inc index) 1 this-and-to-the-left])),
      ; so we have stuff in the last bucket
      (= this-bucket total) nil
      :else
      (loop [distribution (pop distribution)],
        (let
          [[index this-bucket this-and-to-the-left] (peek distribution),
           distribution (if (= this-bucket 1)
                          (pop distribution)
                          (conj (pop distribution)
                                [index (dec this-bucket) (dec this-and-to-the-left)]))],
          (cond
            (<= (- total (dec this-and-to-the-left)) (apply + (subvec m (inc index))))
            (distribute m (inc index) total distribution (dec this-and-to-the-left)),

            (seq distribution) (recur distribution)
            :else nil))))))

;; Helper function for multi-comb
(defn- bounded-distributions
  [m t]
  (let [step
        (fn step [distribution]
          (cons distribution
                (lazy-seq (when-let [next-step (next-distribution m t distribution)]
                            (step next-step)))))]
    (step (distribute m 0 t [] 0))))

;; Combinations of multisets
;; The algorithm in Knuth generates in the wrong order, so this is a new algorithm
(defn- multi-comb
  "Handles the case when you want the combinations of a list with duplicate items."
  [l t]
  (let [f (frequencies l),
        v (vec (distinct l)),
        domain (range (count v))
        m (vec (for [i domain] (f (v i))))
        qs (bounded-distributions m t)]
    (for [q qs]
      (join
       (for [[index this-bucket _] q]
         (repeat this-bucket (v index)))))))

(defn combinations
  "All the unique ways of taking t different elements from items"
  [items t]
  (let [v-items (vec (reverse items))]
    (if (zero? t) (list ())
      (let [cnt (count items)]
        (cond (> t cnt) nil
              (= t 1) (for [item (distinct items)] (list item))
              (all-different? items) (if (= t cnt)
                                        (list (seq items))
                                        (map #(map v-items %) (index-combinations t cnt))),
              :else (multi-comb items t))))))


;;;

(def cards
  (for [a [:red :green :purple]
        b [1 2 3]
        c [:diamond :pill :squiggle]]
    [a b c]))

#_(count (combinations cards 3)) ;; 2925 = 27*26*25/3*2*1

#_(defn set? [hand]
  (->> [0 1 2]
       (every? (fn [i]
                 (let [c (count (set (map #(get % i) hand)))]
                   (or (= c 1) (= c 3)))))))

(defn set? [hand]
  (->> [0 1 2]
       (every? (fn [i]
                 (let [c (count (set (map #(get % i) hand)))]
                   (or (= c 1) (= c 3)))))))

(->> (combinations cards 3)
     (filter set?)
     count)

;; 2925 possible combinations

;; 117 ways to make sets

;; 117/2925 = 4%



;;; v3 - must all be different


(defn set? [hand]
  (->> [0 1 2]
       (every? (fn [i]
                 (let [c (count (set (map #(get % i) hand)))]
                   (= c 3))))))

(->> (combinations cards 3)
     (filter set?)
     count)

;; 36

;; 36/2925 = 1.2%


