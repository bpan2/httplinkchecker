//LinkRetriever gets http links from files
package httplinkchecker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

//I don't like the usage of ArrayList of ArrayList here.
//Other options: ArrayList of Array, HashChain, Array of Array (2D Array)  
//https://stackoverflow.com/questions/8559092/create-an-array-of-arraylists
//Title: Create an Array of Arraylists
//Array of ArrayList has been ruled out

//https://www.journaldev.com/744/java-array-of-arraylist-of-array



public class LinkStringExtractor {
	
	private ArrayList<ArrayList<String>> allFilePathByDirectory;
	private ArrayList<ArrayList<String>> allHTTPLinksByFile = new ArrayList<ArrayList<String>>();
	private ArrayList<String> absFilePaths;
	private ArrayList<String> dirPaths;
	private ArrayList<String> fileNames;
	private ArrayList<String> options;

	LinkStringExtractor() {
		this(null, null, null);
	}

	LinkStringExtractor(ArrayList<String> dirPaths, ArrayList<String> fileNames, ArrayList<String> options) {
		this.allFilePathByDirectory = new ArrayList<ArrayList<String>>(dirPaths.size());
		this.dirPaths = dirPaths;
		this.fileNames = fileNames;
		this.options = options;

		for (String op : this.options) {
			if (op.equalsIgnoreCase("r")) {
				for (String dp : this.dirPaths) {
					absFilePaths = retrieveAllSubDirFilePaths(dp);
					allFilePathByDirectory.add(absFilePaths);
				}
			}
			break;
		}

		System.out.println("allFilePathByDirectory: ");
		print2DArrayList(allFilePathByDirectory);

		
		for (String filename : this.fileNames) {
			allHTTPLinksByFile.add(retrieveAllHTTPLinksByFile(filename));
		}
		
		
		allHTTPLinksByFile.add(retrieveAllHTTPLinksByFile(allFilePathByDirectory));
		System.out.println("allHTTPLinksByFile: ");
		print2DArrayList(allHTTPLinksByFile);
	}
	
	
	//to be tested for performance and accuracy
	public ArrayList<ArrayList<String>> getAllHttpLinksByFile() {
		return new ArrayList<ArrayList<String>>(allHTTPLinksByFile);
	}

	
	private ArrayList<String> retrieveAllSubDirFilePaths(String dirPath) {
		ArrayList<String> allSubDirFilePaths = new ArrayList<>();

		// http://zetcode.com/java/fileutils/
		File dir = new File(dirPath);
		Collection<File> files = FileUtils.listFiles(dir, new String[] {"txt", "html"}, true);
		files.stream().forEach(System.out::println);

		for (File f : files) {
			allSubDirFilePaths.add(f.getPath());
		}

		return allSubDirFilePaths;
	}

	private void print2DArrayList(ArrayList<ArrayList<String>> allPathsByDirectory) {
		for (int i = 0; i < allPathsByDirectory.size(); i++) {
			for (int j = 0; j < allPathsByDirectory.get(i).size(); j++) {
				System.out.println(allPathsByDirectory.get(i).get(j) + " ");
			}
		}
	}

	//take a single file path as the parameter
	private ArrayList<String> retrieveAllHTTPLinksByFile(String fileName) {
		ArrayList<String> alllinks = new ArrayList<>();
		Path filePath = Paths.get(fileName);
		
		try {
			List<String> tmpFile = Files.readAllLines(filePath, StandardCharsets.ISO_8859_1);

			tmpFile.forEach((String line) -> {
				if (isHTTPLink(line)) {
					alllinks.add(line);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return alllinks;
	}

	//take an ArrayList of ArrayList of file paths as the parameter
	private ArrayList<String> retrieveAllHTTPLinksByFile(ArrayList<ArrayList<String>> allFilePathByDirectory) {
		ArrayList<String> alllinks = new ArrayList<>();
		Path filePath = null;
		File tmp = new File("tmp.txt");
		
		for (int i = 0; i < allFilePathByDirectory.size(); i++) {
			for (int j = 0; j < allFilePathByDirectory.get(i).size(); j++) {
				filePath = Paths.get(allFilePathByDirectory.get(i).get(j));
				
				
				File file = new File(filePath.toString());
			    try(BufferedReader br = new BufferedReader(new FileReader(file)); 
			        FileWriter fw = new FileWriter(tmp)) {
			        	String st;
			        	while((st = br.readLine()) != null){
			        		fw.write(st.replaceAll("\\s+", " ").trim().concat("\n"));
			        	}
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			    
				
				try {
					Path p = Paths.get("tmp.txt");
					List<String> tmpFile = Files.readAllLines(p, StandardCharsets.ISO_8859_1);

					tmpFile.forEach((String line) -> {
						//System.out.println("retrieved line: " + line);
						if (isHTTPLink(line)) {
							alllinks.add(line);
							
							//System.out.println("matched line: " + line);
						}
					});

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return alllinks;
	}

	private void writeToT1(String str) {
		try(	
				FileWriter fw = new FileWriter("t1.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)
		    )
			{
			    out.println(str);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	
	// https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/httpThreadLink/HttpClient.html
	//String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
	private boolean isHTTPLink(String url) {
		boolean found = false;
		//https://stackoverflow.com/questions/5713558/detect-and-extract-url-from-a-string
		//String urlRegex = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*" + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";         
                //String urlRegex = "(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
		
		String urlRegex = "\\b(http|https|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(url);
		
		while (matcher.find()) {
			  int matchStart = matcher.start(1); 
			  int matchEnd = matcher.end(); // now you have the offsets of a URL match 
			  url = url.substring(matchStart, matchEnd);
			  
			  writeToT1(url);
			  			  
			found = true;
		}
		
		return found;
	}
}
