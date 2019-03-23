(ns degas.core-test
  (:require [clojure.test :refer :all]
            [degas.core :refer :all]))

(deftest test-crossover-split-50-50
  (let [
        a [[0 0 0] [0 0 0]]
        b [[1 1 1] [1 1 1]]
        ]

    (is (= [[0 0 0] [1 1 1]]
           ))
    ))
