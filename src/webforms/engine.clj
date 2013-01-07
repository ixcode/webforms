(ns webforms.engine)

(defn check-name-and-age [data]
  "You must supply a name and age")

(defn q:text [field]
  (let [path (path-to field)
        {:keys [max-length]} (:options field)]
    (format "<input name='%s' type='text' max-length='%s'></input>" path max-length)))

;;(q:text (-> about-you-form :fields first))


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
              :html-fn q:text
              :options {:max-length 20}
              :validators [[range 0 10]]}
             {:is [:field :meta]
              :name :second-name
              :section :contact-details
              :html-fn q:text
              :options {:max-length 10}}]
   :validators [check-name-and-age]})

(defn html-field [field]  
  ((:html-fn field) field))

;; TODO - we should be able to use multimethods here, keyed on the type from :is
(defn html-for [form]
  (let [{:keys [name fields]} form]
    (apply str (map html-field (-> about-you-form :fields)))))

(println  (html-for about-you-form))



;;(path-to {:id "foo" :section "ho"})
;;(path-to (-> about-you-form :fields :first-name))
;;((-> about-you-form :fields first :html-fn) "jim")

;;(println (map path-to (-> about-you-form :fields)))





