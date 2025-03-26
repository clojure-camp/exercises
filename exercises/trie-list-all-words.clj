{:title
 {:en-US "trie: return the list of all words stored in a trie (recursive) (part 2)"}
 :category :synthesis
 :difficulty :mid
 :related #{"trie-create-from-words"
            "trie-autocomplete"
            "trie-compact"
            "trie-compact-autocomplete"}
 :instructions
 {:en-US ["return the list of all words stored in a trie"
          "`|---h---|`"
          "`|---e---|`"
          "`|---l---|`"
          "`|-/---\\-|`"
          "`|l-----p|`"
          "`|o------|`"
          "should return: #{\"hello\" and \"help\"}"
          (defn words [trie])]}

 :test-cases [{:input (words (trie ["hello" "help" "health" "he" "f"]))
               :output #{"hello" "f" "health" "help" "he"}}]
 :teaches #{reduce assoc-in conj map partial str :recursion :trie-data-structure}
 :uses #{:recursion}
 :code (= #{"hello" "f" "health" "help" "he"}
          (words (trie ["hello" "help" "health" "he" "f"])))}

;; --- [:solution 0]

;; From part 1

(require '[clojure.string :as string])
(defn trie
  "Create a trie from a list of words."
  [words]
  (reduce (fn [memo word]
            (assoc-in memo (conj (string/split word #"") "") nil))
          {} words))

;; Solution

(defn words
  "Return the list of all words stored in a trie (recursive)."
  [trie]
  (set (mapcat (fn [letter]
                 (if-let [subtrie (trie letter)]
                   (map (partial str letter) (words subtrie))
                   [""]))
               (keys trie))))
