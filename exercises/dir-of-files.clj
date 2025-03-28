{:category :synthesis
 :title
 {:en-US "directory of files"
  :pt-BR "diretório de arquivos"}
 :difficulty :mid
 :uses #{:java-interop .isFile .length .getName memfn map reduce filter clojure.string/ends-with?}
 :instructions
 {:en-US ["Write a function that calculates the total size of files with a certain extension in a certain directory."
          (defn directory-file-size [directory-path extension])]
  :pt-BR ["Escreva uma função que calcula o tamanho total dos arquivos com uma certa extensão em um certo diretório."
          (defn directory-file-size [directory-path extension])]}
 :teaches #{file-seq clojure.java.io/file}}

;; --- [:solution 0]

(require '[clojure.java.io :as io])
(require '[clojure.string :as string])

(defn directory-file-size
  [directory-path extension]
  (->> (file-seq (io/file directory-path))
       (filter (memfn isFile))
       (filter (fn [f] (string/ends-with? (.getName f) extension)))
       (map (fn [f] (.length f)))
       (reduce +)))
