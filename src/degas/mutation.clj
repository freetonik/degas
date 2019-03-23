(ns degas.mutation
  (:require [degas.helpers :refer :all]))

;; --------------
;; Generic helper

(defn mut-apply [ind function]
  "Applies function to random unit of ind."
  (let [point (rand-int (count ind))]
    (assoc ind point (function (nth ind point)))))

(defn mutate [mutate-fn prob ind]
  ;; TODO: use real prob
  ;; TODO: for some reason not always flips at the moment
  (let [pos (rand-int (count ind))]
          (assoc ind pos (mutate-fn (nth ind pos)))))


;; ---------------
;; Binary flipping

(defn bitflip [n] (if (= n 1) 0 1))

(defn mut-bitflip [ind]
  "Returns an individual with one random bit flipped. Works with binary units only."
  (mut-apply ind bitflip))


;; --------
;; Swapping

(defn mut-swap [ind]
  "Returns an individual with two random genes swapped."
  (let [a (rand-int (count ind))
        b (rand-int (count ind))]
    (swap ind a b)))

;; --------
;; Shifting

(defn mut-shift [ind n]
  (shift ind n))

;; -------
;; Inc/dec

(defn mut-inc [ind]
  (mut-apply ind inc))

(defn mut-dec [ind]
  (mut-apply ind dec))


;; -------------------
;; Whole ind mutations

(defn mut-reverse-ind [ind]
  "Returns a reversed ind."
  (apply vector (reverse ind)))


;; -------------------
;; Population mutation

(def mutate-pop [pop ratio]
  ;; todo
  )
