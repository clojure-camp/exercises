{:title
 {:en-US "make pmap"
  :pt-BR "criar pmap"}
 :teaches #{future deref time}
 :uses #{map}
 :category :learning-functions
 :difficulty :low
 :instructions
 {:en-US ["`pmap` is a built-in function that acts just like `map`, except it runs the functions over the collection in parallel."
          "Implement your own version of `pmap` and test that it works by using `time` to compare the performance of the normal `map` and your version of `pmap`. You can use `Thread/sleep` inside a function to simulate a long running process."]
  :pt-BR ["`pmap` é uma função nativa que funciona exatamente como `map`, exceto que ela executa as funções sobre a coleção em paralelo."
          "Implemente sua própria versão de `pmap` e teste se ela funciona usando `time` para comparar o desempenho do `map` normal e sua versão de `pmap`. Você pode usar `Thread/sleep` dentro de uma função para simular um processo demorado."]}}

;; --- [:solution 0]

(defn my-pmap
  [f coll]
  (->> coll
       (map (fn [x]
              (future (f x))))
       (map deref)))
