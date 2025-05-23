{:title
 {:en-US "trie: convert a trie to its compact form (part 4)"}
 :category :synthesis
 :difficulty :mid
 :related #{"trie-create-from-words"
            "trie-list-all-words"
            "trie-autocomplete"
            "trie-compact-autocomplete"}
 :instructions
 {:en-US ["Convert a trie to its compact form"]}
 :teaches #{map cond :recursion :trie-data-structure :destructuring}
 :uses #{:recursion}}

;; --- [:function-template]
(defn compact [trie])

;; --- [:solution 0]

;; From previous parts

(require '[clojure.string :as string])
(defn trie
  "Create a trie from a list of words."
  [words]
  (reduce (fn [memo word]
            (assoc-in memo (conj (string/split word #"") "") nil))
          {} words))

(defn words
  "Return the list of all words stored in a trie (recursive)."
  [trie]
  (set (mapcat (fn [letter]
                 (if-let [subtrie (trie letter)]
                   (map (partial str letter) (words subtrie))
                   [""]))
               (keys trie))))

(defn autocomplete
  "Given a trie and a prefix, return all words in trie with that prefix"
  [trie prefix]
  (set (map (partial str prefix)
            (words (get-in trie (string/split prefix #""))))))

;; Solution

(defn compact
  "Convert a trie to its compact form"
  [trie]
  (into {}
        (map (fn [[k v]]
               (cond
                 (= [k v] ["" nil])
                 [k v]
                 (= v {"" nil})
                 [k v]
                 :else
                 (let [v' (compact v)]
                   (if (= 1 (count v'))
                     (let [[subk subv] (first v')]
                       [(str k subk) subv])
                     [k v']))))
             trie)))

;; --- test-cases

(require '[clojure.test :refer [is]])

(is (= {"" "nil"}
       (compact {"" nil})))

(is (= {"a" {"" nil}}
       (compact {"a" {"" nil}})))

(is (= {"a" {"" nil}
        "b" {"" nil}}
       (compact {"a" {"" nil}
                 "b" {"" nil}})))

(is (= {"a" {"" nil
             "b" {"" nil}}}
       (compact {"a" {"" nil
                      "b" {"" nil}}})))

(is (= {"ba" {"" nil}}
       (compact {"b" {"a" {"" nil}}})))

(is (= {"dcba" {"" nil}}
       (compact {"d" {"c" {"b" {"a" {"" nil}}}}})))

(is (= {"b" {"a" {"" nil}
             "c" {"" nil}}}
       (compact {"b" {"a" {"" nil}
                      "c" {"" nil}}})))

(is (= {"ba" {"d" {"" nil}
              "c" {"" nil}}}
       (compact {"b" {"a" {"d" {"" nil}
                           "c" {"" nil}}}})))

(is (= {"f" {"" nil}
        "he"
        {"" nil
         "alth" {"" nil}
         "l"
         {"lo" {"" nil}
          "p" {"" nil}}}}
       (compact (trie ["hello" "help" "health" "he" "f"]))))
