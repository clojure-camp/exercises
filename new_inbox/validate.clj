{:title
 {:en-US "schema validator"
  :pt-BR "validador de esquema"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["Write a function `validate` that takes a schema and a value, and returns `true` if the value matches the schema."
          "Support three schema types:"
          "- A **function**: the value must satisfy the predicate (e.g. `string?`, `int?`)"
          "- A **map**: each key in the schema maps to a sub-schema; the value must be a map where each key satisfies the corresponding sub-schema (recursively)"
          "- A **vector** `[pred]`: the value must be a collection where every element satisfies `pred`"]
  :pt-BR ["Escreva uma função `validate` que recebe um esquema e um valor, e retorna `true` se o valor corresponde ao esquema."
          "Suporte três tipos de esquema:"
          "- Uma **função**: o valor deve satisfazer o predicado (ex: `string?`, `int?`)"
          "- Um **mapa**: cada chave no esquema mapeia para um sub-esquema; o valor deve ser um mapa onde cada chave satisfaz o sub-esquema correspondente (recursivamente)"
          "- Um **vetor** `[pred]`: o valor deve ser uma coleção onde cada elemento satisfaz `pred`"]}
 :function-template (defn validate [schema value])
 :test-cases
 [{:input (validate string? "hello")
   :output true}
  {:input (validate int? "hello")
   :output false}
  {:input (validate {:foo string? :bar int?}
                    {:foo "hello" :bar 3 :baz 5})
   :output true}
  {:input (validate {:foo string? :bar int?}
                    {:foo "hello" :bar "3"})
   :output false}
  {:input (validate {:foo string?
                     :bar {:x string? :y int?}}
                    {:foo "x" :bar {:x "y" :y 3}})
   :output true}
  {:input (validate [int?] [1 2 3 4 5])
   :output true}
  {:input (validate [int?] [1 2 "three"])
   :output false}]
 :uses #{every? map? fn? vector? defmulti defmethod}}

;; --- [:solution 0]

(defmulti validate (fn [schema _value] (type schema)))

(defmethod validate clojure.lang.IPersistentMap
  [schema value]
  (every? (fn [[schema-key sub-schema]]
            (validate sub-schema (get value schema-key)))
          schema))

(defmethod validate clojure.lang.IFn
  [schema value]
  (schema value))

(defmethod validate clojure.lang.IPersistentVector
  [schema value]
  (every? (first schema) value))

(prefer-method validate clojure.lang.IPersistentMap clojure.lang.IFn)
(prefer-method validate clojure.lang.IPersistentVector clojure.lang.IFn)
