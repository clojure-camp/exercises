;; https://github.com/openai/human-eval

(->> (str/split (slurp "HumanEval.jsonl") #"\n")
     (map json/parse-string)
     (map #(get % "prompt"))
     (str/join "\n--------\n\n")
     (spit "prompts.txt"))




;; https://exercism.org/tracks/clojure/concepts
;; https://leetcode.com/
;; https://github.com/exercism/clojure
