(ns degas.core
  (:require [degas.individual :refer :all]
            [degas.crossover :refer :all]
            [degas.mutation :refer :all]
            [degas.population :refer :all]
            [degas.selection :refer :all]
            [degas.fitness :refer :all]
            [degas.helpers :refer :all]
            ))

(def population (atom []))
(reset! population (rand-pop 6 2 1 10))

(def tempopulation (atom []))
;; (reset! tempopulation [])

(dotimes [i 20]
  (print "==============\n")
  (print "Generation: " i "\n")
  (print (reverse (sort-by fitness-sum @population)) "\n")

  (print "Best individual: ")
  (print (first (reverse (sort-by fitness-sum @population))))
  (print ", fitness" (fitness-sum (first (reverse (sort-by fitness-sum @population)))) "\n")

  ;; Generate new population entirely from crossover
  (while (not= (count @population) (count @tempopulation))
    (swap! tempopulation conj (apply crossover-50-50 (tournament-selection @population fitness-sum)))
    )

  (let [point (rand-int (count @tempopulation))
        point-2 (rand-int (count @tempopulation))]
    (print "Mutating gene by swapping at point: " point "\n")
    (swap! tempopulation assoc point (mut-swap-genes (nth @tempopulation point)))
    (print "Mutating gene by swapping at point: " point-2 "\n")
    (swap! tempopulation assoc point (mut-inc-genes (nth @tempopulation point)))
    )

  (reset! population @tempopulation)
  (reset! tempopulation [])
  (print "\n\n")
  )
