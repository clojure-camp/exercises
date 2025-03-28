{:title
 {:en-US "poker-rank"
  :pt-BR "classificação de poker"}
 :category :synthesis
 :uses #{partition map sort apply frequencies vals not sort-by reverse vec cond}
 :difficulty :high
 :related #{"poker-deal"}
 :instructions
 {:en-US ["Write a function to determine the winning hand in a poker game."
          "The function should take a collection of hands, a hand being a collection of cards. Cards can be represented however you wish."
          "The function should return the same collection, but sorted according to poker rules (see https://en.wikipedia.org/wiki/List_of_poker_hands)"]
  :pt-BR ["Escreva uma função que determina a mão vencedora em um jogo de poker."
          "A função deve receber uma coleção de mãos, uma mão sendo uma coleção de cartas. Cartas podem ser representadas como você desejar."
          "A função deve retornar a mesma coleção, mas ordenada de acordo com as regras do poker (veja https://pt.wikipedia.org/wiki/Lista_de_jogadas_do_p%C3%B4quer)"]}
 :source "https://exercism.io/tracks/clojure/exercises/poker"}

;; --- [:solution 0]

(ns poker-rank
  (:refer-clojure :exclude [flush]))

(comment
  "Example card:"
  {:card/rank 1
   :card/suit :clubs}
  "jack, queen, king, ace are rank 11, 12, 13, 14")

;; a few utility functions:

(defn deltas
  [nums]
  (->> nums
       (partition 2 1)
       (map (fn [[a b]]
              (- b a)))))

(defn all-in-order? [cards]
  (->> cards
       (map :card/rank)
       sort
       deltas
       (apply = 1)))

(defn all-same-suit? [cards]
  (apply = (map :card/suit cards)))

(defn n-of-rank? [arr cards]
  (= (sort arr)
     (->> cards
          (map :card/rank)
          frequencies
          vals
          sort)))

;; functions to check hands

(defn straight-flush? [cards]
  (and
    (all-same-suit? cards)
    (all-in-order? cards)))

(defn four-of-a-kind? [cards]
  (n-of-rank? [4 1] cards))

(defn full-house? [cards]
  (n-of-rank? [3 2] cards))

(defn flush? [cards]
  (and
    (all-same-suit? cards)
    (not (all-in-order? cards))))

(defn straight? [cards]
  (and
    (all-in-order? cards)
    (not (all-same-suit? cards))))

(defn three-of-a-kind? [cards]
  (n-of-rank? [3 1 1] cards))

(defn two-pair? [cards]
  (n-of-rank? [2 2 1] cards))

(defn one-pair? [cards]
  (n-of-rank? [2 1 1 1] cards))

(defn high-card? [cards]
  (and
    (n-of-rank? [1 1 1 1 1] cards)
    (not (all-in-order? cards))
    (not (all-same-suit? cards))))

;; functions to sort hands

(defn subrank [cards]
  (->> cards
       (map :card/rank)
       frequencies
       (sort-by (fn [[rank count]]
                  [count rank]))
       reverse
       vec))

(defn rank-hand [cards]
  [(cond
     (straight-flush? cards) 8
     (four-of-a-kind? cards) 7
     (full-house? cards) 6
     (flush? cards) 5
     (straight? cards) 4
     (three-of-a-kind? cards) 3
     (two-pair? cards) 2
     (one-pair? cards) 1
     (high-card? cards) 0)
   (subrank cards)])

(defn rank-hands [hands]
  (reverse (sort-by rank-hand hands)))

;; --- [:solution 1]

(ns poker-rank-test
  (:refer-clojure :exclude [flush])
  (:require
   [poker-rank :refer :all]
   [clojure.test :refer [testing is]]))

