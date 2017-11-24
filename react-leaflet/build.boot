(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.9.0-SNAPSHOT" :scope "test"]
                  [cljsjs/react "15.6.2-1"]
                  [cljsjs/react-dom "15.6.2-1"]
                  [cljsjs/leaflet "1.1.0-2"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.6.5")
(def +version+ (str +lib-version+ "-0"))

(task-options!
 pom  {:project     'cljsjs/react-leaflet
       :version     +version+
       :description "JavaScript Library for Mobile-Friendly Interactive Maps"
       :url         "https://github.com/PaulLeCam/react-leaflet"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
    (download :url      (str "https://unpkg.com/react-leaflet@" +lib-version+ "/dist/react-leaflet.js")
              :target   "cljsjs/react-leaflet/development/react-leaflet.inc.js")
    (download :url      (str "https://unpkg.com/react-leaflet@" +lib-version+ "/dist/react-leaflet.min.js")
              :target   "cljsjs/react-leaflet/production/react-leaflet.min.inc.js")
    (deps-cljs :provides ["react-leaflet" "cljsjs.react-leaflet"]
               :requires ["leaflet" "react" "react-dom"]
               :global-exports '{react-leaflet ReactLeaflet})
    (pom)
    (jar)
    (validate)))
