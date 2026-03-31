(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn check
  [alphabet word]
  (->> (map = alphabet (str/lower-case word))
       (filter true?)
       count))

#_(check alphabet "abode")

#_(map (partial check alphabet) ["abode" "ABc" "xyzD" "ABCD"])
