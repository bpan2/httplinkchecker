package httplinkchecker;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class LinkValidator {
	private ArrayList<ArrayList<String>> allHTTPLinksByFile;
	private File responseResult;

	public LinkValidator() {
		this(null);
	}
	
	public LinkValidator(ArrayList<ArrayList<String>> allHTTPLinksByFile) {
		this.allHTTPLinksByFile = allHTTPLinksByFile;
		if(responseResult == null) {
			responseResult = new File("httpResponsResult.txt");
		}
		
		
		for (int i = 0; i < this.allHTTPLinksByFile.size(); i++) {
			for (int j = 0; j < this.allHTTPLinksByFile.get(i).size(); j++) {
				try {
					validateHttpLink(allHTTPLinksByFile.get(i).get(j));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	
	private File removeAllSpaces(String path) {
		File f = new File(path);
		
		
		
		return f;
	}
	
	//In the main thread, send a HTTP request to the server, retrieve its response, and write it to a file
	private void validateHttpLink(String url) throws Exception {
		String response = null;
		LinkStatus ls = LinkStatus.DEFAULT_VALUE;
		
		try {
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) link.openConnection();
			
			int responseCode = connection.getResponseCode();
			response = ls.getCompleteStatusMsg(responseCode);
			
			//write to a file rather than send to the console right away in order to avoid the potential bottleneck due to the computer's IO capacity 
			writeHttpResponseToFile("\"" + url + "\" :"+ response);
			
		} catch (Exception e) {
			System.out.print(url + " : " + e.getMessage());
		}
	}
	
	public void writeHttpResponseToFile(String response) {
		
		try(	
				FileWriter fw = new FileWriter(responseResult, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)
		    )
			{
			    out.println(response);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	
	public String getResponseResultFilePath() {
		if(this.responseResult != null) {
			return this.responseResult.getAbsolutePath().toString();
		}
		else {
			return null;
		}
	}
}
