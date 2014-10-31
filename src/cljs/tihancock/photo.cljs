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
                        :asNavFor       :#main-photo
                        :focusOnSelect  true
                        :centerMode     true
                        :centerPadding  "0px"})))
  (-> ($ :#main-photo)
      (.slick (clj->js {:lazyLoad       :ondemand
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
                        [:h1 [:img {:src   (str bucket p ".thumb")
                                    :class :photo}]]]))
      (.slickAdd ($ :#main-photo)
                 (html [:div {:class (name category)}
                        [:h1 [:img {:data-lazy (str bucket p)
                                    :class     :photo}]]])))))

(go (let [result (<! (http/get (str bucket "photos.json") {:with-credentials? false}))
          photos (js->clj (:body result))]
      (enslicken!)
      (add-photos! photos)))
