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
  [c/dashboard
   {:route :home, :title "Home"}
   [:div "here home page content"]])

(defn- login-page []
  [c/dashboard
   {:route :login, :title "Login"}
   [:div "here login page content"]])

(defn- signup-page []
  [c/dashboard
   {:route :signup, :title "Signup"}
   [:div "here signup page content"]])

(defn- bingo-page []
  [c/dashboard
   {:route :bingo, :title "Welcome to Bingo!"}
   [c/bingo75-card (bingo75/gen-values (js/Date.now))]])

(defn- not-found-page []
  [c/dashboard
   {:title "Not Found"}
   [:div "here not found page content"]])

(defn- app []
  [(if @router-match
     (:view (:data @router-match))
     not-found-page)])

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

(defonce root (rdc/create-root
               (js/document.getElementById "root")))

(defn ^:export init! []
  (start-router)
  (rdc/render root [:> react/StrictMode {} [app]]))
