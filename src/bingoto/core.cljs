(ns bingoto.core
  (:require
   ["react" :as react]
   [reagent.dom.client :as rdomc]
   [bingoto.components :as comps]
   [bingoto.factories.bingo75 :as bingo75]))

;; -------------------------
;; Views

(defn app []
  [:div.container.mx-auto.text-center
   [:h1.text-3xl.font-semibold.my-4 "Welcome to Bingoto"]
   [comps/bingo75-card (bingo75/gen-values (js/Date.now))]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (let [root (rdomc/create-root (js/document.getElementById "root"))]
    (rdomc/render root [:> react/StrictMode {} [app]])))

(defn ^:export init! []
  (mount-root))
