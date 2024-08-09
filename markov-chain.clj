(ns markov-chain.core)

(defn transition [state transition-matrix]
  (let [probabilities (transition-matrix state)]
    (rand-nth (keys probabilities))))

(defn simulate-markov-chain [initial-state transition-matrix steps]
  (loop [current-state initial-state
         step 0
         states [initial-state]]
    (if (>= step steps)
      states
      (let [new-state (transition current-state transition-matrix)]
        (recur new-state (inc step) (conj states new-state))))))

(defn -main [& args]
  (let [initial-state :A
        transition-matrix {:A {:A 0.9 :B 0.1}
                           :B {:A 0.5 :B 0.5}}
        steps 10]
    (println "Markov Zinciri Simülasyonu:")
    (println (simulate-markov-chain initial-state transition-matrix steps))))
