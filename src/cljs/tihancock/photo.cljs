(ns tihancock.photo
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [hiccups.core :refer [html]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [jayq.core :refer [$]]
            [hiccups.runtime :as hiccupsrt]
            [cljs.reader :refer [read-string]]))

(def bucket "https://tihancock-photos.s3.amazonaws.com/")

(defn enslicken! []
  (-> ($ :#photo-container)
      (.slick (clj->js {:autoplay       true
                        :autoplaySpeed  "5000"
                        :lazyLoad       :ondemand
                        :arrows         true
                        :slidesToShow   1
                        :slidesToScroll 1
                        :fade           true}))))


(defn add-photos! [photos]
  (doseq [p photos]
    (.slickAdd ($ :#photo-container)
               (html [:div
                      [:img {:data-lazy (str bucket p)
                             :class     :photo}]]))))

(go (let [result (<! (http/get (str bucket "photos.json") {:with-credentials? false}))
          photos (read-string (:body result))]
      (enslicken!)
      (add-photos! photos)
      (.focus ($ :.slick-list))))
