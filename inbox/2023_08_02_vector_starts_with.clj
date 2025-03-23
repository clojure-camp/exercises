(ns exercises.aug2
  (:require [hyperfiddle.rcf :as rcf]))

(rcf/enable!)

(defn starts-with? [big small]
  (= small
     (take (count small) big)))



;; truncate a to length of b, and check equality


;; iterate over both, do they equal
;; exit true if get to end of b
;;    map over both
;;    reduce over b   ;; short circuit w/ reduced
;;    loop

(defn starts-with? [big small]
  (reduce (fn [memo [index item]]
            (if memo
             (= (get big index) item)
             false))
          true
          (map-indexed vector small)))


(defn starts-with? [big small]
  (reduce (fn [_ [index item]]
            (if (= (get big index) item)
              true
              (reduced false)))
          true
          (map-indexed vector small)))

(defn starts-with? [b s]
  (reduce (fn [acc item]
            (let [idx 0]
              (if (= idx (count s))
                 (reduced (= acc s))
                 (do (conj acc item)
                     (inc idx)))))
          [] b))

(defn starts-with? [b s]
  (and
   (>= (count b) (count s))
   (->> (map = b s)
        (every? true?))))

(starts-with? [:foo :foo] [:foo])

(macroexpand-1 '(->> (map = b s)
                     (every? true?)))

(map-indexed vector ["a" "b" "c"])
(map vector [1 2 3] [4 5 6])
(zipmap [1 2 3] [4 5 6])


(rcf/tests
 (starts-with? [:foo :bar 0 :baz]
               [:foo :bar])
 := true

 (starts-with? [:foo]
               [:foo :bar])
 := false

 (starts-with? [:foo :zed]
               [:foo :bar])
 := false


 (starts-with? [:foo :bar]
               [:a :bar])
 := false)



(macroexpand-1 '(->> list
                    (reduce +)
                    (filter even?)))

