# httplinkchecker
Written in java, httplinkchecker is used to check if HTTP links are broken or not

##  1. Guidelines on developing the tool on Ubuntu:

###  	1.1 Environment Setup:

		[Node.js (npm)](https://nodejs.org/en/download/)

#### 	1.2 Prerequisites:
	
#### 	1.3 After installing the prerequisites:

#### 	1.4 Finally:

#### 	1.5 Frequently Asked Questions (FAQ)

##  2. Known issues
	3.1 jdk version conflicts between the version installed on Windows 10 and the version used by Eclipse(2020-09) 
		On Linux, the following exception is thrown:
				Exception in thread "main" java.lang.UnsupportedClassVersionError: 
				httplinkchecker/LinkChecker has been compiled by a more recent version of
				the Java Runtime (class file version 58.0), this version of the Java Runtime 
				only recognizes class file versions up to 55.0
				
	3.2 The CMD console on Windows system can't produce colored output based on ANSC Codes.
	
##  3. To-Do List:
	4.1 output in color, based on https://github.com/dialex/JColor
	4.2 multithreading
	4.3 handle all checked exceptions
