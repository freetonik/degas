(ns degas.mutation
  (:require [degas.helpers :as h]))

;; Generic helper
(defn mut-apply [ind function]
  "Applies function to random unit of individual."
  (let [point (rand-int (count ind))]
    (assoc ind point (function (nth ind point)))))

;; Binary flipping
(defn bitflip [n] (if (= n 1) 0 1))

(defn mut-bitflip [ind]
  "Returns an individual with one random bit flipped. Works with binary units only."
  (mut-apply ind bitflip))

;; Swapping
(defn mut-swap [ind]
  "Returns an individual with two random genes swapped."
  (let [a (rand-int (count ind))
        b (rand-int (count ind))]
    (h/swap ind a b)))

;; Shifting
(defn mut-shift [ind]
  "Shift individual by random amount."
  (into [] (h/shift ind (rand-int (count ind)))))

;; Inc/dec
(defn mut-inc [ind]
  "Increment random unit of individual."
  (mut-apply ind inc))

(defn mut-dec [ind]
  "Decrement random unit of individual."
  (mut-apply ind dec))

;; Whole individual mutations
(defn mut-reverse [ind]
  "Returns a reversed ind."
  (apply vector (reverse ind)))

;; Population mutation
(defn mutate-pop [pop mutations-map ratio]
  (let [
        popsize       (count pop)
        work-pop      (shuffle pop)

        mutable-count (Math/floor (* ratio popsize))
        unmutable-count (- popsize mutable-count)

        mutable-pop   (take mutable-count work-pop)
        unmutable-pop (take-last unmutable-count work-pop)]

    (into [] (concat
              (map #((h/weighted-rand-choice mutations-map) %) mutable-pop)
              unmutable-pop))))
