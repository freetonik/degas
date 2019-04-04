(defproject degas "0.2.3"
  :description "FIXME: write description"
  :url         "http://example.com/FIXME"
  :license     { :name "Eclipse Public License"
                 :url "http://www.eclipse.org/legal/epl-v10.html" }

  :plugins
  [[lein-cljsbuild "1.1.7"]]

  :dependencies
  [[org.clojure/clojure "1.10.0"]
   [org.clojure/clojurescript "1.10.520"]]

  :cljsbuild
  { :builds
    [{ :id "advanced"
       :source-paths ["src" "test"]
       :compiler
       { :main           degas.core
         :output-to      "target/main.js"
         :optimizations  :advanced
         :source-map     "target/main.js.map"
        :pretty-print   false }}]}

  )
