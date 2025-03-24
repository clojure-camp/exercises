{:title
 {:en-US "counting DNA nucleotides"
  :pt-BR "contando nucleotídeos de DNA"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Given a string that represents a section of DNA, ex. `\"ATAGCAGGTA\"`, return a count of each nucleotide."]
  :pt-BR ["Dada uma string que representa uma seção de DNA, ex. `\"ATAGCAGGTA\"`, retorne a contagem de cada nucleotídeo."]}
 :function-template (defn count-nucleotides [s])
 :test-cases [{:input (count-nucleotides "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC")
               :output {"A" 20
                        "C" 12
                        "G" 17
                        "T" 21}}]
 :teaches #{frequencies}
 :uses #{clojure.string/split}
 :source "https://exercism.io/tracks/clojure/exercises/nucleotide-count"}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn count-nucleotides [s]
  (-> s
      (string/split #"")
      frequencies))
