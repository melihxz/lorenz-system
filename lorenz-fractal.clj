(ns lorenz-fractal.core
  (:require [clojure.math.numeric-tower :as math]))

(def sigma 10.0)
(def rho 28.0)
(def beta (/ 8.0 3.0))
(def dt 0.01)
(def max-iterations 10000)

(defn lorenz [x y z]
  (let [dx (* sigma (- y x))
        dy (- (* x (- rho z)) y)
        dz (- (* x y) (* beta z))]
    [(+ x (* dx dt))
     (+ y (* dy dt))
     (+ z (* dz dt))]))

(defn simulate-lorenz [initial-state steps]
  (loop [state initial-state
         step 0
         results []]
    (if (>= step steps)
      results
      (let [[x y z] state
            new-state (lorenz x y z)]
        (recur new-state (inc step) (conj results new-state))))))

(defn fractal-dimension [points]
  (let [dists (for [p1 points
                    p2 points
                    :when (not= p1 p2)]
                (math/abs (- (math/sqrt (math/pow (- (first p1) (first p2)) 2)
                              (math/pow (- (second p1) (second p2)) 2)))))
        sorted-dists (sort dists)
        log-dists (map math/log sorted-dists)]
    (let [log-dist-points (map-indexed #(vector (math/log (+ %1 1)) (log-dists %2)) (range (count log-dists)))]
      (apply max (map #(- (second %) (first %)) (partition 2 1 log-dist-points))))))

(defn -main [& args]
  (let [initial-state [0.1 0.0 0.0]
        steps max-iterations
        lorenz-data (simulate-lorenz initial-state steps)
        xy-points (map (fn [[x y z]] [x y]) lorenz-data)
        fractal-dim (fractal-dimension xy-points)]
    (println "Lorenz Sistemi Simülasyonu ve Fraktal Analiz:")
    (println "Fraktal Boyut:" fractal-dim)))
