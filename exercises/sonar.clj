{:title
 {:en-US "sonar: submarine tracking"
  :pt-BR "sonar: rastreamento de submarino"}
 :difficulty :high
 :category :synthesis
 :instructions
 {:en-US ["A submarine is moving through a grid. We know the sequence of directions it travelled (`:N`, `:S`, `:E`, `:W`), but not its starting position."
          "The grid contains islands (marked as `\\X`) that the submarine cannot enter, and the submarine must stay within the grid."
          "Write a function `sonar` that takes a grid string and a sequence of directions, and returns a set of all possible ending positions `[x y]` of the submarine."
          "The grid is a newline-separated string where `\\.` is open water and `\\X` is an island. Coordinates are `[col row]`, 0-indexed from the top-left."
          "Example: for grid `\"X.X.\\n...X\\n....\\n....\"` and directions `[:N :N :W :N]`, the answer is `#{[0 1]}`."
          "Hint: for every open cell as a possible start, simulate the path and check if all positions are valid."]
  :pt-BR ["Um submarino está se movendo por uma grade. Sabemos a sequência de direções que ele percorreu (`:N`, `:S`, `:E`, `:W`), mas não sua posição inicial."
          "A grade contém ilhas (marcadas como `\\X`) que o submarino não pode entrar, e o submarino deve permanecer dentro da grade."
          "Escreva uma função `sonar` que recebe uma string de grade e uma sequência de direções, e retorna um conjunto de todas as posições finais possíveis `[x y]` do submarino."
          "A grade é uma string separada por quebras de linha onde `\\.` é água aberta e `\\X` é uma ilha. As coordenadas são `[coluna linha]`, indexadas a partir de 0 no canto superior esquerdo."
          "Exemplo: para a grade `\"X.X.\\n...X\\n....\\n....\"` e direções `[:N :N :W :N]`, a resposta é `#{[0 1]}`."
          "Dica: para cada célula aberta como possível início, simule o caminho e verifique se todas as posições são válidas."]}
 :function-template (defn sonar [grid directions])
 :test-cases
 [{:input (sonar "X.X.\n...X\n....\n...." [:N :N :W :N])
   :output #{[0 1]}}
  {:input (sonar "..\n.." [:N])
   :output #{[0 1] [1 1]}}
  {:input (sonar ".X\n.." [:N])
   :output #{[0 1]}}]
 :uses #{clojure.string/split-lines map-indexed set filter every? reduce conj}}

;; --- [:solution 0]

(require '[clojure.string :as string])

(defn- open-cells [grid]
  (->> (string/split-lines grid)
       (map-indexed (fn [y line]
                      (map-indexed (fn [x ch]
                                     (when (= ch \.) [x y]))
                                   line)))
       (apply concat)
       (remove nil?)
       set))

(defn- trace-path [directions start]
  (reduce (fn [path dir]
            (let [[x y] (last path)]
              (conj path (case dir
                           :N [x (dec y)]
                           :S [x (inc y)]
                           :E [(inc x) y]
                           :W [(dec x) y]))))
          [start]
          directions))

(defn sonar [grid directions]
  (let [valid (open-cells grid)]
    (->> valid
         (map (fn [start] (trace-path directions start)))
         (filter (fn [path] (every? valid path)))
         (map last)
         set)))
