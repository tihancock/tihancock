(ns tihancock.css
  (:require [garden.def :refer [defstyles]]))

(defstyles style
  [:* {:padding "0"
       :margin  "0"
       :background-color :black}]

  [:body {:position :absolute
          :height "100%"
          :width  "100%"}]

  [:.full-screen {:overflow :hidden
                  :position :relative
                  :height   "100%"
                  :width    "100%"}]

  [:.home-page-link-container {:display :inline-block
                               :height "100%"
                               :width "50%"
                               :text-align :center}]

  [:.home-page-link {:position :relative
                     :top "50%"
                     :transform "translateY(-50%)"
                     :display :block
                     :text-decoration :none
                     :color :white
                     :text-align :center
                     :font-size "150%"}]

  [:.home-page-link-image {:display :inline-block
                           :max-width "90%"
                           :max-height "90%"}]

  [:#sidebar {:position :absolute
              :width "calc(20% - 2px)"
              :height "100%"
              :border-right "solid 2px white"}]

  [:#sidebar-link-container {:position :relative
                             :top "50%"
                             :transform "translateY(-50%)"}]

  [:.sidebar-link {:display :block
                   :text-decoration :none
                   :color :white
                   :text-align :center
                   :font-size "150%"}]

  [:#photo-container {:position :absolute
                      :left "20%"
                      :width "80%"
                      :height "100%"}]

  [:#main-photos {:height "80%"
                  :width "100%"
                  :margin-bottom "0"}]

  [:#photo-previews {:display :inline-block
                     :bottom "0"
                     :height "20%"
                     :width "100%"
                     :margin-bottom "0"
                     :border-top "solid 2px white"}]

  [:.main-photo-container    {:height "80%"}]
  [:.preview-photo-container {:height "20%"}]

  [:.main-photo {:position :relative
                 :max-width "100%"
                 :max-height "100%"
                 :top "50%"
                 :transform "translateY(-50%)"
                 :margin-left :auto
                 :margin-right :auto}]

  [:.preview-photo {:height "100%"
                    :width :auto}]

  [:.slick-list {:height "100%"}])
