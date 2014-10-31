(ns tihancock.pages
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [clojure.java.io :refer [file]]))

(def links [{:link "/photography"
             :title "Photography"}
            {:link "/programming"
             :title "Programming"}])

(defn page [content]
  (html5 [:head (include-css "/css/style.css"
                             "//cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.3.13/slick.css")]
         content))

(def sidebar
  [:div {:id :sidebar}
   [:div {:id :sidebar-link-container}
    (for [{link :link title :title} links]
      [:div {:id title}
       [:a {:class :sidebar-link
            :href link}
        title]])]])

(def index
  (page [:body [:div {:class :full-screen}
                (for [{link :link title :title} links]
                  [:div {:class :home-page-link-container}
                   [:a {:class :home-page-link
                        :href link}
                    [:img {:class :home-page-link-image
                           :src (str "/images" link ".jpg")}]
                    title]])]]))

(def photo
  (page [:body [:div {:class :full-screen}
                sidebar
                [:div {:id :photo-container}
                 [:div {:id :main-photos}]
                 [:div {:id :photo-previews}]]
                (include-js "//code.jquery.com/jquery-1.9.0.min.js"
                            "//cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.3.13/slick.min.js"
                            "/js/photo.js")]]))

(def programming
  (page [:body [:div {:class :full-screen}
                sidebar]]))

(def pages {"/index.html"   index
            "/photography/" photo
            "/programming/" programming})
