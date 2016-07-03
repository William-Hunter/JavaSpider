import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FuQiDangAnGuan {
	private String homepage="http://www.fuqidangan.com";
	private String imgFolder="./PornVideoImg/";
	private static Scanner sc=new Scanner(System.in);
	
	public void menu() throws IOException{
		File folder=new File(imgFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
        Document doc= Jsoup.connect(homepage)
                .timeout(300000)
                .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .get(); //设置了连接最大超出时间
    
        
        
		
	}
	
	
}
