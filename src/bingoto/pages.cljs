(ns bingoto.pages
  (:require
   [bingoto.components :as c]
   [bingoto.factories.bingo75 :as bingo75]))

(defn home []
  [c/dashboard
   {:route :home, :title "Home"}
   [:div "here home page content"]])

(defn login []
  [c/dashboard
   {:route :login, :title "Login"}
   [:div "here login page content"]])

(defn signup []
  [c/dashboard
   {:route :signup, :title "Signup"}
   [:div "here signup page content"]])

(defn bingo []
  [c/dashboard
   {:route :bingo, :title "Welcome to Bingo!"}
   [c/bingo75-card (bingo75/gen-values (js/Date.now))]])

(defn not-found []
  [c/dashboard
   {:title "Not Found"}
   [:div "here not found page content"]])
