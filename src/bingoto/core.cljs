(ns bingoto.core
  (:require
   ["react" :refer [StrictMode]]
   [reagent.dom.client :as rdomc]
   [re-frame.core :as re-frame]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [bingoto.config :as config]
   [bingoto.events :as events]
   [bingoto.views :as views]))

;;; Routing ;;;

(def routes
  [["/"       {:name :home   :view views/home}]
   ["/login"  {:name :login  :view views/login}]
   ["/signup" {:name :signup :view views/signup}]
   ["/bingo"  {:name :bingo  :view views/bingo}]])

(defn- start-router []
  (rfe/start!
   (rf/router routes)
   #(re-frame/dispatch [::events/navigated %])
   {:use-fragment false}))

;;; Setup ;;;

(defonce root (rdomc/create-root
               (js/document.getElementById "app")))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (rdomc/render root [:> StrictMode {} [views/main]]))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (start-router)
  (mount-root))
