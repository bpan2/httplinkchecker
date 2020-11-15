package httplinkchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class PageContentParser {

  private ArrayList<URL> urls;
  private ArrayList<File> files;

  public PageContentParser(ArrayList<String> srcURLs) {
    this.urls = new ArrayList<URL>();
    for (int i = 0; i < srcURLs.size(); i++) {
      this.urls.add(strToURL(srcURLs.get(i)));
    }

    webContentToFiles();
  }

  private URL strToURL(String src) {
    URL url = null;
    try {
      url = new URL(src);
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return url;
  }

  private void webContentToFiles() {
    this.files = new ArrayList<>();

    for (URL url : urls) {
      String fileName = new String(url.toString().substring(5) + ".txt");
      File f = new File(fileName);

      //The following segment of code is to copy the contents on a webpage into a file.
      //The segment is adopted from
      //https://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java
      ReadableByteChannel rbc = null;
      try {
        rbc = Channels.newChannel(url.openStream());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      FileOutputStream fos;
      try {
        fos = new FileOutputStream(f);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      this.files.add(f);
    }
  }

  public ArrayList<URL> getUrls() {
    return urls;
  }

  public ArrayList<File> getFiles() {
    return files;
  }
}
