(ns bingoto.auth
  (:require
   ["firebase/auth" :as fa]))

(defn signup [email password]
  (-> (fa/createUserWithEmailAndPassword (fa/getAuth) email password)
      (.then #(pr (:user %)))
      (.catch #(pr %))))

(defn login [email password]
  (-> (fa/signInWithEmailAndPassword (fa/getAuth) email password)
      (.then #(pr (:user %)))
      (.catch #(pr %))))

(defn logout []
  (-> (fa/signOut (fa/getAuth))
      (.then #(pr "logout"))
      (.catch #(pr %))))
