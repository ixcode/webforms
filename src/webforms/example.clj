(ns webforms.example)

(defn q:text [id & more]
  (let ))

(form "about-you" :submit-url "/processme" :method "POST" :tags [loan application]
  (section :id "customer" :title "Basic details" 
    (q:text "first-name" :max-chars 30 :valid-pattern "^[A..Z].*")
    (q:integer "age" 
       (v:range [18 60] 
         :summary "Please check the age you have entered" 
         :field "You must be aged between #{start} and #{end} to apply for this loan"))
    (q:text "reason-for-loan" :multiline)
    (validate-with 'check-name-and-age "You must ented a valid #{first name|:customer :first-name} and #{age | :customer :age}")
))

(def form-data {
   :is [:loan :application :form]
   :validation-messages [{ :message "You must ented a valid #{first name|:customer :first-name} and #{age | :customer :age}"}
                         { :message "Please check the age you have entered" :link-to [:customer :age]}]

                :customer {:first-name {:value "Johnny"}
                           :age {:value 340 :validation-messages ["You must be aged between 18 and 160 to apply for this loan"]}
                           :reason-for-loan {:value "Some long winded diatribe about why I needs ca$h!"}
                           }})

;;(get-in form-data [:customer :age :value])

(defn i-take-a-map [id & more]  
  (let [map (apply hash-map more)]
    (format "My id is [%s] and the map is %s" id map)))

(apply hash-map [:a "foo" :b])

(source hash-map)
(doc apply)

(apply + [1 2 3])

(hash-map {} [:a "foo"])


(defn arg 
  ([key value] (println "key / value")))

(defn arg-map [& list]
  (apply arg list))

(defn assoc-key-value [map list]
  (let [key (first list)
        remains (rest list)]
    (if (and (not (empty? remains))
             (not (keyword? (first remains))))
      [(assoc map key (first remains))
       (rest remains)]
      [(assoc map key true)
       remains])))

(defn args [list]
  (loop [map {}
         my-list list]        
    (let [[new-map remains] (assoc-key-value map my-list)]
         (if (empty? remains)
           new-map
           (recur new-map remains)))))



