(ns degas.individual-test
  (:require [degas.individual :refer :all]
            [clojure.test :refer :all]
            [clojure.spec.alpha :as s]))

;; (s/def ::unit int?)

(deftest test-rand-unit
  (is (<= (rand-unit 4) 4)))

(deftest test-rand-gene
  (is (= (count (rand-gene 10 2)) 10)))
