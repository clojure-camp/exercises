(ns exercises.marketplace)

;; there is marketplace of vendors
;; each offering a certain amount of foos for bars
;; want to be able to convert from foos to bars
;; function 1 - show cost of converting some number of foos to bars, via multiple trades in marketplace (ex. 2 foo => 5 bars)
;; function 2 - path to achieve that conversion 1 foo => 4 cats => 2 zigs => 25 bars

(def offers
  [{:from [4 :banana]
    :to [2 :eagles]
    :name "bob"}
   {:from [2 :zebra]
    :to [2 :eagles]
    :name "bill"}])

;; TODO convert offers to lookup

(def lookup
 {:bananas {:eagles [4 2]
            :apples [1 2]}
  :apples {:zebras [2 4]
           :monkeys [1 5]}
  :eagles {}
  :zebras {}
  :turtles {}
  :monkeys {:turtles [2 4]}})

;; assuming unidirectional offers only
;; assuming there is always a path to be found
;; assuming there is only 1 path to be found

(defn search [path target-unit]
  (if (= (last path) target-unit)
    path
    (let [current-unit (last path)
          options (keys (lookup current-unit))]
     (->> options
          (map (fn [option]
                (search (conj path option) target-unit)))
          (remove empty?)
          first))))

#_(search [:bananas] :eagles)
;; [:banana :eagles]
#_(search [:bananas] :zebras)
;; [:banana :apples :zebra]
#_(search [:bananas] :turtles)
;; [:banana :apples :monkeys :turtles]

(defn convert [[quantity source-unit] target-unit])
  ;; TODO


#_(convert [5 :apple] :zebra)
;; [6.2 :zebra]

(defn detailed-convert [[quantity source-unit] target-unit])
  ;; TODO

#_(detailed-convert [5 :apple] :zebra)
;; [[5 :apple] [3 :banana] [2 :eagles] [6.2 :zebra]]
