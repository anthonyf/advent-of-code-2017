(ns advent-2017.day3)

(def input 368078)

(defn grid-accumulator
  []
  {:grid {}
   :curr-coords nil})

(defn add-coords
  [acc coords id]
  (-> acc
      (assoc :curr-coords coords)
      (assoc-in [:grid coords] id)))

(defn build-grid
  ([]
   (build-grid (fn [acc coords val] (add-coords acc coords val))))
  ([step-fn]
   (fn 
     [acc val]
     (if-let [[x y] (:curr-coords acc)]
       ;; determine what the next coords to be created should be based on the surrounding squares
       (let [grid (:grid acc)
             left [(dec x) y]
             right [(inc x) y]
             up [x (inc y)]
             down [x (dec y)]]
         (cond
           ;; if square exists on left and no square exists above, move up
           (and (contains? grid left) (not (contains? grid up)))
           (step-fn acc up val)

           ;; if square exists below, move left
           (and (contains? grid down) (not (contains? grid left)))
           (step-fn acc left val)

           ;; if square exists on right, move down
           (contains? grid right)
           (step-fn acc down val)

           ;; in all other cases, move right
           :else (step-fn acc right val)))
       ;; Grid is empty, add origin coords
       (step-fn acc [0 0] val)))))

(defn find-coords
  [location-id]
  (let [ids (range 1 (inc location-id))
        {:keys [curr-coords]} (reduce (build-grid) (grid-accumulator) ids)]
    curr-coords))

(defn solve-pt1
  []
  (let [[x y] (find-coords input)]
    (+ (Math/abs x) (Math/abs y))))

(defn adjacent-coords
  [[x y]]
  [[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
   [(dec x) y] [(inc x) y]
   [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]])

(defn solve-pt2
  "This is tricky (read: convoluted).
   Solves part 2 by reducing over 1..input, building a grid per the instructions until a
   coordinate value is found that is greater than the input value. When it is found, the
   reduce is short-circuited. I'm too lazy to come up with something cleaner and less
   side-effecty."
  []
  (let [steps (range 1 (inc input))
        find-first-larger-value
        (build-grid (fn [acc coords _]
                      (let [grid (:grid acc)
                            adjacent-coords (adjacent-coords coords)
                            val (apply + (keep #(get grid %) adjacent-coords))]
                        (cond (> val input) (reduced val) ;; short-circuit!
                              (= val 0) (add-coords acc coords 1)
                              :else (add-coords acc coords val)))))]
    (reduce find-first-larger-value (grid-accumulator) steps)))
