(ns degas.individual-test
  (:require [degas.individual :refer :all]
            [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(deftest test-rand-unit
  (is (<= (rand-unit 4) 4)))


(deftest test-rand-gene
  (is (= (count (rand-gene 10 2)) 10)))


(defn ranged-rand
  "Returns random int in range start <= rand < end"
  [start end]
  (+ start (long (rand (- end start)))))



;; --
;; (s/def ::unit int?)

;; (s/fdef rand-unit
;;   :args (s/cat :max int?)
;;   :ret int?
;;   :fn #(<= (:ret %) (-> % :args :max)))
