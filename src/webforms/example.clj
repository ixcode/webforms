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

