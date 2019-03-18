(ns degas.mutation)

(defn mutate [mutate-fn prob ind]
  ;; TODO: use real prob
  ;; TODO: for some reason not always flips at the moment
  (let [pos (rand-int (count ind))]
          (assoc ind pos (mutate-fn (nth ind pos)))))

;; ---

(defn mut-flip-bits [gene]
  "Returns a vector, gene with all bits flipped. Works with binary units only."
  (defn flip [n]
    (if (= n 1) 0
        1))
  (mapv flip gene))

(defn mut-reverse [gene]
  "Returns a vector, gene reversed."
  (apply vector (reverse gene)))
