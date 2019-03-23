(ns degas.helpers)

;; -------------------
;; Vector manipulation

(defn replace-at [v subv point]
  "Replaces portion of v at point with subv."
  (apply vector (concat
                 (subvec v 0 point)
                 subv
                 (subvec v (+ point (count subv))))))

(defn swap [v i1 i2]
  "Swaps two elements in a sequence."
  (assoc v i2 (v i1) i1 (v i2)))

(defn shift [v n]
  (let [distance (mod n (count v))]
    (concat (subvec v distance) (subvec v 0 distance))))


;; -------
;; Making choices

(defn weighted-rand-choice [m]
  "Given a map of keys and weights, returns one of keys at random, based on weight."
  (let [weights (reductions + (vals m))
        choice  (rand-int (last weights))]
    (nth (keys m) (count (take-while #(<= % choice) weights)))))

(defmacro do-with-prob [prob & body]
  "Do body with probability prob."
  `(if (<= (rand-int 100) (* ~prob 100))
       (do ~@body)))
