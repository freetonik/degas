(ns degas.individual
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(defn rand-unit [max]
  "Returns a single unit integer from 0 to max-1"
  (rand-int max))


(defn rand-gene [length u-max]
  "Returns a vector, a single gene of given length, consisting of units of u-max arity"
  (apply vector (repeatedly length #(rand-unit u-max))))


(defn rand-ind [length gene-len u-max]
  "Returns a vector, an individual of given length, consisting of gene-len genes, consisting of units of u-max arity."
  (apply vector (repeatedly length #(rand-gene gene-len u-max))))


(defn decode [decode-fn ind]
  "Returns a string consistings of decode-fn applied to each gene of ind seq."
  (str/join "" (map decode-fn ind)))
