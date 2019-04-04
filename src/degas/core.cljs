(ns degas.core
  (:require [degas.individual :refer :all]
            [degas.crossover :refer :all]
            [degas.mutation :refer :all]
            [degas.population :refer :all]
            [degas.selection :refer :all]
            [degas.fitness :refer :all]
            [degas.helpers :refer :all]
            ))

;; ----

(def distances-2 [[0 1 3 7]
                  [1 0 1 4]
                  [3 1 0 3]
                  [7 4 3 0]])

(def distances [[ 0   28  57  72  81  85  80  113 89  80  ]
                [ 28  0   28  45  54  57  63  85  63  63  ]
                [ 57  28  0   20  30  28  57  57  40  57  ]
                [ 72  45  20  0   10  20  72  45  20  45  ]
                [ 81  54  30  10  0   22  81  41  10  41  ]
                [ 85  57  28  20  22  0   63  28  28  63  ]
                [ 80  63  57  72  81  63  0   80  89  113 ]
                [ 113 85  57  45  41  28  80  0   40  80  ]
                [ 89  63  40  20  10  28  89  40  0   40  ]
                [ 80  63  57  45  41  63  113 80  40  0   ]])

(defn get-distance [[a b]]
  (get-in distances-2 [a b]))

(defn pathify [vec]
  "Turns a vector into a vector of transitions,
   including last element back to the first
   ex: [0 1 2] -> [[0 1] [1 2] [2 0]]"
  (map (fn [i]
           (subvec (into vec vec) i (+ 2 i)))
       (range (count vec))))

(defn fitness-tsm [ind]
  "Returns the total distance travelled for a given solution"
  (let [path (pathify ind)
        distances (map get-distance path)]
    (reduce + distances)))

;; ----

(def population (atom []))
(reset! population (rand-pop 10 4 rand-unique-ind))

(def tempopulation (atom []))
;; (reset! tempopulation [])

(def best (atom (first @population)))

(dotimes [i 1000]
  (print "==============\n")
  (print "Generation: " i "\n")
  ;; (print (sort-by fitness-tsm @population) "\n")

  (print "Best individual: ")
  (print (first (sort-by fitness-tsm @population)))
  (print ", fitness" (fitness-tsm (first (sort-by fitness-tsm @population))) "\n")

  ;; elitism: take 3 top inds and put into next gen
  (reset! tempopulation
          (into [] (concat (apply vector (take 3 (sort-by fitness-tsm @population))))))

  ;; Generate new population entirely from crossover
  (while (not= (count @population) (count @tempopulation))
    (swap! tempopulation conj (apply crossover-ordered (conj (tournament-selection @population fitness-tsm) 2 2)))
    )

  (dotimes [j 5]
    (let [index (rand-int (count @tempopulation))]
      (swap! tempopulation assoc index (mut-swap (nth @tempopulation index)))))

  ;; (let [point (rand-int (count @tempopulation))
  ;;       point-2 (rand-int (count @tempopulation))]
  ;;   (print "Mutating gene by swapping at point: " point "\n")
  ;;   (swap! tempopulation assoc point (mut-swap (nth @tempopulation point)))
  ;;   (print "Mutating gene by swapping at point: " point-2 "\n")
  ;;   (swap! tempopulation assoc point (mut-inc (nth @tempopulation point)))
  ;;   )

  (reset! population @tempopulation)
  (reset! tempopulation [])

  (if (< (fitness-tsm (first (sort-by fitness-tsm @population))) (fitness-tsm @best))
    (reset! best (first (sort-by fitness-tsm @population)))
    )

  (print "\n")
  )

(print "Best: " @best "\nFitness: " (fitness-tsm @best))
