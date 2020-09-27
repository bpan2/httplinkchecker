package httplinkchecker;

public class LinkChecker {
public static int MAXNUMPATHS = 10;
	
	public static void main(String[] args) {		
		Message msg = Message.getInstance();
			
		//.........................................................................
		//0. CLI arguments for testing
		//String[] cmdarg = new String[0];
		//String[] cmdarg = {"-r", "-HElp", "sample.txt", "this is not a file name", "C:\\Users\\2water\\eclipse-workspace",  "C:\\Users\\2water\\eclipse-workspace\\t2.txt", "C:\\Users\\2water\\eclipse-workspace\\httplinkchecker\\index.html", "t1.txt"};
		//String[] cmdarg = {"-v", "-HElp","t1.txt", "-r", "C:\\Users\\2water\\eclipse-workspace\\httplinkchecker\\index.html"};
		String[] cmdarg = {"-v", "-",  "-hElP", "t1.txt", "C:\\Users\\2water\\eclipse-workspace"};
		
		//.........................................................................
		//1. based on file names, and options, get all absolute paths to the files
		 ArgParser ap = new ArgParser(cmdarg);
		
		  System.out.print("dirPaths: "); 
		  msg.displyMessage("Element", 0, ap.getDirPaths(), 0);
		  
		  System.out.print("filePaths: "); 
		  msg.displyMessage("Element", 0, ap.getFilePaths(), 0);
		  
		  System.out.print("fileNames: "); 
		  msg.displyMessage("Element", 0, ap.getFileNames(), 0);
		  
		  System.out.print("options: "); 
		  msg.displyMessage("Element", 0, ap.getOptions(), 0);
		 
			/*
			 * System.out.print("subDirPathByFile: ");
			 * ap.print2DArrayList(ap.getSubDirPathByFile());
			 */

		
		//.........................................................................
		//2. in each available file, search for links via regex
		//LinkRetriever lr = new LinkRetriever(ap.getDirPaths(), ap.getFileNames(), ap.getOptions());
		
		
		//.........................................................................
		//3. validate the retrieved links via ExecutorsService
		//LinkValidator lv = new LinkValidator(lr.getAllHttpLinksByFile());
		
		
		//.........................................................................
		//4. output all the returned results
		//msg.displayResponseResult(lv.getResponseResultFilePath());
		
	} 
}
