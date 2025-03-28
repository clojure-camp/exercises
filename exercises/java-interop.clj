{:title
 {:en-US "java interop"
  :pt-BR "interoperabilidade com Java"}
 :category :learning-functions
 :difficulty :mid
 :instructions
 {:en-US ["Use the `java.security.MessageDigest` class from the Java standard library to write a function that generates the hex-encoded SHA-256 hash of an input string."
          "In Java, you would do:"
          "`String s = \"Some Text\"`"
          "`MessageDigest md = MessageDigest.getInstance(\"SHA-256\")`"
          "`md.update(s.getBytes(\"UTF-8\"))`"
          "`byte[] dig = md.digest()`"
          "You can convert a byte to its character in Clojure using `(format \"%02x\" the-byte)`"]
  :pt-BR ["Use a classe `java.security.MessageDigest` da biblioteca padrão do Java para escrever uma função que gera o hash SHA-256 de uma string de entrada, codificado em hexadecimal."
          "Em Java, você faria:"
          "`String s = \"Some Text\"`"
          "`MessageDigest md = MessageDigest.getInstance(\"SHA-256\")`"
          "`md.update(s.getBytes(\"UTF-8\"))`"
          "`byte[] dig = md.digest()`"
          "Você pode converter um byte para seu caractere em Clojure usando `(format \"%02x\" the-byte)`"]}
 :function-template (defn sha256 [s])
 :test-cases [{:input (sha256 "hello world")
               :output "b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9"}]
 :teaches #{:interop doto import}
 :uses #{-> ->> map apply format}}

;; --- [:solution 0]
(import '(java.security MessageDigest))

(defn sha256
  [s]
  (-> (doto (MessageDigest/getInstance "SHA-256")
        (.update (.getBytes s "UTF-8")))
      (.digest)
      (->> (map (fn [b] (format "%02x" b)))
           (apply str))))
