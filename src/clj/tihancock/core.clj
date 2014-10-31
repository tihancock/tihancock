(ns tihancock.core
  (:require [stasis.core :as stasis]
            [tihancock.pages :refer [pages]]))

(defn get-pages []
  (merge pages
         (stasis/slurp-directory "resources/public" #".*\.(css|js)")))

(def app (stasis/serve-pages (get-pages)))

(defn export []
  (stasis/export-pages pages "resources/public"))
