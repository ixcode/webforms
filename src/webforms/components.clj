(ns webforms.components
  (:use [platform.collections :as ix :only [map-with-default]]))

(defn q:text [id & params]
  (let [{:keys [max-chars valid-pattern multiline]} (ix/map-with-default params)]
    (if multiline
      (format "<textarea id='%s' name='%s' maxlength='%s'></textarea>" id id max-chars)
      (format "<input id='%s' name='%s' type='text' maxlength='%s'></input>" id id max-chars))))
