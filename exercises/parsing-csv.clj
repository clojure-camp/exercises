{:title        {:en-US "CSV parsing"
                :pt-BR "parser de CSV"}
 :category     :synthesis
 :difficulty   :high
 :instructions {:en-US ["Write a function to parse a CSV string into a series of vectors."
                        "For example, if the file looks like this:"
                        [:file
                         "name,email,comment"
                         "james,james@james.com,alright\\, but weird"
                         "raf,raf@raf.com,they say \"works, plays\""
                         "beep,beep@beep.com,they say\\, \"works, plays\",3"]
                        "The output would be:"
                        [:code
                         [["name" "email" "comment" "id"]
                          ["james" "james@james.com" "alright, but weird" "1"]
                          ["raf" "raf@raf.com" "they say \"works, plays\"" "2"]
                          ["beep" "beep@beep.com" "they say, \"works, plays\"" "3"]]]
                        "In practice, you'd use a library to parse CSVs, but for this exercise, do it from scratch."]
                :pt-BR ["Escreva uma função para fazer o parse de uma string CSV em uma série de vetores."
                        "Por exemplo, se o arquivo se parece com isso:"
                        [:file
                         "name,email,comment"
                         "james,james@james.com,alright\\, but weird"
                         "raf,raf@raf.com,they say \"works, plays\""
                         "beep,beep@beep.com,they say\\, \"works, plays\",3"]
                        "O resultado será:"
                        [:code
                         [["name" "email" "comment" "id"]
                          ["james" "james@james.com" "alright, but weird" "1"]
                          ["raf" "raf@raf.com" "they say \"works, plays\"" "2"]
                          ["beep" "beep@beep.com" "they say, \"works, plays\"" "3"]]]
                        "Na prática, você usaria uma biblioteca para fazer o parse de CSVs, mas para esse exercício, faça do zero."]}
 :uses         #{slurp clojure.string/split loop recur cond empty? conj map clojure.string/replace}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn parse-csv
  [file-name]
  (let [lines (string/split (slurp file-name) #"\n")]
    ;; if we don't need to worry about quotes, we can take the easy path
    (for [line lines]
      (if (nil? (string/index-of line "\""))
        ;; you could write the pattern as #"(?<!\\),", just doing it this way because the syntax highlighter complains
        (->> (string/split line (re-pattern "(?<!\\\\),"))
             (mapv (fn [col] (string/replace col #"\\," ","))))
        (loop [accum            [[]]
               in-quote?        false
               escaped?         false
               [c & cs :as txt] line]
          (let [idx (dec (count accum))]
            (cond
              (empty? txt)
              (mapv (partial apply str) accum)

              escaped?
              (recur (update accum idx conj c)
                     in-quote? false cs)

              (= \\ c)
              (recur accum in-quote? true cs)

              (= \" c)
              (recur (update accum idx conj c)
                     (not in-quote?)
                     false
                     cs)

              (and (not in-quote?) (= \, c))
              (recur (conj accum []) in-quote? false cs)

              :else
              (recur (update accum (dec (count accum))
                             conj c)
                     in-quote?
                     false
                     cs))))))))
