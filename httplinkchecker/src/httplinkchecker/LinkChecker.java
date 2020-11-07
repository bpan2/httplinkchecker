package httplinkchecker;

//This class is the entry point of my tool


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LinkChecker {
public static int MAXNUMPATHS = 10;
	
	public static void main(String[] args) {		
		Message msg = Message.getInstance();
		
		ArgParser ap = null;
		ArrayList<String> urls = null;
		ArrayList<File> files = null;
		PageContentParser pcp = null;
		
		//.........................................................................
		//0. CLI arguments for testing
		//String[] cmdarg = new String[0];
		//String[] cmdarg = {"-r", "-HElp", "sample.txt", "this is not a file name", "C:\\Users\\2water\\eclipse-workspace",  "C:\\Users\\2water\\eclipse-workspace\\t2.txt", "C:\\Users\\2water\\eclipse-workspace\\httplinkchecker\\index.html", "t1.txt"};
		//String[] cmdarg = {"-v", "-HElp","t1.txt", "-r", "C:\\Users\\2water\\eclipse-workspace\\httplinkchecker\\index.html"};
		//String[] cmdarg = {"-v", "--good", "url-list"};
		

		if(args.length > 0) {
			 ap = new ArgParser(cmdarg);
		}else {
			 urls = new ArrayList<>();
			 Scanner urlInput = new Scanner(System.in);
			 System.out.println("Please enter a URL");
			 
			 while (urlInput.hasNext()) {
				 urls.add(urlInput.next());
		        }
		 
			 urlInput.close();
		}
		
		if(urls != null) {
			pcp = new PageContentParser(urls);
			files = new ArrayList<>();
			files = pcp.getFiles();
		}



		//.........................................................................
		//1. based on file names, and options, get all absolute paths to the files
		
		  System.out.print("dirPaths: "); 
		  msg.displyMessage("Element", 0, ap.getDirPaths(), null);
		  
		  System.out.print("filePaths: "); 
		  msg.displyMessage("Element", 0, ap.getFilePaths(), null);
		  
		  System.out.print("fileNames: "); 
		  msg.displyMessage("Element", 0, ap.getFileNames(), null);
		  
		  System.out.print("options: "); 
		  msg.displyMessage("Element", 0, ap.getOptions(), null);
		 
			/*
			 * System.out.print("subDirPathByFile: ");
			 * ap.print2DArrayList(ap.getSubDirPathByFile());
			 */

		
		//.........................................................................
		//2. in each available file, search for links via regex
		//LinkStringExtractor lr = new LinkStringExtractor(ap.getDirPaths(), ap.getFileNames(), ap.getOptions());
		LinkStringExtractor lr = new LinkStringExtractor(files);
		
		//.........................................................................
		//3. validate the retrieved links via ExecutorsService
		LinkValidator lv = new LinkValidator(lr.getAllHttpLinksByFile());
		
		
		//.........................................................................
		//4. output all the returned results
		//msg.displayResponseResult(lv.getResponseResultFilePath());
		  for(String op : ap.getOptions()) {
				if(op.equalsIgnoreCase("good")) {
					msg.displyMessage("good", 1, lv.getValidLinks(), lv.getResponses());
				}
				else if(op.equalsIgnoreCase("bad")) {
					msg.displyMessage("bad", 1, lv.getValidLinks(), lv.getResponses());
				}
				
			}
		
	} 
}
