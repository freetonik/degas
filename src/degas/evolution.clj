(ns degas.evolution)

(def next-generation [population fitness-fn selection-fn selection-prob crossover-fn mutation-fn]
  "Returns a new population."
  (selection-fn population selection-prob fitness-fn)
  )
