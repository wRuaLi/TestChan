package per.wjy.test.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import per.wjy.test.Tool.*;

/**
 * LoliconAPI处理部分
 * CSDN抄了一块下来
 * */

public class CusTomLoliconAPI {
	 public static String Main(String Cu) {
		 	String CTLreturn = null;
	        try {
	            //建立连接
	            URL url = new URL(Cu);
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            //伪装一手，假装我是个浏览器
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setRequestMethod("GET");
	            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            //获取输入流
	            InputStream input = httpUrlConn.getInputStream();
	            //将字节输入流转换为字符输入流
	            InputStreamReader read = new InputStreamReader(input, "utf-8");
	            //为字符输入流添加缓冲
	            BufferedReader br = new BufferedReader(read);


	            // 读取返回结果
	            String data = br.readLine();

	            //把返回的json中的色图链接提取出来
	            try {
	            data = loliconAPIchuli.loliconChuli(data);
	            System.out.println("图片链接:"+data);
	            }catch (Exception e) {
	            	//Test没有搜索到该tag或该tag不存在1";
	            	System.out.println("per.wjy.test.Main.CusTomLoliconAPI Error!");
				}
	            CTLreturn = HPDownLoad.main(data);

	            // 释放资源
	            br.close();
	            read.close();
	            input.close();
	            httpUrlConn.disconnect();
	        } catch (MalformedURLException e) {
	            if (CTLreturn != null) {
			        return CTLreturn = "errorMessage";
			    } else {
			        return "粗错啦！";
			    } // 调用失败，则把错误的原因拿出来，返回给前端。
	        } catch (IOException e) {
	        	 if (CTLreturn != null) {
				        return CTLreturn = "errorMessage";
				    } else {
				        return "粗错啦！";
				    } // 调用失败，则把错误的原因拿出来，返回给前端。
	        }
	        System.out.println(CTLreturn+"现在");
	        return CTLreturn;
	    }


}
