(ns degas.fitness)

(defn bitsum [gene]
  "Returns a sum of sequence."
  (reduce + gene))

(defn fitness-sum [ind]
  "Returns integer sum of all units in an individual."
  (reduce + (map bitsum ind)))
