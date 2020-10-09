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
		   msgPools.put("Instruction-1", "* manual on how to use this tool (to be implemented)");
		   msgPools.put("Instruction-2", "* httplinkchecker version 1.1, September 24, 2020");
		   msgPools.put("ErrMsg-1", "Please use the following format on the commandline:  $java -jar httplinkchecker.jar -r fileName folderPath ...");
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
		   try{
	           FileReader fileReader = new FileReader(filePath);
	           
	           BufferedReader bufferedReader = new BufferedReader(fileReader);
	           
	           while((line = bufferedReader.readLine()) != null){
	               System.out.println(line);
	           }
	           bufferedReader.close();
	       }
	       catch(IOException ex){
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
					
				case "good":
					printGoodLinks(target, style);
					break;
					
				case "bad":
					printBadLinks(target, style);
					break;
				default:
					
				}
			}else {
				
			}
			
		}
		
		private void printBadLinks(ArrayList<String> target, ArrayList<Integer> style) {
			for(int i=0; i < style.size(); i++) {
				if( style.get(i) > 400) {
					System.out.println(target.get(i));
				}
			}
		}


		private void printGoodLinks(ArrayList<String> target, ArrayList<Integer> style) {
			for(int i=0; i < style.size(); i++) {
				if( style.get(i) < 400 && style.get(i) > 100) {
					System.out.println(target.get(i));
				}
			}
		}
		
		private void printElement(ArrayList<String> strs, int style) {
			if (strs != null && strs.size() > 0) {
				for (String str : strs) {
					System.out.print(str.toString() + ",  ");
				}
				System.out.println();
			}
		}
		
		//to be improved
		private void printResults(ArrayList<String> strs, int style) {
			for(String str : strs) {
				System.out.println(str.toString());
			}
		}
		
		
		private void printResults(ArrayList<ArrayList<String>> allPathsByDirectory) {
			for (int i = 0; i < allPathsByDirectory.size(); i++) {
				for (int j = 0; j < allPathsByDirectory.get(i).size(); j++) {
					System.out.println(allPathsByDirectory.get(i).get(j) + ", ");
				}
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

