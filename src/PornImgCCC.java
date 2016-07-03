import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by william on 16-7-2.
 * aim to website:
 */
public class PornImgCCC {
    private String homepage="http://www.u6f4.com";
    private String imgFolder="./PornImg/";
	private static Scanner sc=new Scanner(System.in);
	
    public void menu() throws IOException {
        File folder=new File(imgFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
        Document doc= Jsoup.connect("http://www.u6f4.com/AAtupian/AAtb/")
                .timeout(300000)
                .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .get(); //设置了连接最大超出时间
        Elements menu=doc.select("ul.nav.nav_menu.mt30>li.menu-item>a");
        Map<String,String> map=new HashMap<String,String>();      
        for(int index=0;index<menu.size();index++){
        	map.put(index+"", menu.get(index).attr("href"));
        	System.out.println(index+":"+menu.get(index).text());        	       	
        }                
        System.out.println("请选择你喜欢的领域");
        String category=sc.nextLine();
        System.out.println("请输入你想要的关键词，如果不需要请直接回车");        
        String keyword=sc.nextLine();
        page(homepage+map.get(category),keyword);     
    }
    public void page(String pagePath,String keyword) throws IOException {
    	System.out.println("当前page："+pagePath);       
        Document doc= Jsoup.connect(pagePath)
                .timeout(300000)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0")
                .get(); //设置了连接最大超出时间
        String selector="div.box.list.channel>ul a";
        if(keyword!=null&&!keyword.isEmpty()){
        	selector="div.box.list.channel>ul a:contains("+keyword+")";
        }
        Elements list=doc.select(selector);
        for (Element e:list) {
            System.out.println("正在下载："+e.text()+".........");
            viewImg(homepage+e.attr("href"),e.text());
        }
        Element nextPage=doc.select("a.pagegbk:contains(下一页)").first();
        if(null!=nextPage){
            System.out.println("-------nextPage--------\t"+homepage+nextPage.attr("href"));
            page(homepage+nextPage.attr("href"),keyword);
        }
    }

    public void viewImg(String imgpage,String title) throws IOException {
        Document doc= Jsoup.connect(imgpage)
                .timeout(300000)
                .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .get(); //设置了连接最大超出时间
        Elements imglist=doc.select("div.post img");
        for (Element e:imglist) {
            downloadImg(e.attr("src"),title);
        }

    }

    public void downloadImg(String imgSrc,String title) throws IOException {
        String fileName=new File(imgSrc).getName();
        URL url = new URL(imgSrc);
        // 建立连接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*5000);
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
        //建立文件夹
        File folder=new File(imgFolder+title+"/");
        if(!folder.exists()){
            folder.mkdir();
        }
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(imgFolder+title+"/"+fileName+".jpg");
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }



}
