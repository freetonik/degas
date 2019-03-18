(ns degas.crossover)

;; (defn crossover [crossover-fn a b & args]
;;   "Applies crossover-fn to a and b."
;;   (crossover-fn a b args))

(defn crossover-split [a b percent]
  (let [a-len (Math/ceil (* percent (count a)))
        b-len (- (count b) a-len)
        ]
    (apply vector
           (concat
            (take a-len a)
            (take-last b-len b)
            ))))

(defn crossover-50-50 [a b]
  (crossover-split a b 0.5))

(defn crossover-30-70 [a b]
  (crossover-split a b 0.3))

(defn crossover-25-75 [a b]
  (crossover-split a b 0.25))
