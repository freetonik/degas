(defproject degas "0.2.41"
  :description "Small framework for evolutionary computation in the browser."
  :url         "https://github.com/freetonik/degas"
  :license     { :name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html" }

  :plugins [[lein-cljsbuild "1.1.7"]]

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]]

  :cljsbuild { :builds
              [{ :id "advanced"
                :source-paths ["src"]
                :compiler
                {
                 :output-to      "target/main.js"
                 :optimizations  :advanced
                 :source-map     "target/main.js.map"
                 :pretty-print   false }}]}
  )
