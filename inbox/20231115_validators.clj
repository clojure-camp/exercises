(ns exercises.20231115
   (:require
    [hyperfiddle.rcf :as rcf]))

(rcf/enable!)

;; input:
;; schema:
;;   {:foo string?
;;    :bar int?}
;; entity
;;  {:foo "hello" :bar 3 :baz 5}
;;
;; true

(defn validate [schema obj]
  (reduce (fn [_acc [schema-key check-fn]]
            (if (check-fn (get obj schema-key))
              true
              (reduced false)))
          true
          schema))


(defn validate [schema obj]
  (loop [s schema]
    (if-let [[schema-key check-fn] (first s)]
      (if (check-fn (obj schema-key))
        (recur (rest s))
        false)
      true)))

(defn validate-with-map [schema obj]
  (every? (fn [[schema-key check-fn]]
           (check-fn (obj schema-key)))
         schema))

(defn validate-with-fn [schema obj]
  (schema obj))

;; v1 - assume flat map (keys and values)
;; for each k/v in schema , run against entity, report first failure
;; reduce, reduced
;; recursively
;; map + filter + first
;;  any? / every? / some?


(defn validatex [schema obj]
  (cond
    (map? schema)
    (validate-with-map schema obj)
    (fn? schema)
    (validate-with-fn schema obj)))


;;;

(defmulti validate (fn [schema _obj]
                     (type schema)))



(defmethod validate clojure.lang.IPersistentMap
  [schema obj]
  (every? (fn [[schema-key sub-schema-or-fn]]
            (validate sub-schema-or-fn (obj schema-key)))
          schema))

(defmethod validate clojure.lang.IFn
   [schema obj]
   (schema obj))

(defmethod validate clojure.lang.IPersistentVector
  [schema obj]
  (every? (first schema) obj))


(prefer-method validate
               clojure.lang.IPersistentMap
               clojure.lang.IFn)

(prefer-method validate
               clojure.lang.IPersistentVector
               clojure.lang.IFn)

(rcf/tests
 (validate {:foo string?
            :bar int?}
           {:foo "hello"
            :bar 3
            :baz 5})
 := true

 (validate {:foo string?
            :bar int?}
           {:foo "hello"
            :bar "3"})
 := false

 ;; part 2
 (validate string? "hello") := true

 (validate int? "hello") := false

;; recursion

(validate {:foo string?
           :bar {:x string?
                 :y int?}}
          {:foo "x"
           :bar {:x "y"
                 :y 3}})
:= true

;; vector
(validate [int?] [1 2 3 4 5]) := true
(validate [int?] [1 2 3 4 5 6]) := true




