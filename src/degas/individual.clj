(ns degas.individual
  (:require [clojure.string :as str]))

(defn rand-ind [length]
  "Returns a vector, an individual of given length with random elements up to u-max each."
  (apply vector (repeatedly length #(rand-int 10))))

(defn rand-unique-ind [length]
  (shuffle (range length)))


(defn decode [decode-fn ind]
  "Returns a string consistings of decode-fn applied to each element of ind seq."
  (str/join "" (map decode-fn ind)))
