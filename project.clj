(defproject tihancock "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [hiccups "0.3.0"]
                 [garden "1.2.5"]
                 [cljs-http "0.1.17"]
                 [jayq "2.5.2"]]

  :plugins [[lein-garden "0.2.4"]
            [lein-cljsbuild "1.0.3"]
            [lein-pdo "0.1.1"]]

  :aliases {"dev" ["pdo" ["cljsbuild" "auto"] ["garden" "auto"]]}

  :garden {:builds [{:source-paths "src/clj"
                     :stylesheet tihancock.css/style
                     :compiler {:output-to "resources/public/css/style.css"
                                :pretty-print? false}}]}

  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/photo.js"
                                   :optimizations :advanced
                                   :externs ["externs/jquery-1.9.js"
                                             "externs/slick.js"]}}]})
