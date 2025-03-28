{:title
 {:en-US "trie: autocomplete - return all words in trie with prefix (part 3)"}
 :category :synthesis
 :difficulty :low
 :related #{"trie-create-from-words"
            "trie-list-all-words"
            "trie-compact"
            "trie-compact-autocomplete"}
 :instructions
 {:en-US ["Given a trie and a prefix, return all words in trie with that prefix."
          "(autocomplete (trie [\"hello\" \"help\" \"health\" \"he\" \"f\"])
                        \"he\")"
          "should return:  #{\"hello\" \"health\" \"help\" \"he\"}"]}
 :teaches #{mapcat set map partial str keys :recursion :trie-data-structure}
 :uses #{:recursion}}

;; --- [:function-template]
(defn autocomplete [trie prefix])

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

;; Solution

(defn autocomplete
  "Given a trie and a prefix, return all words in trie with that prefix"
  [trie prefix]
  (set (map (partial str prefix)
            (words (get-in trie (string/split prefix #""))))))

;; --- test-cases
(require '[clojure.test :refer [is]])

(is (= #{"hello" "help" "he" "health"}
       (autocomplete (trie ["hello" "help" "health" "he" "f"])
                     "he")))
