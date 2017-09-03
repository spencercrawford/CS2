Programmer  : Spencer Crawford
Project     : Homework #1
Date        : 9/2/17
Description : Documentation and Planning for Homework #1

Step 2)

greetUser method:
 - welcome and prompt user
 - (go to main) call the readUserLoginFromUser method:

readUserLoginFromUser Method:
- once the user has entered input, store input in a string called userInput 
	~ need a scanner (System.in)
	~ maybe .nextLine()
 - (go to main) call checkCase method
	
checkCase method:
 - search the input for uppercase and lowercase letters
	~  maybe charAt(0) through charAt(length)
 - if the input is ok, store YES boolean, otherwise NO boolean
	~ need a boolean called upperCase and one called lowerCase
 - (go to main) call checkLength method

checkLength method:
 - search the input for a length of 5 characters or more
	~ maybe .length
 - if the input is ok, store YES boolean, otherwise NO boolean
	~ need a boolean called hasLength
 - (go to main) call checkSpecialCharacter method

checkSpecialCharacter method:
 - search the input for the inclusion of at least one of the following: ! @ # $
	~ maybe a nested for loop
	~ need a boolean called specChar
 - (go to main) call the checkDigit method

checkDigit method:
 - search the input for the inclusion of at least one numerical digit
	~ maybe a nested for loop
	~ need a boolean called hasDigit
 - (go to main) call the checkOtherInvalidCharacter method

checkOtherInvalidCharacter method:
 - search the input for the inclusion of invalid characters such as: % ^ & *
	~ maybe a nested for loop
	~ need a boolean called otherChar
 - (go to main) call the checkSpace method

checkSpace method:
 - search the input for the inclusion of a space or tab
	~ maybe a .contains(“ “)
	~ need a boolean called hasSpace
 - (go to main) call the checkValidity method

checkValidity Method:
 - check status of all booleans
 - (go to main) call printUserLoginValidity method

printUserLoginValidity method:
 - print out the results of the login (Valid or Invalid)
 - if input is ok, print Login: + input + (Valid)
 - if input is not ok, print Login: + input + (Invalid)
 - print reasons why login is invalid based on boolean results
	~ ex) if uppercaseCase boolean is NO, print “Needs at Least One Uppercase Letter”
 - store the output in a string call userReport
 - (go to main) call the addToReport method

addToReport method:
 - add the most recent login report to a cumulative report
	~ need a finalReport variable
 - (to to main) call the printReport method

printReport method:
 - create a file called reportFile
 - save finalReport to the file
