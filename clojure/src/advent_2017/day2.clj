(ns advent-2017.day2
  (:require [advent-2017.util :as util]
            [clojure.string :as str]))

(def input-file "day2/p1.txt")

(defn input->2d-vector
  [input]
  (mapv (fn [line]
          (mapv #(Integer/parseInt %)
                (str/split line #"\s+")))
        input))

(defn row-checksum
  [row]
  (let [sorted-row (sort row)
        min (first sorted-row)
        max(last sorted-row)]
    (- max min)))

(defn row-quotient
  [row]
  (let [sorted-row (sort row)]
    (loop [dividend-index 1
           divisor-index 0]
      (let [quotient (/ (nth sorted-row dividend-index)
                        (nth sorted-row divisor-index))]
        (cond (= dividend-index divisor-index) (recur (inc dividend-index) 0)
              (int? quotient) quotient
              :else (recur dividend-index (inc divisor-index)))))))

(defn solve
  [row-operation]
  (util/with-input input-file
    (fn [input]
      (let [rows (input->2d-vector input)]
        (apply + (map row-operation rows))))))

(defn solve-pt1 [] (solve row-checksum))

(defn solve-pt2 [] (solve row-quotient))
