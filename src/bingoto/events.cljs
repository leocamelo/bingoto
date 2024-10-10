(ns bingoto.events
  (:require
   [re-frame.core :as re-frame]
   [bingoto.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 #(-> db/default-db))

(re-frame/reg-event-db
 ::navigated
 (fn [db [_ new-match]]
   (assoc db :current-route new-match)))

(re-frame/reg-event-db
 ::update-name
 (fn [db [_ new-name]]
   (assoc db :name new-name)))
