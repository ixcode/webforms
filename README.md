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

    (form :submit-url "/processme" :method "POST" :tags [loan application]
      (section :id "customer" :title "About you" 
         (q:text :id "first-name" :max-chars 30 :valid-pattern "^[A..Z].*")
         (q:integer :id "age" :range [0 120])
         (q:text :id "biography" :multiline)
          
    ))

Will produce the following data structure:

    {
      :is [loan application form]
       
    }