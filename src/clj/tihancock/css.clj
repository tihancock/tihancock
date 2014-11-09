(ns tihancock.css
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]))

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

  (at-media {:min-device-width "600px"}
            [:#photo-container {:position :absolute
                                :margin-bottom "0"
                                :top "10%"
                                :bottom "10%"
                                :left "10%"
                                :right "10%"
                                :border "solid 5px"
                                :border-color :#ADADAD}])
  
  (at-media {:max-device-width "600px"}
            [:#photo-container {:position :absolute
                                :height "100%"
                                :width "100%"}])

  [:.photo {:position :relative
            :max-height "100%"
            :max-width "100%"
            :top "50%"
            :transform "translateY(-50%)"
            :margin-left :auto
            :margin-right :auto}]

  [:.slick-slide :#photo-container {:background "url(\"/images/ajax-loader.gif\") center center no-repeat"}]

  [:.slick-list 
   :.slick-track
   :.slick-slide {:position :relative
                  :height "100%"}]

  [:.slick-prev {:left "40px ! important"}]
  [:.slick-next {:right "40px ! important"}]

  [:.slick-prev :.slick-next {:height "40px ! important"
                              :width  "40px ! important"}]

  [:.slick-prev:before :.slick-next:before {:font-size "40px ! important"}]

  [:.slick-prev:before {:content "\"\\25C0\" ! important"}]
  [:.slick-next:before {:content "\"\\25B6\" ! important"}])
