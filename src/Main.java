import java.io.IOException;
import java.util.Scanner;

/**
 * Created by william on 16-7-2.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PornImgCCC spider=new PornImgCCC();
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你想要的关键词，如果不需要请直接回车");
        String keyword=sc.nextLine();
        System.out.println("START");
        spider.asia(keyword);
        System.out.println("END");
    }
}
