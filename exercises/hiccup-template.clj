{:title
 {:en-US "hiccup template"
  :pt-BR "template hiccup"}
 :category :synthesis
 :difficulty :low
 :instructions
 {:en-US ["Write a function that reads a hiccup template .edn file and replaces symbols in that template with values in a map."
          "For example, with a file that looks like the below:"
          [:div {:class class-name}
           [:p "hello " some-stuff]]
          "You should be able to call your function like follows:"
          (hiccup-template "name-of-file.edn" {'class-name "theclass"
                                               'some-stuff "world!"})
          "Which would return:"
          [:div {:class "theclass"}
           [:p "hello " "world!"]]]
  :pt-BR ["Escreva uma função que lê um arquivo .edn de template hiccup e substitui símbolos nesse template com valores em um mapa."
          "Por exemplo, com um arquivo que se parece com o abaixo:"
          [:div {:class class-name}
           [:p "hello " some-stuff]]
          "Você deveria ser capaz de chamar sua função dessa maneira:"
          (hiccup-template "nome-do-arquivo.edn" {'class-name "theclass"
                                                 'some-stuff "world!"})
          "O que retornaria:"
          [:div {:class "theclass"}
           [:p "hello " "world!"]]]}
 :related #{"hiccup-html"}
 :uses #{clojure.edn/read-string slurp clojure.walk/postwalk-replace}}

;; --- [:solution 0]

(require '[clojure.edn :as edn])
(require '[clojure.walk :as walk])

(defn hiccup-template
  [file-name template-vars]
  (->> (slurp file-name)
       (edn/read-string)
       (walk/postwalk-replace template-vars)))
