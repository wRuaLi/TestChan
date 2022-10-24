package com.mineir.wjy.testchan;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import static com.mineir.wjy.testchan.TestApplication.textArea;

public class PicDownload {

    public static String main(String url,String path,String ext) {
        if("NULL".equals(url)){
            return "DownLoadFailed";
        }
        /* 获取当前的事件  */
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        /* 把下载的文件移动到jar目录下的TestPic */
//        String dir = System.getProperty("user.dir")+"\\TestPic";
        String dir = path;
        /* 图片名捏 */
        String fileName = "Test小姐的馈赠("+formatter.format(date)+")."+ext;
        downloadHttpUrl(url, dir, fileName);
        System.out.println("DownloidSucceed");

        return "DownloidSucceed";


    }
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        String HPreturn = null;
        try {
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }

            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                    NoopHostnameVerifier.INSTANCE);
            CloseableHttpClient	client = HttpClients.custom().setSSLSocketFactory(scsf).build();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            int cache = 10 * 1024;
            FileOutputStream fileout = new FileOutputStream(dir+"/"+fileName);
            byte[] buffer = new byte[cache];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();

        } catch (Exception e) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            String time = formatter.format(date);
            textArea.appendText("\n\n"+"["+time+"]");
            textArea.appendText("\nTest:请检查网络！Test无法连接至Internet！\n");
        }
    }
}

