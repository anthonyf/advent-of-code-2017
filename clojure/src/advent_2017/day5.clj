(ns advent-2017.day5
  (:require [advent-2017.util :refer [with-input]]))

(def input-file "day5/p1.txt")

(defn parse-input
  [input]
  )

(defn solve-pt1
  []
  (with-input input-file
    (fn [input]
      (let [instructions (reduce #(conj %1 (Integer/parseInt %2)) [] input)
            length (count instructions)]
        (loop [instructions instructions
               pos 0
               steps 0]
          (if (or (>= pos length) (< pos 0))
            steps
            (recur (update instructions pos inc)
                   (+ pos (get instructions pos))
                   (inc steps))))))))

(defn solve-pt2
  []
  (with-input input-file
    (fn [input]
      (let [instructions (reduce #(conj %1 (Integer/parseInt %2)) [] input)
            length (count instructions)]
        (loop [instructions instructions
               pos 0
               steps 0]
          (if (or (>= pos length) (< pos 0))
            steps
            (let [offset (get instructions pos)]
              (recur (assoc instructions pos (if (>= offset 3) (dec offset) (inc offset)))
                     (+ pos (get instructions pos))
                     (inc steps)))))))))
