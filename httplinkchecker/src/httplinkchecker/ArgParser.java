//ArgParser is used to process the CLI input and to generate the absolute paths to the files for this tool to check
package httplinkchecker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class ArgParser {
	private ArrayList<String> dirPaths;
	private ArrayList<String> filePaths;
	private ArrayList<ArrayList<String>> subDirPathByFile; 
	private ArrayList<String> fileNames;
	private ArrayList<String> options;
	private Message msg;

	public ArgParser() {
		this.dirPaths = null;
		this.filePaths = null;
		this.subDirPathByFile = null;
		this.fileNames = null;
		this.options = null;
		this.msg = null;
	};

	public ArgParser(String[] args) {
		msg = Message.getInstance();

		if (args.length == 0) {
			this.msg.displyMessage("ErrMsg", 1, null, 1);
		}

		parseArgs(args);

		if (options.size() > 0 ) {
			for (String op : options) {
				switch (op) {
				case "h":
				case "help":
					this.msg.displyMessage("Instruction", 1, null, 1);
					break;
					
				case "v":
				case "version":
					this.msg.displyMessage("Instruction", 2, null, 1);
					break;
					
				case "r":
					subDirPathByFile = new ArrayList<ArrayList<String>>();
					
					for(String dirpath : dirPaths) {
						subDirPathByFile.add(retrieveAllSubDirFilePaths(dirpath, true));
					}
					break;
				}
			}
		}
	}

	private void parseArgs(String[] args) {
		dirPaths = new ArrayList<>();
		filePaths = new ArrayList<>();
		fileNames = new ArrayList<>();
		options = new ArrayList<>();

		for (String arg : args) {
			if(arg.startsWith("--")) {
				if(arg.length() == 2){//to check the length against the option input String contains only two '--' hyphen
					System.out.println("Invalid option: " + "\"" + arg + "\"");
					continue;
				}
				this.options.add(arg.substring(2).toLowerCase()); 
			}
			else if (arg.startsWith("-") || arg.startsWith("\"")) {
				if(arg.length() == 1){//to check the length against the option input String contains only one '-' hyphen or one '\'
					System.out.println("Invalid option: " + "\"" + arg + "\"");
					continue;
				}
				this.options.add(arg.substring(1).toLowerCase());
			}else { 
				File f = new File(arg.toLowerCase());

				if (f.exists()) {
					String tmppath = null;
					
					//retrieve the canonical paths for files and paths entered via the command line
					try {
						tmppath = f.getCanonicalPath().toString();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (f.isDirectory()) {
						this.dirPaths.add(tmppath);
						this.filePaths.addAll(retrieveAllSubDirFilePaths(tmppath, false));//need an explanation of the different usage based on the boolean value
					} else if (f.isFile()) {
						this.fileNames.add(arg);
						this.filePaths.add(tmppath);
					}
				}
				else {
					System.out.println("Invalid argument input: " + "\"" + arg + "\"");
				}
			}
		}
	}

	private ArrayList<String> retrieveAllSubDirFilePaths(String dirPath, boolean searchSubDir) {
		ArrayList<String> allSubDirFilePaths = new ArrayList<>();

		// http://zetcode.com/java/fileutils/
		File dir = new File(dirPath);
		Collection<File> files = FileUtils.listFiles(dir, new String[] { "txt", "html" }, searchSubDir); // recursively search
																									// all the
																									// sub-directories
																									// for those files
																									// with "txt" and
																									// "html" file
																									// extensions
		//files.stream().forEach(System.out::println);

		for (File f : files) {
			allSubDirFilePaths.add(f.getPath());
		}

		return allSubDirFilePaths;
	}
	
	/* Deprecated:
	 * public ArrayList<String> retrieveAllAbsoluteFilePaths(String path) { File f =
	 * new File(path); File[] files = f.listFiles();
	 * 
	 * ArrayList<String> filePaths = new ArrayList<>();
	 * 
	 * for (File file : files) { filePaths.add(file.getAbsolutePath()); }
	 * 
	 * return filePaths; }
	 */

	
	
	public ArrayList<String> getDirPaths() {
		return new ArrayList<>(this.dirPaths);
	}
	
	public ArrayList<String> getFilePaths() {
		return new ArrayList<>(this.filePaths);
	}

	
	  public void print2DArrayList(ArrayList<ArrayList<String>>
	  allPathsByDirectory) { for (int i = 0; i < allPathsByDirectory.size(); i++) {
	  for (int j = 0; j < allPathsByDirectory.get(i).size(); j++) {
	  System.out.println(allPathsByDirectory.get(i).get(j) + ", "); } } }
	 
	
	public ArrayList<ArrayList<String>> getSubDirPathByFile() {
		return this.subDirPathByFile;
	}
	
	public ArrayList<String> getFileNames() {
		return new ArrayList<>(this.fileNames);
	}
	
	public ArrayList<String> getOptions() {
		return new ArrayList<>(this.options);
	}
}

