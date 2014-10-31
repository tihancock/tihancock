(ns tihancock.photo
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [hiccups.core :refer [html]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [jayq.core :refer [$]]
            [hiccups.runtime :as hiccupsrt]))

(enable-console-print!)

(def bucket "https://tihancock-photos.s3.amazonaws.com/")

(defn enslicken! []
  (-> ($ :#photo-previews)
      (.slick (clj->js {:arrows         false
                        :variableWidth  true
                        :slidesToScroll 1
                        :asNavFor       :#main-photos
                        :focusOnSelect  true
                        :centerMode     true
                        :centerPadding  "0px"})))
  (-> ($ :#main-photos)
      (.slick (clj->js {:autoplay       true
                        :autoplaySpeed  "5000"
                        :lazyLoad       :ondemand
                        :arrows         false
                        :slidesToShow   1
                        :slidesToScroll 1
                        :asNavFor       :#photo-previews
                        :fade           true}))))

(defn add-photos! [photos]
  (doseq [[category photo-list] photos]
    (doseq [p photo-list]
      (.slickAdd ($ :#photo-previews)
                 (html [:div {:class (name category)}
                        [:h1 {:class :preview-photo-container}
                         [:img {:data-lazy (str bucket p ".thumb")
                                :class     :preview-photo}]]]))
      (.slickAdd ($ :#main-photos)
                 (html [:div {:class (name category)}
                        [:h1 {:class :main-photo-container}
                         [:img {:data-lazy (str bucket p)
                                :class     :main-photo}]]])))))

(go (let [result (<! (http/get (str bucket "photos.json") {:with-credentials? false}))
          photos (js->clj (:body result))]
      (enslicken!)
      (add-photos! photos)
      (.focus ($ :.slick-list))))
