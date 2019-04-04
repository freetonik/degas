(ns degas.selection)

;; Helpers
(defn get-fitter [a b fitness-fn]
  "Returns the fittest of the two"
  (first (reverse (sort-by fitness-fn [a b]))))

;; Elitism
(defn top-n [pop n fitness-fn]
  "Returns top n individuals from pop by fitness."
  (->> pop
       (sort-by fitness-fn)
       reverse
       (take n)))

(defn top-portion [pop ratio fitness-fn]
  "Returns the top portion of pop."
  (top-n pop (* ratio (count pop)) fitness-fn))

;; Basic randomized selection
(defn random-selection [pop]
  "Returns a pair of two individuals selected randomly from pop."
  (->> pop
      shuffle
      (take 2)))

;; Tournaments
(defn tournament-round [pop fitness-fn]
  "Returns a 50% population after one round of tournament selection."
  (let [pairs
        (->> pop
             count
             range
             shuffle
             ;; add padding to partition in case of odd size
             ;;              ↓
             (partition 2 2 [(rand-int (count pop))]))]
    (mapv
     (fn [pair]
       (get-fitter (nth pop (first pair)) (nth pop (second pair))
                   fitness-fn ))
     pairs)))

(defn tournament-selection
  [pop fitness-fn]
  "Returns a pair of two individuals selected by continous tournament selection."
  (let [a (atom pop)]
    (while (> (count @a) 2) (reset! a (tournament-round @a fitness-fn)))
    (deref a)))
