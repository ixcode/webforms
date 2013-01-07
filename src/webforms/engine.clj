(ns webforms.engine)

(defn check-name-and-age [data]
  "You must supply a name and age")

(defn q:text [foo]
  (format "Hello %s" foo))

(defn path-to [field]
  (let [{:keys [section name]} field]
    (format "%s%s" section name)))

(def about-you-form 
  {:is [:form :meta]
   :name :about-you
   :tags [:contact]
   :fields [ {:is [:field :meta]
              :name :first-name
              :section :contact-details
              :html q:text
              :options [:max-length 20]
              :validators [[range 0 10]]}
             {:is [:field :meta]
              :name :second-name
              :section :contact-details
              :html q:text}]
   :validators [check-name-and-age]})

(defn html-field [field]
  (path-to field))

;; TODO - we should be able to use multimethods here, keyed on the type from :is
(defn html-for [form]
  (let [{:keys [name fields]} form]
    (map html-field (-> about-you-form :fields))))

(html-for about-you-form)



;;(path-to {:id "foo" :section "ho"})
;;(path-to (-> about-you-form :fields :first-name))
;;((-> about-you-form :fields (get 0) :html) "jim")

;;(println (map path-to (-> about-you-form :fields)))





