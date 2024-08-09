(ns mandelbrot.core)

(defn mandelbrot [c max-iterations]
  (loop [z 0
         iter 0]
    (if (or (>= iter max-iterations) (> (Math/abs z) 2))
      iter
      (recur (+ (* z z) c) (inc iter)))))

(defn generate-mandelbrot [width height max-iterations]
  (for [y (range height)
        x (range width)]
    (let [c (+ (* (- (/ x width) 0.5) 3) (* (- (/ y height) 0.5) 2i))]
      (mandelbrot c max-iterations))))

(defn -main [& args]
  (let [width 80
        height 40
        max-iterations 1000
        mandelbrot-set (generate-mandelbrot width height max-iterations)]
    (doseq [row (partition width mandelbrot-set)]
      (println (apply str (map #(if (> % 5) "*" " ") row))))))