(defn ->cards
  "Utility function to make testing easier.
              Can represent cards with abbreviated keywords,
              ex. :c9 -> {:card/rank 9 :card/suit :clubs}"
  [& cards]
  (->> cards
       (map (fn [card]
              (let [[_ suit-char rank-str] (re-matches #"(.)(.*)" (name card))]
                {:card/rank (Integer. rank-str)
                 :card/suit (case suit-char
                              "c" :clubs
                              "h" :hearts
                              "d" :diamonds
                              "s" :spades)})))))

(def straight-flush (->cards :c9 :c7 :c10 :c11 :c8))
(def straight-flush-2 (->cards :c9 :c7 :c10 :c6 :c8))
(def four-of-a-kind (->cards :c5 :d5 :h5 :s5 :c8))
(def four-of-a-kind-2 (->cards :c5 :d5 :h5 :s5 :c7))
(def four-of-a-kind-3 (->cards :c3 :d3 :h3 :s3 :c8))
(def full-house (->cards :s6 :h6 :c13 :d6 :h13))
(def full-house-2 (->cards :s6 :h6 :c12 :d6 :h12))
(def full-house-3 (->cards :s5 :h5 :c14 :d5 :h14))
(def flush (->cards :d2 :d3 :d5 :d7 :d9))
(def flush-2 (->cards :d2 :d3 :d5 :d7 :d8))
(def straight (->cards :c9 :d7 :c10 :c11 :c8))
(def straight-2 (->cards :c9 :d7 :c10 :c6 :c8))
(def three-of-a-kind (->cards :c5 :h5 :c8 :d5 :s6))
(def three-of-a-kind-2 (->cards :c5 :h5 :c7 :d5 :s6))
(def three-of-a-kind-3 (->cards :c4 :h4 :c9 :d4 :s6))
(def two-pair (->cards :h11 :s3 :s11 :h5 :c3))
(def two-pair-2 (->cards :h11 :s3 :s11 :h2 :c3))
(def two-pair-3 (->cards :h11 :s2 :s11 :h9 :c2))
(def two-pair-4 (->cards :h10 :s9 :s10 :h13 :c9))
(def one-pair (->cards :s8 :h10 :h7 :s10 :c4))
(def one-pair-2 (->cards :s6 :h10 :h7 :s10 :c4))
(def one-pair-3 (->cards :s8 :h9 :h7 :s9 :c4))
(def high-card (->cards :d12 :s7 :d13 :s4 :h3))
(def high-card-2 (->cards :d12 :s7 :d13 :s4 :h2))
(def high-card-3 (->cards :d12 :s7 :d11 :s4 :h2))

(testing "straight-flush"
  (testing "correct"
    (is (= true (straight-flush? straight-flush))))
  (testing "incorrect"
    (is (= false (straight-flush? straight)))
    (is (= false (straight-flush? flush)))))

(testing "four-of-a-kind"
  (testing "correct"
    (is (= true (four-of-a-kind? four-of-a-kind))))
  (testing "incorrect"
    (is (= false (four-of-a-kind? three-of-a-kind)))))

(testing "full-house"
  (testing "correct"
    (is (= true (full-house? full-house))))
  (testing "incorrect"
    (is (= false (full-house? two-pair)))
    (is (= false (full-house? three-of-a-kind)))))

(testing "flush?"
  (testing "correct"
    (is (= true (flush? flush))))
  (testing "incorrect"
    (is (= false (flush? straight-flush)))
    (is (= false (flush? high-card)))))

(testing "straight?"
  (testing "correct"
    (is (= true (straight? straight))))
  (testing "incorrect"
    (is (= false (straight? straight-flush)))
    (is (= false (straight? high-card)))))

(testing "three-of-a-kind"
  (testing "correct"
    (is (= true (three-of-a-kind? three-of-a-kind))))
  (testing "incorrect"
    (is (= false (three-of-a-kind? four-of-a-kind)))
    (is (= false (three-of-a-kind? full-house)))))

(testing "two-pair"
  (testing "correct"
    (is (= true (two-pair? two-pair))))
  (testing "incorrect"
    (is (= false (two-pair? full-house)))
    (is (= false (two-pair? four-of-a-kind)))))

(testing "one-pair"
  (testing "correct"
    (is (= true (one-pair? one-pair))))
  (testing "incorrect"
    (is (= false (one-pair? four-of-a-kind)))
    (is (= false (one-pair? full-house)))
    (is (= false (one-pair? two-pair)))))

(testing "high-card"
  (testing "correct"
    (is (= true (high-card? high-card))))

  (testing "incorrect"
    (is (= false (high-card? straight-flush)))
    (is (= false (high-card? four-of-a-kind)))
    (is (= false (high-card? full-house)))
    (is (= false (high-card? flush)))
    (is (= false (high-card? straight)))
    (is (= false (high-card? three-of-a-kind)))
    (is (= false (high-card? two-pair)))
    (is (= false (high-card? one-pair)))))

(testing "subrank"
  (is (= [[11 1] [10 1] [9 1] [8 1] [7 1]] (subrank straight-flush)))
  (is (= [[5 4] [8 1]] (subrank four-of-a-kind)))
  (is (= [[6 3] [13 2]] (subrank full-house)))
  (is (= [[9 1] [7 1] [5 1] [3 1] [2 1]] (subrank flush)))
  (is (= [[11 1] [10 1] [9 1] [8 1] [7 1]] (subrank straight)))
  (is (= [[5 3] [8 1] [6 1]] (subrank three-of-a-kind)))
  (is (= [[11 2] [3 2] [5 1]] (subrank two-pair)))
  (is (= [[10 2] [8 1] [7 1] [4 1]] (subrank one-pair)))
  (is (= [[13 1] [12 1] [7 1] [4 1] [3 1]] (subrank high-card))))

(defn is-ordered [hands-sym]
  (is (= hands-sym
         (->> hands-sym
              (map (fn [sym]
                     [sym (deref (resolve sym))]))
              shuffle
              (sort-by (fn [[sym cards]]
                         (rank-hand cards)))
              reverse
              (map first)))))

(testing "rank-hands"
  (testing "correct"
    (is-ordered '[straight-flush
                  four-of-a-kind
                  full-house
                  flush
                  straight
                  three-of-a-kind
                  two-pair
                  one-pair])

    (is-ordered '[straight-flush
                  straight-flush-2
                  four-of-a-kind
                  four-of-a-kind-2
                  four-of-a-kind-3
                  full-house
                  full-house-2
                  full-house-3
                  flush
                  flush-2
                  straight
                  straight-2
                  three-of-a-kind
                  three-of-a-kind-2
                  three-of-a-kind-3
                  two-pair
                  two-pair-2
                  two-pair-3
                  two-pair-4
                  one-pair
                  one-pair-2
                  one-pair-3
                  high-card
                  high-card-2
                  high-card-3])))
