import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Attribute;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import java.util.ArrayList;
public class Scraper{

  public static Document getDoc(String baseUrl, String path){
    Document doc = null;
    try{
      doc = Jsoup.connect(baseUrl + path).get();
    }catch(Exception e){
      System.out.println("Cant open: " + baseUrl + path);
    };
    return doc;
  }

  public static void main(String[] args) throws IOException{
    String baseUrl = "https://www.google.com/search?client=ubuntu&hs=nrU&channel=fs&biw=1333&bih=670&tbm=isch&sa=1&ei=3_YgXIyAEtKj1fAPo7e6oAM&q=hilma+af+klint&oq=hilma+af+klint&gs_l=img.3..35i39l2j0j0i67j0l6.8913.10491..10874...0.0..0.102.293.2j1......1....1..gws-wiz-img.GEc2-MWwXpk#imgrc=_";
    Document doc = getDoc(baseUrl, "");
    
    Elements img_links = doc.getElementsByClass("rg_ic rg_i");
    System.out.println(img_links.size());
    ArrayList<String> links = new ArrayList<String>();
    for(Element img_link : img_links){
      String link;
      link =  img_link.attr("data-src");
      if(!link.equals("")){
        links.add(link);
      }
    }

    int nr = 1;
    for(String imageUrl : links){
      String destFile = "./imgs/img" + nr + ".jpg";
      nr++;
      
      System.out.println(imageUrl);
      URL url = new URL(imageUrl);
      InputStream is = url.openStream();
      OutputStream os = new FileOutputStream(destFile);

      byte[] b = new byte[2048];
      int length;

      while ((length = is.read(b)) != -1) {
        os.write(b, 0, length);
      }

      is.close();
      os.close();
    }
    
  }
}