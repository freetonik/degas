(ns degas.evolution
  (:require [degas.helpers :as h]
            [degas.mutation :as m]
            [degas.selection :as s]))

(defn next-generation
  "Returns a new generation."
  [pop fitness-fn selections-map crossovers-map mutations-map mutation-ratio elitism-ratio]
  (let [
        popsize         (count pop)
        elite-count     (Math/floor (* elitism-ratio popsize))
        offspring-count (- popsize elite-count)

        selection-fn    (h/weighted-rand-choice selections-map)
        crossover-fn    (h/weighted-rand-choice crossovers-map)

        sorted-pop      (vec (sort-by fitness-fn pop))
        elite-pop       (vec (take elite-count sorted-pop))

        breedable-pop   (m/mutate-pop (take-last offspring-count sorted-pop)
                                      mutations-map
                                      mutation-ratio)]

      (into elite-pop (repeatedly offspring-count
                                #(apply crossover-fn
                                        (selection-fn breedable-pop fitness-fn))))))
