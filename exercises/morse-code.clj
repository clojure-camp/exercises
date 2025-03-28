{:title
 {:en-US "morse code"
  :pt-BR "código morse"}
 :category :synthesis
 :difficulty :low
 :related #{"morse-code-redux"}
 :instructions
 {:en-US ["Write functions to convert to & from morse code:"
          (defn morse->english [morse])
          (defn english->morse [english])]
  :pt-BR ["Escreva funções para converter de e para código morse:"
          (defn morse->english [morse])
          (defn english->morse [english])]}
 :function-template [(defn morse->english [morse])
                     (defn english->morse [english])]
 :test-cases
[{:input (morse->english "... --- ...")
  :output "SOS"}
 {:input (english->morse "SOS")
  :output "... --- ..."}]
 :uses #{:maps map-invert clojure.string/split clojure.string/join
        ->> map}}

;; --- [:solution 0]

(require '[clojure.set :refer [map-invert]])
(require '[clojure.string :as string])

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
  [morse]
  (->> (string/split morse #"\s+")
       (map morse->alphabet)
       (string/join "")))

(defn english->morse
  [english]
  (->> (string/split english #"")
       (map alphabet->morse)
       (string/join " ")))
