(ns bingoto.core
  (:require
   ["react" :as react]
   ["firebase/app" :as f]
   ["firebase/analytics" :as fa]
   [reagent.core :as r]
   [reagent.dom.client :as rdc]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [bingoto.pages :as p]))

;; -------------------------
;; State

(defonce router-match (r/atom nil))

;; -------------------------
;; Routes

(def routes
  [["/"       {:name :home   :view p/home}]
   ["/login"  {:name :login  :view p/login}]
   ["/signup" {:name :signup :view p/signup}]
   ["/bingo"  {:name :bingo  :view p/bingo}]])

(defn- start-router []
  (rfe/start!
   (rf/router routes)
   #(reset! router-match %)
   {:use-fragment false}))

;; -------------------------
;; Initialize app

(defonce fb-app (f/initializeApp
                 #js {:apiKey "AIzaSyAu9PvuxsHj02xlxKVPSRG_qxS78xDLKuM",
                      :authDomain "bingoto.firebaseapp.com",
                      :projectId "bingoto",
                      :storageBucket "bingoto.appspot.com",
                      :messagingSenderId "320204401616",
                      :appId "1:320204401616:web:2405e881e776f7494c67d5",
                      :measurementId "G-XWMY4Z01M6"}))

(defonce fb-analytics (fa/getAnalytics
                       fb-app))

(defonce root (rdc/create-root
               (js/document.getElementById "root")))

(defn- app []
  [(if @router-match
     (:view (:data @router-match))
     p/not-found)])

(defn ^:export init! []
  (start-router)
  (rdc/render root [:> react/StrictMode {} [app]]))
