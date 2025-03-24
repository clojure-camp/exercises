{:title
 {:en-US "morse code redux"
  :pt-BR "código morse redux"}
 :category :synthesis
 :difficulty :mid
 :related #{"morse-code"}
 :instructions
 {:en-US ["Convert from morse code to English, where the input is a signal, given as a sequence of ones or zeros."
          "If the signal stays high for more than three samples, consider it a dash; otherwise, it's a dot."
          "The signal being low for more than three samples indicates the end of a character and being low for more than five samples indicates a gap between words."]
  :pt-BR ["Converta de código morse para inglês, onde a entrada é um sinal, dado como uma sequência de uns ou zeros."
          "Se o sinal permanecer alto por mais de três amostras, considere-o um traço; caso contrário, é um ponto."
          "O sinal estar baixo por mais de três amostras indica o fim de um caractere e estar baixo por mais de cinco amostras indica uma lacuna entre palavras."]}
 :function-template (defn morse-signal->english [signal])
 :test-cases
[{:input "HELLO WORLD"
  :output (morse-signal->english
           [0
            0
            1
            1
            0
            1
            1
            0
            0
            1
            0
            1
            0
            0
            0
            0
            1
            0
            0
            0
            0
            1
            0
            1
            1
            1
            1
            0
            0
            1
            1
            0
            1
            0
            0
            0
            0
            1
            1
            0
            0
            1
            1
            1
            1
            0
            1
            1
            1
            0
            0
            1
            1
            0
            0
            0
            0
            1
            1
            1
            1
            0
            0
            1
            1
            1
            1
            1
            0
            0
            1
            1
            1
            1
            0
            0
            0
            0
            0
            0
            0
            1
            0
            1
            1
            1
            1
            0
            0
            1
            1
            1
            1
            1
            0
            0
            0
            0
            1
            1
            1
            1
            0
            0
            0
            1
            1
            1
            1
            1
            0
            1
            1
            1
            1
            0
            0
            0
            0
            0
            1
            1
            0
            0
            1
            1
            1
            1
            0
            1
            0
            0
            0
            0
            1
            0
            1
            1
            1
            1
            0
            1
            0
            0
            1
            0
            0
            0
            0
            1
            1
            1
            1
            0
            0
            1
            0
            1
            0
            0])}]
:uses #{:maps clojure.string/split clojure.string/join :regex
        partition-by reduce count conj update clojure.string/blank? cond map -> ->>}}

;; --- [:solution 0]

(require '[clojure.string :as string])
(require '[clojure.set :refer [map-invert]])

(def alphabet->morse
  {"A" ".-"
   "B" "-..."
   "C" "-.-."
   "D" "-.."
   "E" "."
   "F" "..-."
   "G" "--."
   "H" "...."
   "I" ".."
   "J" ".---"
   "K" "-.-"
   "L" ".-.."
   "M" "--"
   "N" "-."
   "O" "---"
   "P" ".--."
   "Q" "--.-"
   "R" ".-."
   "S" "..."
   "T" "-"
   "U" "..-"
   "V" "...-"
   "W" ".--"
   "X" "-..-"
   "Y" "-.--"
   "Z" "--.."
   "1" ".----"
   "2" "..---"
   "3" "...--"
   "4" "....-"
   "5" "....."
   "6" "-...."
   "7" "--..."
   "8" "---.."
   "9" "----."
   "0" "-----"})

(def morse->alphabet (map-invert alphabet->morse))

(defn morse->english
  "Take a string of space-separated Morse code encoded as '.' and '-' and translates them into numbers & letters"
  [morse]
  (->> (string/split morse #"\s+")
       (map morse->alphabet)
       (string/join "")))

(defn english->morse
  "Take a string of numbers and letters, convert to Morse code"
  [english]
  (->> (string/split english #"")
       (map alphabet->morse)
       (string/join " ")))

(defn- collapse-runs
  "Collapse adjacent runs of identical values into [value count]."
  [numbers]
  (->> (partition-by identity numbers)
       (map (fn [[v & _ :as run]] [v (count run)]))))

(defn signal->morse
  "Take a sequence of numbers indicating a signal over time -- either 1 or 0.
  Translates the signal into a sequence of '.' and '-'"
  [signal]
  (->> signal
       collapse-runs
       (reduce
        (fn [chars [val len]]
          (let [last-idx (dec (count chars))]
            (cond
              (and (= val 0) (>= len 6)) (conj chars [] [])
              (and (= val 0) (> 6 len 3)) (conj chars [])

              (= val 0) chars

              (> len 3) (update chars last-idx conj "-")

              true (update chars last-idx conj "."))))
        [[]])
       (map (fn [chars] (string/join "" chars)))))

(defn morse-signal->english
  [signal]
  (->> signal
       signal->morse
       (partition-by string/blank?)
       (map (partial string/join " "))
       (map morse->english)
       (remove string/blank?)
       (string/join " ")))

