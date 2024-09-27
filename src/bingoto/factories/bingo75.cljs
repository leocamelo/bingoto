(ns bingoto.factories.bingo75
  (:require
   ["pure-rand" :as prand]))

(def balls-len 75)
(def balls (for [i (range balls-len)] (str (inc i))))

(def cols-len 5)
(def rows-len 5)

(def values-len (* cols-len rows-len))
(def balls-group-len (/ balls-len cols-len))

(defn- assoc-value [values col row index]
  (assoc values (+ col (* row cols-len)) (nth balls index)))

(defn- gen-column-values
  ([values rng min-index max-index col]
   (gen-column-values values rng min-index max-index col 0 #{}))

  ([values rng min-index max-index col row memo]
   (if (= row rows-len)
     values
     (let [[i, rng] (prand/uniformIntDistribution min-index max-index rng)]
       (if (contains? memo i)
         (recur values rng min-index max-index col row memo)
         (recur (assoc-value values col row i) rng min-index max-index col (inc row) (conj memo i)))))))

(defn- do-gen-values [values rng]
  (reduce
   #(let [min-index (* %2 balls-group-len)
          max-index (+ min-index (dec balls-group-len))]
      (gen-column-values %1 rng min-index max-index %2))
   values
   (range cols-len)))

(defn gen-values [seed]
  (let [rng (prand/xoroshiro128plus seed)
        values (vec (repeat values-len ""))]
    (assoc (do-gen-values values rng) (/ values-len 2) "")))
