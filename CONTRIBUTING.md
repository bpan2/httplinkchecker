# httplinkchecker
Written in java, httplinkchecker is used to check if HTTP links are broken or not

##  0. CLI Usage on Windows 10 system:
	0.1 
		0.1.1 Download httplinkchecker Runnable JAR file from the link above.
			Note: work in progress, the functionalities are not fully implemented.
		
		0.1.2 jdk-14.02 needs to be installed and added to the path of Windows system.

	0.2 Syntax: 
		$java -jar httplinkchecker.jar -v arg-1 arg-2 ...

	0.3 Explanation: 
		1. the tool is an executable jar file in java.
			Before using it, on Windows 10's cmd.exe, issue the following command:
				javac
			to check if your machine has installed the latest version of jdk 15 or 14.
			
		2. the CLI arguments are separated by one space key stroke;
		
	  	3. arg-1 and arg-2, etc can be 
			either a directory/folder path 
			or a file name with a regular file extension(such as txt, html) in the current directory/folder
			or both.
			
		4. the options of this tool ALWAYS starts with a single '-' character(the hyphen).
		   So far, the options programmed are: 
			1. -r: recursively read all existing sub-directories/folders for regular files
			2. -v: version information
			3. -h/help: for information about the tool as well as instructions on how to use the tool
