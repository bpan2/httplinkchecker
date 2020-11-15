# httplinkchecker
Written in java, httplinkchecker is used to check if HTTP links are broken or not

##  1. Java Dev Environment Setup on Ubuntu:
1. Install Java 15:

	Please follow the instruction on the following posting: [Install Oracle Java 15 on Ubuntu using an APT PPA repository](https://www.linuxuprising.com/2020/09/how-to-install-oracle-java-15-on-ubuntu.html)


2. Install Eclipse for Ubuntu using linux terminal:

	2.1 Install Eclipse IDE:
	
		sudo snap install --classic eclipse

		
	2.2 Install Java JRE:
	
		sudo apt install default-jre
		
	Note:
		The detailed instruction can be found at https://linuxconfig.org/ubuntu-20-04-eclipse-installation
		
3. Add the required plugins into Eclipse:

	3.1 Add SpotBugs as the linter:
	
		Open Eclipse, go to Help menu, 
		then open Eclipse Marketplace wizard, on its search tab, search for "Spotbugs", 
		click on the "install" button for SpotBugs Eclipse plugin.
	
	3.2 Add Google Java Format as the source code formatter:
	
		1. Go to Google's Java Format at Github at https://github.com/google/google-java-format
	
		2. Download the jar file for google-java-format-1.9 at https://github.com/google/google-java-format/releases
		
		3. Import the downloaded jar file into Eclipse:
		
			3.1 Project -> Properties -> Java Build Path -> Libraries -> click on Classpath 
				    -> Add External JARs 
				    -> find and select the jar file for google-java-format-1.9-all-deps.jar, and click on open 
				    -> Apply and Close.

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
