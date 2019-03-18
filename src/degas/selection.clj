(ns degas.selection)

(defn tournament-selection [pop prob fitness-fn]
  "Returns a new population, individuals selected with prob and sorted by fitness."
  (reverse
   (sort-by fitness-fn (apply vector (random-sample prob pop)))))
