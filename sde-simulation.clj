(ns sde-simulation.core
  (:require [clojure.math.numeric-tower :as math]))

(defn gbm [S0 mu sigma dt]
  (let [dW (math/sqrt dt)]
    (* S0 (Math/exp (+ (* mu dt) (* sigma dW (rand-normal)))))))

(defn rand-normal []
  (let [u1 (rand)
        u2 (rand)
        r (Math/sqrt (- (* -2 (Math/log u1)) (Math/pow (* 2 Math/PI) u2)))
        theta (* 2 Math/PI u2)]
    (* r (Math/cos theta))))

(defn simulate-gbm [S0 mu sigma dt steps]
  (loop [S S0
         step 0
         results [S0]]
    (if (>= step steps)
      results
      (let [new-S (gbm S mu sigma dt)]
        (recur new-S (inc step) (conj results new-S))))))

(defn -main [& args]
  (let [S0 100.0 ; Başlangıç fiyatı
        mu 0.05 ; Ortalama getiri
        sigma 0.2 ; Volatilite
        dt 0.01 ; Zaman adımı
        steps 1000]
    (println "Geometric Brownian Motion Simülasyonu:")
    (doseq [price (simulate-gbm S0 mu sigma dt steps)]
      (println price))))
