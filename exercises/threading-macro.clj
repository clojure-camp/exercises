{:title
 {:en-US "threading macro"
  :pt-BR "macro de encadeamento (threading macro)"}
 :difficulty :low
 :category :learning-functions
 :instructions
 {:en-US ["Refactor the below functions to use the \"threading macros\" `->` and `->>`"
          (defn process-number [n]
            (* (+ (inc (/ n 1.5)) 2) 10))
          (defn process-list [coll]
            (map (fn [x] (* x 10)) (filter even? (map inc coll))))]
  :pt-BR ["Refatore as funções abaixo para usar as \"macros de encadeamento\" (threading macros) `->` e `->>`"
          (defn process-number [n]
            (* (+ (inc (/ n 1.5)) 2) 10))
          (defn process-list [coll]
            (map (fn [x] (* x 10)) (filter even? (map inc coll))))]
  }
 :teaches #{-> ->>}
 :uses #{map filter}}

;; --- [:solution 0]

(defn process-number [n]
  (-> n
      (/ 1.5)
      inc
      (+ 2)
      (* 10)))

(defn process-list
  [coll]
  (->> (map inc coll)
       (filter even?)
       (map (fn [x] (* x 10)))))
