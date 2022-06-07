package per.wjy.test.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import per.wjy.test.Tool.*;

public class loliconAPI {
	public static boolean a = false;
	    public static void LoliconMain() {
	        try {
	            //建立连接
	            URL url = new URL("https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re");
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
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
	            data = loliconAPIchuli.loliconChuli(data);


	            try {
					HPDownLoad.main(data);
				} catch (Exception e) {
					e.printStackTrace();
				}

	            // 释放资源
	            br.close();
	            read.close();
	            input.close();
	            httpUrlConn.disconnect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        a = true;

	    }
	}

