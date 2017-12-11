(ns advent-2017.day1
  (:require [advent-2017.util :refer [with-input]]))

(def input-file "day1/p1.txt")

(defn str-to-int-vector
  [int-str]
  (->> (seq int-str)
       (mapv #(Integer/parseInt (str %)))))

(defn solve
  "Solves both parts of the first problem.
   Takes a function that, given the length of the input, returns the offset value
   to find the paired digit for any index within the input."
  [calc-offset]
  (with-input
    input-file
    (fn [[captcha-str]]
      (let [captcha (str-to-int-vector captcha-str)
            len (count captcha)
            item-pair-offset (calc-offset len)
            get-paired-digit (fn [index]
                               (nth captcha
                                    (mod (+ index item-pair-offset) len)))]
        (->> captcha
             (keep-indexed (fn [index item]
                             (let [pair (get-paired-digit index)]
                               (if (= item pair)
                                 item
                                 nil))))
             (apply +))))))

(defn solve-pt1 [] (solve (constantly 1)))

(defn solve-pt2 [] (solve (fn [len] (/ len 2))))
