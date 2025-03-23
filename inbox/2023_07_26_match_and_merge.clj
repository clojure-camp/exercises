(ns exercises.meet2023july26
  (:require
   [hyperfiddle.rcf :as rcf]))




;; 1
;;  accumulator map
;; for each item in both lists
;;    store in map based on id, merging maybe
;; reduce
;; {1 {:id 1 :name "Alice" :email "alice@example.com"} ...}


(defn match-and-merge [k coll-a coll-b]
  (->> (concat coll-a coll-b)
       (reduce (fn [acc item]
                 (assoc acc (k item) {}))
               {})))
    ;; TODO


(defn match-and-merge [k coll-a coll-b]
  (vals
   (reduce (fn [acc item]
             (if (contains? acc (k item))
               (update acc (k item) merge item)
               (assoc acc (k item) item)))
           {}
           (concat coll-a coll-b))))

(defn match-and-merge [k coll-a coll-b]
  (vals
   (reduce (fn [acc item]
             (update acc (:id item) merge item))
           {}
           (concat coll-a coll-b))))



;; 2
;; index both
;; {1 {:id 1 :name "Alice"} ...}
;; {1 {:id 1 :email "alice@example.com"} ...}
;; then merge-with merge

(merge-with +
            {:a 2 :b 4}
            {:a 3 :c 4})

;; 3
;; combine the lists, and then group-by :id, vals, and then merge

(defn tee [x] (clojure.pprint/pprint x) x)

(defn match-and-merge [k coll-a coll-b]
  (->> (concat coll-a coll-b)
       (group-by k)
       vals
       (map (fn [[a b]] (merge a b)))))

;;    (map (partial apply merge))
;; 4
;; clojure.set/join xxxxxx

(defn match-and-merge [k coll-a coll-b]
  (clojure.set/join (set coll-a) (set coll-b)))

;; 5
;; merge-with

(match-and-merge :id
                  [{:id 1
                    :name "Alice"}
                   {:id 2
                    :name "Bob"}
                   {:id 3
                    :name "Cathy"}
                   {:id 4
                    :name "Donald"}]
                  [{:id 5
                    :email "evelyn@example.com"}
                   {:id 1
                    :email "alice@example.com"}
                   {:id 9
                    :email "x@example.com"}
                   {:id 3
                    :email "cathy@example.com"}]
                 )

(def first-relation #{{:a 1} {:a 2}})
(def second-relation #{{:a 1} {:a 4}})
(clojure.set/intersection first-relation second-relation)

{a 1} {a 1}
{a 1} {b 2}
{a 2} {a 1}
{a 2} {b 2}

(def animals #{{:name "betsy" :owner "brian" :kind "cow"}
               {:name "jake"  :owner "brian" :kind "horse"}
               {:name "josie" :owner "dawn"  :kind "cow"}})

(def personalities #{{:kind "cow" :personality "stoic"}
                     {:kind "horse" :personality "skittish"}})

(clojure.set/intersection #{:name  :owner :kind}
                          #{:kind :personality})

{:name "betsy" :owner "brian" :kind "cow"}{:kind "cow" :personality "stoic"}
{:name "jake"  :owner "brian" :kind "horse"} {:kind "cow" :personality "stoic"}
{:name "josie" :owner "dawn"  :kind "cow"}{:kind "cow" :personality "stoic"}
{:name "betsy" :owner "brian" :kind "cow"} {:kind "horse" :personality "skittish"}
{:name "jake"  :owner "brian" :kind "horse"}{:kind "horse" :personality "skittish"}
{:name "josie" :owner "dawn"  :kind "cow"}{:kind "horse" :personality "skittish"}


(rcf/tests
 (set (match-and-merge :id
                       [{:id 1
                         :name "Alice"}
                        {:id 2
                         :name "Bob"}
                        {:id 3
                         :name "Cathy"}
                        {:id 4
                         :name "Donald"}]
                       [{:id 5
                         :email "evelyn@example.com"}
                        {:id 1
                         :email "alice@example.com"}
                        {:id 9
                         :email "x@example.com"}
                        {:id 3
                         :email "cathy@example.com"}]))
 :=
 (set
  [{:id 1
    :name "Alice"
    :email "alice@example.com"}
   {:id 2
    :name "Bob"}
   {:id 3
    :name "Cathy"
    :email "cathy@example.com"}
   {:id 4
    :name "Donald"}
   {:id 5
    :email "evelyn@example.com"}
   {:id 9
    :email "x@example.com"}]))


