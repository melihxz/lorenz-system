(ns lorenz-simulation.core)

(def sigma 10.0)
(def rho 28.0)
(def beta (/ 8.0 3.0))
(def dt 0.01)

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

(defn -main [& args]
  (let [initial-state [0.1 0.0 0.0]
        steps 10000
        result (simulate-lorenz initial-state steps)]
    (doseq [[x y z] result]
      (println (str x ", " y ", " z)))))
