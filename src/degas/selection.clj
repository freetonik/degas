(ns degas.selection)

;; -------
;; Helpers
(defn get-fitter [a b fitness-fn]
  "Returns the fittest of the two"
  (first (sort-by fitness-fn [a b])))


;; --------------------------
;; Basic randomized selection

(defn random-selection [pop & opts]
  "Returns a pair of two individuals selected randomly from pop."
  (->> pop
      shuffle
      (take 2)))

(defn basic-selection-pop [pop ratio fitness-fn]
  "Returns a random subpopulation."
  (->> pop
       (sort-by fitness-fn)
       (take (* ratio (count pop)))))


;; -----------
;; Tournaments

(defn tournament-round [pop fitness-fn]
  "Returns a population after one round of tournament selection."
  (let [pairs
        (->> pop
             count
             range
             shuffle
             (partition 2 2 [(rand-int (count pop))]))]
    (mapv
     (fn [pair] (get-fitter (nth pop (first pair)) (nth pop (second pair)) fitness-fn ))
     pairs)))


(defn tournament-selection [pop fitness-fn]
  "Returns a pair of two individuals selected by continous tournament selection."
  (let [a (atom pop)]
    (while (> (count @a) 2) (reset! a (tournament-round @a fitness-fn)))
    (deref a)))
