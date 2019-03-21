(ns degas.fitness
  (:require [clojure.string :as str]))

;; -------------------
;; Simplest reductions

(defn fitness-bitsum [ind]
  "Returns a sum of sequence."
  (reduce + ind))


;; ---------
;; Distances

(defn fitness-matching-distance [ind goal]
  (map-indexed
   (fn [idx itm]
     ;; todo
     )
   ind))

(defn fitness-hamming-distance [ind goal]
  (count (filter true? (map (partial reduce not=)
                            (map vector (str/join "" ind) (str/join "" goal))))))

;; (fitness-hamming-distance [1 1 1] [1 0 0])
