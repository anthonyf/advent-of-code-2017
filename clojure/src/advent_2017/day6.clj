(ns advent-2017.day6
  (:require [advent-2017.util :as util]
            [clojure.string :as str]))

(def input-file "day6/p1.txt")

(defn pos-of-max
  [banks]
  (->> banks
       (reduce max)
       (.indexOf banks)))

(defn redistribute
  [banks pos]
  (let [length (count banks)
        next-pos #(mod (inc %) length)]
    (loop [block-count (get banks pos)
           banks (assoc banks pos 0)
           pos (next-pos pos)]
      (if (= block-count 0)
        banks
        (recur (dec block-count)
               (update banks pos inc)
               (next-pos pos))))))

(defn solve
  []
  (util/with-input input-file
    (fn [input]
      (loop [banks (util/parse-int-array (first input))
             pos (pos-of-max banks)
             bank-history {banks 1} ;; map of banks configs to how many cycles ago they were seen
             cycle-count 1]
        (let [redistributed-bank (redistribute banks pos)]
          (if (contains? bank-history redistributed-bank)
            {:cycle-count cycle-count
             :loop-size (get bank-history redistributed-bank)}
            (recur redistributed-bank
                   (pos-of-max redistributed-bank)
                   (-> (zipmap (keys bank-history) (map inc (vals bank-history)))
                       (assoc redistributed-bank 1))
                   (inc cycle-count))))))))

(defn solve-pt1
  []
  (:cycle-count (solve)))

(defn solve-pt2
  []
  (:loop-size (solve)))
