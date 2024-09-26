(ns bingoto.core
  (:require
   ["react" :as react]
   [reagent.dom.client :as rdomc]))

;; -------------------------
;; Views

(defn bingo-75-ball-card-head [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-amber-200.font-bold
   value])

(defn bingo-75-ball-card-body [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-zinc-200
   value])

(defn bingo-75-ball-card []
  (let [header [\B \I \N \G \O]]
    [:div.inline-grid.grid-cols-5.gap-4
     (for [v header] ^{:key v} [bingo-75-ball-card-head v])
     (for [v (range 25)] ^{:key v} [bingo-75-ball-card-body (inc v)])]))

(defn app []
  [:div.container.mx-auto.text-center
   [:h1.text-3xl.font-semibold.my-4 "Welcome to Bingoto"]
   [bingo-75-ball-card]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (let [root (rdomc/create-root (js/document.getElementById "root"))]
    (rdomc/render root [:> react/StrictMode {} [app]])))

(defn ^:export init! []
  (mount-root))
