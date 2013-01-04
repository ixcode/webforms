# WebForms

* A library designed to make it easy to build and process componentised web forms.


* You can:

  - Declaritvely specify form structure (like a DSL)
  - Use some preconfigured components (e.g. text-box, drop-down, lookup, radio, rate-this)
  - Design your own components
  - Delarcatively state validation that needs to be performed
  - Map form data into standard clojure datastructures (which can then easily be transformed into JSON / XML / SQL - for storage
  - Have muli-page/section/step forms with navigation between them and progress indication
  - Choose different CSS themes for the forms

* Future things:
  - Export to PDF?
  - Digital signatures?
  - Encryption

# Examples

The following code:

```lisp
(defn check-name-and-age [data]
   false)

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


```

Will produce the following data structure:

```lisp
    {
      :is [:loan :application :form]
      :validation-messages [
        { :message "You must ented a valid #{first name|:customer :first-name} and #{age | :customer :age}"}
        { :message "Please check the age you have entered" :link-to [:customer :age]}
      ]
      :customer {
        :first-name {:value "Johnny"}
        :age {:value 340 :validation-messages ["You must be aged between 18 and 160 to apply for this loan"]}
        :reason-for-loan {:value "Some long winded diatribe about why I needs ca$h!"}
      }
    }
```

You can do things with this data structure:

```lisp
    (field-value :customer :age)    
    -> 340
```

To chain forms together:

```lisp
(form-sequence :loan-details :about-you :current-financials)
```