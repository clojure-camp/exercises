(ns exercises.20250102
  (:require
   [clojure.string :as string]))

;; who's here?
;; raf
;; ionse/chris
;; hi, tim
;; tims
;; recia

;; the problem:

;; we are a hunting submarine
;; we know the path it took
;;  but not its starting location
;; we need to determine its ending location
;; we also have a map of islands
;; submarine can never have hit an island or gone off the map

;; NNWN

;; X.X.
;; ...X
;; ....
;; ....

;; 4
;; 32
;;  1
;;  0

;; valid positions:
;; 0,1

;; plan:

;; set of allowed starting positions

;; for every potential starting point (the grid):
  ;; NNWN  + 0,0  => 0,0 -1,0  -1,-1 ...
  ;; filter for ones where path does not violate constraints

(def grid
  "X.X.\n...X\n....\n....")

;; #{ [0 0] [0 1]   [0 3]  [1 0 ]   }

(defn starting-positions [g]
  (->> g
       string/split-lines
       (map-indexed (fn [y line]
                      (map-indexed (fn [x v]
                                     (when (not= v \X) [x y]))
                                   line)))
       (apply concat)
       (remove nil?)
       set))

#_(starting-positions grid)

(defn generate-path [directions start-coord]
  (reduce (fn [acc dir]
            (let [[last-x last-y] (last acc)]
              (conj acc (case dir
                          :N [last-x (dec last-y)]
                          :S [last-x (inc last-y)]
                          :E [(inc last-x) last-y]
                          :W [(dec last-x) last-y]))))
          [start-coord]
          directions))

#_(generate-path [:N :N :W :N] [0 0]) ;; [ [0 0] ... ]


(def valid-pos (starting-positions grid))

(->> grid
     starting-positions
     (map (fn [pos] (generate-path [:N :N :W :N] pos)))
     (filter (fn [path]
               (every? valid-pos path)))
     (map last))


;; homework: solve this with core.logic
