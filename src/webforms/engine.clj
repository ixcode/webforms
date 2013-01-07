(ns webforms.engine)

(defn check-name-and-age [data]
  "You must supply a name and age")

(defn q:text [foo]
  (format "Hello %s" foo))

(defn path-to [field]
  (let [{:keys [section id]} field]
    (format "%s%s" section id)))

(def about-you-form 
  {:is :form-definition
   :fields {:first-name {:id :first-name
                         :section :contact-details
                         :html q:text}}
   :validators [check-name-and-age]})

;;(path-to {:id "foo" :section "ho"})
;;(path-to (-> about-you-form :fields :first-name))
;;((-> about-you-form :fields (get 0) :html) "jim")

(let [{:keys [a]} {:a "foo"}]
  a)

(:is about-you-form)
(is? about-you-form)


