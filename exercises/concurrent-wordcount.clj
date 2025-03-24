{:title
 {:en-US "concurrent word-count"
  :pt-BR "contagem de palavras concorrente"}
 :category :synthesis
 :difficulty :low
 :instructions
 {:en-US ["Write a function that takes a list of file names and returns the total word count across all the files."
          "To try to speed it up, make your function sum the files in parallel (you can measure how long excution takes with `time`)."]
  :pt-BR ["Escreva uma função que recebe uma lista de nomes de arquivos e retorna a contagem total de palavras em todos os arquivos."
          "Para tentar acelerar, faça sua função somar os arquivos em paralelo (você pode medir quanto tempo a execução leva com `time`)."]}
 :uses #{atom deref swap! pmap doall time}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn word-count-files
  [file-names]
  (let [counts (atom 0)]
    (doall
     (pmap
      (fn [file-name]
        (let [text (slurp file-name)]
          (->> (string/split text #"\s+")
               (count)
               (swap! counts +))))
      file-names))
    @counts))
