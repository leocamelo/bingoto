(ns bingoto.components
  (:require
   [reitit.frontend.easy :refer [href]]))

;;; container ;;;

(defn container [content]
  [:div.mx-auto.max-w-7xl.px-4.py-6.sm:px-6.lg:px-8
   content])

;;; navbar ;;;

(defn- navbar-logo []
  [:div.flex-shrink-0
   [:img.h-8.w-8
    {:src "https://tailwindui.com/plus/img/logos/mark.svg?color=indigo&shade=500"
     :alt "Bingoto"}]])

(defn- navbar-link [current-route route label]
  [:a.rounded-md.px-3.py-2.text-sm.font-medium
   {:href (href route)
    :class (if (= current-route route)
             "bg-gray-900 text-white"
             "text-gray-300 hover:bg-gray-700 hover:text-white")}
   label])

(defn navbar [current-route]
  [:nav.bg-gray-800
   [:div.mx-auto.max-w-7xl.px-4.sm:px-6.lg:px-8
    [:div.flex.h-16.items-center.justify-between
     [:div.flex.items-center
      [navbar-logo]
      [:div.hidden.md:block
       [:div.ml-10.flex.items-baseline.space-x-4
        [navbar-link current-route :home "Home"]
        [navbar-link current-route :login "Login"]
        [navbar-link current-route :signup "Signup"]
        [navbar-link current-route :bingo "Bingo"]]]]]]])

;;; header ;;;

(defn header [title]
  [:header.bg-white.shadow
   [container
    [:h1.text-3xl.font-bold.tracking-tight.text-center.text-gray-900
     title]]])

;;; dashboard ;;;

(defn dashboard [attrs content]
  [:div.min-h-full
   [navbar (:route attrs)]
   [header (:title attrs)]
   [:main [container content]]])

;;; bingo75-card ;;;

(defn- bingo75-card-head [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-amber-200.font-bold
   value])

(defn- bingo75-card-body [value]
  [:div.h-20.w-20.place-content-center.text-center.rounded.bg-zinc-200
   value])

(defn bingo75-card [values]
  (let [header [\B \I \N \G \O]]
    [:div.text-center
     [:div.inline-grid.grid-cols-5.gap-4
      (for [v header] ^{:key v} [bingo75-card-head v])
      (for [v values] ^{:key v} [bingo75-card-body v])]]))
