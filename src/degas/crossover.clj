(ns degas.crossover)

;; (defn crossover [crossover-fn a b & args]
;;   "Applies crossover-fn to a and b."
;;   (crossover-fn a b args))

;; -------------------------
;; Simple customizable split

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


;; -----------
;; Specialized

(defn crossover-ordered
  "Returns a vector constructed by placing a section of b at point, of given length and adding elements of a in order. e.g.: [0 1 2 3] [3 2 1 0] 1 2 -> [3 1 2 0]. Random point and length if not provided. "
  ([a b point length]
  (let [subv   (subvec a point (+ point length))
        bprime (filterv #(not (contains? (set subv) %)) b)]

    (apply vector (concat
                   (subvec bprime 0 point)
                   subv
                   (subvec bprime point)))))
  ([a b]
  (let [
        point (rand-int (count a))
        length (rand-int (- (count a) point))]
    (crossover-ordered a b point length))))

;; ----
;; TODO
;; (defn crossover-interleave [a b length])
;; (defn crossover-2point-split [a b p1 p2])
;; crossover with two children?
