{:title
 {:en-US "basic files"
  :pt-BR "básico de arquivos"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Read in a text file (assuming it's in the current directory), reverse the file's contents, and write the result to a new file (the name now prefixed by \"rev-\")."]
  :pt-BR ["Leia um arquivo de texto (assumindo que ele está no diretório atual), inverta o conteúdo do arquivo, e escreva o resultado em um novo arquivo (o nome agora com o prefixo \"rev-\")."]}
 :teaches #{spit slurp}
 :uses #{clojure.string/reverse str ->>}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn reverse-file
  [file-name]
  (->> (slurp file-name)
       string/reverse
       (spit (str "rev-" file-name))))
