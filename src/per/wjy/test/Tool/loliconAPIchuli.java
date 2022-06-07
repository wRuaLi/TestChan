package per.wjy.test.Tool;
import java.lang.String;
/**
 * loliconAPIchuli LoliconAPI返回json处理部分
 * API返回的是一个json，但我不会json这一块的玩意
 * json？懒得学，我直接用String爆杀他!
 * */
public class loliconAPIchuli {
	public static String loliconChuli(String data) {

		/* 顶级操作  */
		StringBuffer sb = new StringBuffer(data);//接受链接data
		sb = sb.reverse();//翻转链接
		String url = sb.toString();//转换成String
		int index1 = url.indexOf('g');//判断第一个g在哪个位置
		int index2 = url.indexOf(':')+1;//判断第一个:在那个位置 并将获取到的位置索引+1
		StringBuffer sb2 = new StringBuffer(url.substring(index1, index2));//将获取的两个索引用于字符串截取
		String url2 ="https"+sb2.reverse().toString();//再次翻转为正常链接并加上https
		System.out.println("url2"+url2);

		/* 返回处理之后的链接 */
		return url2;

	}
}


