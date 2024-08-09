(ns logistic-growth.core)

(defn logistic-growth [r K P0 n]
  (letfn [(next-pop [P]
            (+ P (* r P (- 1 (/ P K)))))]
    (take n (iterate next-pop P0))))

(defn -main [& args]
  (let [r 0.1 ; Büyüme oranı
        K 1000 ; Taşıma kapasitesi
        P0 10 ; Başlangıç popülasyonu
        n 50] ; Adım sayısı
    (println "Popülasyon büyümesi:")
    (doseq [p (logistic-growth r K P0 n)]
      (println p))))
