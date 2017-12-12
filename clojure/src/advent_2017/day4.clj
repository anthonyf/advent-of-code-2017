(ns advent-2017.day4
  (:require [advent-2017.util :refer [with-input]]
            [clojure.string :as str]))

(def input-file "day4/p1.txt")

(defn count-words
  [passphrase]
  (->> (str/split passphrase #"\s+")
       frequencies))

(defn count-anagrams
  [passphrase]
  (->> (str/split passphrase #"\s+")
       (map frequencies)
       frequencies))

(defn passphrase-validator
  [counting-fn]
  (fn [passphrase]
    (let [counts (counting-fn passphrase)]
      (reduce (fn [valid? [_ occurrences]]
                (and valid? (= 1 occurrences)))
              true
              counts))))

(defn solve
  [counting-fn]
  (with-input input-file
    (fn [input]
      (->> input
           (map (passphrase-validator counting-fn))
           (filter identity) ;; filter all non-true values
           count))))

(defn solve-pt1 [] (solve count-words))

(defn solve-pt2 [] (solve count-anagrams))
