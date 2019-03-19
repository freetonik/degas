(ns degas.evolution)

(def next-generation [population crossover-fn mutation-fn selection-fn]
  "Returns a new population."
  (selection-fn population)
  )
