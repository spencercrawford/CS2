Programmer  : Spencer Crawford
Project     : Homework #1
Date        : 9/2/17
Description : Documentation and Planning for Homework #1

Step 1)

Login Specification:
 - 1 uppercase letter
 - 1 lowercase letter
 - 1 numerical digit
 - 1 of the following: ! @ # $
 - length must be at least 5 characters
 - space / tab are not permitted 

Input/Output
 - program reads string from user
 - output could be the following:

Login: Example!17 (Valid)

or perhaps

Login: No0 (Invalid)
—— Needs Special Character (!@#$)
—— Too Short (Minimum of 5 Characters)

Program needs the following methods:
 - main
 - greetUser (brief explanation / prompt)
 - checkCase
 - checkLength
 - checkValidity
 - PrintUser LoginValidity
 - addToReport (concatenates result checking UserLogin to report variable.)
 - printReport (to file)