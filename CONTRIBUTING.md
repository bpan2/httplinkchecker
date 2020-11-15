# httplinkchecker
Written in java, httplinkchecker is used to check if HTTP links are broken or not

##  1. Java Dev Environment Setup on Ubuntu:
#### 	1.1 Prerequisites:
1. Follow the instruction on the following posting: [Install Oracle Java 15 on Ubuntu using an APT PPA repository](https://www.linuxuprising.com/2020/09/how-to-install-oracle-java-15-on-ubuntu.html)

2.     
		
#### 	1.2 After installing the prerequisites:

#### 	1.3 Finally:

#### 	1.4 Frequently Asked Questions (FAQ)

##  2. Known issues
	2.1 jdk version conflicts between the version installed on Windows 10 and the version used by Eclipse(2020-09) 
		On Linux, the following exception is thrown:
				Exception in thread "main" java.lang.UnsupportedClassVersionError: 
				httplinkchecker/LinkChecker has been compiled by a more recent version of
				the Java Runtime (class file version 58.0), this version of the Java Runtime 
				only recognizes class file versions up to 55.0
				
	2.2 The CMD console on Windows system can't produce colored output based on ANSC Codes.
	
##  3. To-Do List:
	3.1 output in color, based on https://github.com/dialex/JColor
	3.2 multithreading
	3.3 handle all checked exceptions
