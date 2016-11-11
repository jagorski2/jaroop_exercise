# Bank Account Simulator
##Build Instructions

To compile and run this program please do the following:

1. Download all files in this repository

2. Navigate to the checked out repository and run "C:\path\to\javac.exe -cp jsoup-1.10.1.jar jaroop.java"

3. Run the program with "C:\path\to\java.exe -cp .;jsoup-1.10.1.jar Jaroop Jaroop.class"

Note: log.html has to be in the same directory as the executable.


## Short Explanation
I used a switch statement for handling which prompt should be displayed because that same method worked well for me in a side project I was just recently working on. To make sure the input was valid I created a function to make sure the input met the given criteria before I sent it to my other function to add it to the log.html file.

I used an external library called jsoup to handle the html parsing. It was much quicker to use this library than to write my own parser and using third party libraries was allowed for the exercise.
