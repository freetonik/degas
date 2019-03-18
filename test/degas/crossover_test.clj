(ns degas.crossover-test
  (:require [degas.crossover :refer :all]
            [clojure.test :refer :all]))

(deftest test-crossover-split-50-50
  (let [a [[0 0 0] [0 0 0]]
        b [[1 1 1] [1 1 1]]]

    (is (= [[0 0 0] [1 1 1]]
           (crossover-split 0.5 a b)))))

(deftest test-crossover-split-30-70
  (let [a [[0 0 0] [0 0 0] [0 0 0]]
        b [[1 1 1] [1 1 1] [1 1 1]]]

    (is (= [[0 0 0] [1 1 1] [1 1 1]]
           (crossover-split 0.3 a b)))))
