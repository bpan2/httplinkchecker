//Message class is a Singleton class to display all messages of the tool


package httplinkchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Message {
	private static Message instance;
	   private Message(){}
	   	   
	   private static HashMap<String, String> msgPools = new HashMap<String, String>();
	   static {
		   msgPools.put("Instruction-1", "* Manual");
		   msgPools.put("Instruction-2", "* httplinkchecker version 1.1, September 24, 2020");
		   msgPools.put("ErrMsg-1", "Please enter CLI arguments as:  $java -jar httplinkchecker.jar -r fileName folderPath ...");
		   msgPools.put("ErrMsg-2", "An invalid file name or directory path");   
	   }
	   
	   public static synchronized Message getInstance(){
	        if(instance == null){
	            instance = new Message();
	        }
	        return instance;
	    }
	   
	   
	   //to be modified
	   public void displayResponseResult(String filePath) {
		   String line = null;
		   try
	       {
	           /* FileReader reads text files in the default encoding */
	           FileReader fileReader = new FileReader(filePath);
	           
	           /* always wrap the FileReader in BufferedReader */
	           BufferedReader bufferedReader = new BufferedReader(fileReader);
	           
	           while((line = bufferedReader.readLine()) != null)
	           {
	               System.out.println(line);
	           }
	           
	           /* always close the file after use */
	           bufferedReader.close();
	       }
	       catch(IOException ex)
	       {
	           System.out.println("Error reading file named '" + filePath + "'");
	       }
	   }
	  
	   
		public void displyMessage(String msgType, int msgIndex, ArrayList<String> target, int style) {
			if(msgType != null) {
				String msgKey = msgType + "-" + msgIndex; 
				
				switch(msgType) {
				case "Element":
					printElement(target, style);
					break;
					
				case "Result":
					printResults(target, style);
					break;
					
				case "Instruction":
					printMsg(msgKey, style);
					break;
					
				case "ErrMsg":	
					printMsg(msgKey, style);
					break;
					
				default:
					
				}
			}else {
				
			}
			
		}
		
		private void printElement(ArrayList<String> strs, int style) {
			if (strs != null && strs.size() > 0) {
				for (String str : strs) {
					System.out.print(str.toString() + ";  ");
				}
				System.out.println();
			}
		}
		
		private void printResults(ArrayList<String> strs, int style) {
			for(String str : strs) {
				System.out.println(str.toString());
			}
		}
		
		private void printMsg(String msgKey, int style) {
			System.out.println(msgPools.get(msgKey).toString());
		}
		
		
		//ConsoleColors are not supported on Windows OS
		//to be researched and implemented in future 
		private void printInStyle(ArrayList<String> strs) {
			
		}
}

