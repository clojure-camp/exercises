{:title
 {:en-US "caeser cipher"
  :pt-BR "cifra de César"}
 :category :starter
 :difficulty :mid
 :instructions
 {:en-US ["Write a function to decode a Caeser Cipher. A Caeser Cipher is a rotation cipher: each letter in some text is shifted in the alphabet by a fixed number. For example, \"IBM\" and -1 results in \"HAL\"."
          "Create a function `caesar-decode` that takes a string and a \"secret key\" which is a number that indicates how many characters to rotate the letters by."
          "You can assume that only upper-case letters will be used, leave spaces unchanged."]
  :pt-BR ["Escreva uma função que decodifica a cifra de César. A cifra de César é uma cifra de rotação: cada letra em um texto é deslocada no alfabeto por um número fixo. Por exemplo, \"IBM\" e -1 resulta em \"HAL\"."
          "Crie uma função `caesar-decode` que recebe uma string e uma \"chave secreta\" que é um número que indica quantos caracteres deverá rotacionar as letras."
          "Você pode assumir que apenas letras maiúsculas serão usadas, deixe os espaços inalterados."]}
 :function-template (defn caeser-decode [key message])
 :test-cases
[{:input (caeser-decode 3 "HELLO WORLD")
  :output "KHOOR ZRUOG"}
 {:input (caeser-decode -3 "KHOOR ZRUOG")
  :output "HELLO WORLD"}
 {:input (caeser-decode 13 (caeser-decode 13 "HELLO WORLD"))
  :output "HELLO WORLD"}]
 :uses #{int char + - mod map ->> apply str seq}}

;; --- [:solution 0]

(def char-base (int \A))

(defn caeser-decode
  [key message]
  (->> (seq message)
       (map (fn [chr]
              (if (= chr \space)
                \space
                (-> (int chr)
                    (- char-base)
                    (+ key)
                    (mod 26)
                    (+ char-base)
                    char))))
       (apply str)))
