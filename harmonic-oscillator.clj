(ns harmonic-oscillator.core)

(defn harmonic-motion [A ω φ t]
  (* A (Math/cos (+ (* ω t) φ))))

(defn simulate-motion [A ω φ steps dt]
  (for [t (range 0 (* steps dt) dt)]
    (harmonic-motion A ω φ t)))

(defn -main [& args]
  (let [A 1.0 ; Genlik
        ω 2.0 ; Açısal hız
        φ 0.0 ; Faz açısı
        steps 100
        dt 0.1] ; Zaman adımı
    (println "Basit harmonik hareket:")
    (doseq [x (simulate-motion A ω φ steps dt)]
      (println x))))
