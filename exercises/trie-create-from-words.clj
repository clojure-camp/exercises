{:title
 {:en-US "trie: create from list of words (part 1)"}
 :category :synthesis
 :difficulty :low
 :related #{"trie-list-all-words"
            "trie-autocomplete"
            "trie-compact"
            "trie-compact-autocomplete"}
 :instructions
 {:en-US ["Write a function to create a trie map from list of words:"
          "`|---h---|`"
          "`|---e---|`"
          "`|---l---|`"
          "`|-/---\\-|`"
          "`|l-----p|`"
          "`|o------|`"
          (defn trie [words])]}

 :test-cases [{:input (trie ["hello" "help" "health" "he" "f"])
               :output {"h" {"e" {"l" {"l" {"o" {"" nil}}
                                       "p" {"" nil}}
                                  "a" {"l" {"t" {"h" {"" nil}}}}
                                  "" nil}}
                        "f" {"" nil}}}]
 :teaches #{reduce assoc-in conj :regex :trie-data-structure}
 :uses #{:reduce :data :string-operations}
 :code (= {"h" {"e" {"l" {"l" {"o" {"" nil}}
                          "p" {"" nil}}
                     "a" {"l" {"t" {"h" {"" nil}}}}
                     "" nil}}
           "f" {"" nil}}
          (trie ["hello" "help" "health" "he" "f"]))}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn trie
  "Create a trie from a list of words."
  [words]
  (reduce (fn [memo word]
            (assoc-in memo (conj (string/split word #"") "") nil))
          {} words))
