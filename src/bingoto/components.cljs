(ns bingoto.components
  (:require
   [reitit.frontend.easy :as rfe]))

(defn navbar []
  [:ul.my-4
   [:li [:a {:href (rfe/href :home)} "Home"]]
   [:li [:a {:href (rfe/href :login)} "Login"]]
   [:li [:a {:href (rfe/href :signup)} "Signup"]]
   [:li [:a {:href (rfe/href :bingo)} "Bingo"]]])

(defn- bingo75-card-head [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-amber-200.font-bold
   value])

(defn- bingo75-card-body [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-zinc-200
   value])

(defn bingo75-card [values]
  (let [header [\B \I \N \G \O]]
    [:div.inline-grid.grid-cols-5.gap-4
     (for [v header] ^{:key v} [bingo75-card-head v])
     (for [v values] ^{:key v} [bingo75-card-body v])]))
