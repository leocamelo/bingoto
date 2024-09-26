(ns bingoto.factories.bingo75)

(defn values []
  (for [i (range 25)]
    (str (inc i))))
