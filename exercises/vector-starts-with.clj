{:title
 {:en-US "vector starts with"
  :pt-BR "vetor começa com"}
 :difficulty :low
 :category :starter
 :instructions
 {:en-US ["Write a function `starts-with?` that takes two vectors `big` and `small`, and returns `true` if `big` starts with all the elements of `small`."]
  :pt-BR ["Escreva uma função `starts-with?` que recebe dois vetores `big` e `small`, e retorna `true` se `big` começa com todos os elementos de `small`."]}
 :function-template (defn starts-with? [big small])
 :test-cases
 [{:input (starts-with? [:foo :bar 0 :baz] [:foo :bar])
   :output true}
  {:input (starts-with? [:foo] [:foo :bar])
   :output false}
  {:input (starts-with? [:foo :zed] [:foo :bar])
   :output false}
  {:input (starts-with? [:foo :bar] [:a :bar])
   :output false}
  {:input (starts-with? [:a :b :c] [])
   :output true}
  {:input (starts-with? [] [])
   :output true}]
 :uses #{map every? count take =}}

;; --- [:solution 0]

(defn starts-with? [big small]
  (and
   (>= (count big) (count small))
   (->> (map = big small)
        (every? true?))))
