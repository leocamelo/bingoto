(ns bingoto.views
  (:require
   [re-frame.core :as re-frame]
   [bingoto.components :as c]
   [bingoto.events :as events]
   [bingoto.subs :as subs]
   [bingoto.factories.bingo75 :as bingo75]))

(defn home []
  (let [name (re-frame/subscribe [::subs/name])]
    [c/dashboard
     {:route :home, :title "Home"}
     [:div
      [:h2.text-xl.mb-4
       "Hello " @name "!"]
      [:label
       {:for "name"} "Name: "]
      [:input
       {:type "text"
        :id "name"
        :value @name
        :on-change #(re-frame/dispatch
                     [::events/update-name
                      (-> % .-target .-value)])}]]]))

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

(defn main []
  (let [current-route (re-frame/subscribe [::subs/current-route])]
    [(if @current-route (:view (:data @current-route)) not-found)]))
