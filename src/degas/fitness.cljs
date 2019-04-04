(ns degas.fitness
  (:require [clojure.string :as str]))

;; Simplest reduction
(defn fitness-bitsum [ind]
  "Returns a sum of sequence."
  (reduce + ind))

;; Distances
(defn fitness-hamming-distance [ind goal]
  "Returns the hamming distance between ind and goal sequence."
  (count (filter true? (map (partial reduce not=)
                            (map vector (str/join "" ind) (str/join "" goal))))))

(defn pathify [vec]
  "Turns a vector into a vector of transitions,
   including last element back to the first
   ex: [0 1 2] -> [[0 1] [1 2] [2 0]]"
  (map (fn [i]
           (subvec (into vec vec) i (+ 2 i)))
       (range (count vec))))
