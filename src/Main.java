import java.io.IOException;
import java.util.Scanner;

/**
 * Created by william on 16-7-2.
 */
public class Main {
	private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws IOException {    	
    	System.out.println("1:porn image\n2:porn video\n3:article\n请输入你想要做什么：");
    	switch(sc.nextLine()){
    	case "1":
    		PornImgCCC spider=new PornImgCCC();        
            System.out.println("START");
            spider.menu();
            System.out.println("END");
    		break;
    	case "2":break;
    	case "3":break;
    	default:break;
    	}    	
    }
}
