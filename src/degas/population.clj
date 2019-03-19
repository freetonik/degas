(ns degas.population
  (:require [ :as ]))

(defn rand-pop [length ind-len gene-len u-max]
  "Returns a vector, a population of given length, each individual of ind-len length, consisting of genes of gene-len length, consisting of units of u-max arity."
  (apply vector (repeatedly length #(rand-ind ind-len gene-len u-max))))
