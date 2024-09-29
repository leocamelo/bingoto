(ns bingoto.core
  (:require
   ["react" :as react]
   [reagent.core :as r]
   [reagent.dom.client :as rdc]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [bingoto.components :as c]
   [bingoto.factories.bingo75 :as bingo75]))

;; -------------------------
;; State

(defonce router-match (r/atom nil))

;; -------------------------
;; Views

(defn- home-page []
  [:h1 "Home Page"])

(defn- login-page []
  [:h1 "Login Page"])

(defn- signup-page []
  [:h1 "Signup Page"])

(defn- bingo-page []
  [:div.text-center
   [:h1.text-3xl.font-semibold.my-4 "Welcome to Bingo!"]
   [c/bingo75-card (bingo75/gen-values (js/Date.now))]])

(defn- not-found-page []
  [:h1 "Not Found Page"])

(defn- app []
  [:div.container.mx-auto
   [c/navbar]
   [(if @router-match
      (:view (:data @router-match))
      not-found-page)]])

;; -------------------------
;; Routes

(def routes
  [["/"       {:name :home   :view home-page}]
   ["/login"  {:name :login  :view login-page}]
   ["/signup" {:name :signup :view signup-page}]
   ["/bingo"  {:name :bingo  :view bingo-page}]])

(defn- start-router []
  (rfe/start!
   (rf/router routes)
   #(reset! router-match %)
   {:use-fragment false}))

;; -------------------------
;; Initialize app

(defonce root (rdc/create-root (js/document.getElementById "root")))

(defn ^:export init! []
  (start-router)
  (rdc/render root [:> react/StrictMode {} [app]]))
