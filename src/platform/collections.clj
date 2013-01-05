(ns platform.collections)


(defn keyvals [out in default]
  (let [key (first in)
        remains (rest in)]
    (if (and (not (empty? remains))
             (not (keyword? (first remains))))
      [(conj out [key (first remains)])
       (rest remains)]
      [(conj out [key default])
       remains])))


(defn slurp-keyvals [list default]
  (loop [out []
         my-list list]        
    (let [[out* remains] (keyvals out my-list default)]
      (if (empty? remains)
        out*
        (recur out* remains)))))

;; e.g. (map-with-default [:a "foo" :b "bar" :c :d "doom"])
(defn map-with-default 
  "Converts a collection of key-value pairs into a map, but if it finds a single key, gives it a default value."
  ([col] (map-with-default col true))
  ([col default]
      (->> (slurp-keyvals col default)
           flatten
           (apply array-map))))


