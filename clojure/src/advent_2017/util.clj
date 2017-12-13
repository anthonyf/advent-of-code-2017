(ns advent-2017.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn with-input
  "Applies the supplied function to a line-seq of the specified resource file"
  [file-name f]
  (with-open [rdr (-> (io/resource file-name)
                      (io/reader))]
    (-> (line-seq rdr)
        f)))

(defn parse-int-array
  [input-line]
  (->> (str/split input-line #"\s+")
       (mapv #(Integer/parseInt %))))
