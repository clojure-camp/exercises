{:title
 {:en-US "trie: autocomplete compacted tries (part 5)"}
 :category :synthesis
 :difficulty :high
 :related #{"trie-create-from-words"
            "trie-list-all-words"
            "trie-autocomplete"
            "trie-compact"}
 :instructions
 {:en-US ["Autocomplete, but for compacted tries"
          "(compact-autocomplete {\"he\" {\"l\" {\"lo\" {\"\" nil}, \"p\" {\"\" nil}}, \"alth\" {\"\" nil}, \"\" nil}, \"f\" {\"\" nil}} \"he\")"
          "should return: \"health\""
          (defn compact-autocomplete [trie prefix])]}

 :test-cases [{:input (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "x")
               :output #{}}
              {:input (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "h")
               :output #{"health" "he" "help" "hello"}}
              {:input (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "he")
               :output #{"health" "he" "help" "hello"}}
              {:input (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "hea")
               :output #{"health"}}]
 :teaches #{loop recur map mapcat partial :recursion :trie-data-structure}
 :uses #{:recursion}
 :code [(= #{}
           (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "x"))

        (= #{"health" "he" "help" "hello"}
           (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "h"))

        (= #{"health" "he" "help" "hello"}
           (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "he"))

        (= #{"health"}
           (compact-autocomplete (compact (trie ["hello" "help" "health" "he" "f"])) "hea"))]}

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

;; Solution

(defn compact-autocomplete
  "Like autocomplete, but for compacted tries."
  [trie prefix]
  (loop [path [(str (first prefix))]
         remaining-letters (rest prefix)]
    (if (seq remaining-letters)
      (if (get-in trie path)
                    ;; have letters remaining, and current path has a subtrie
                    ;; so... start a new path key with the next letter
        (recur (conj path (str (first remaining-letters)))
               (rest remaining-letters))
                    ;; have letters remaining, but current path has no subtrie
                    ;; so... append next letter to last working path key
        (recur (update path (dec (count path)) (fn [x] (str x (first remaining-letters))))
               (rest remaining-letters)))
      (if (get-in trie path)
                    ;; no letters remaining, and the current path has a subtrie
                    ;; so... return all words in subtrie (prefixed by search term)
        (set (map (partial str prefix)
                  (words (get-in trie path))))
                    ;; no letters remaining, but the current path has no subtrie
                    ;; so... find the key in the last valid subtrie that starts with the last path key (or none)
                    ;; and prefix by search term
        (let [valid-path (vec (butlast path))]
          (if-let [[k subtrie] (->> (get-in trie valid-path)
                                    (filter (fn [[k _]] (string/starts-with? k (last path))))
                                    first)]
            (set (map (partial str (string/join "" (flatten (conj valid-path k))))
                      (words subtrie)))
            #{}))))))
