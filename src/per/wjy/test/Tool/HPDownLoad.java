package per.wjy.test.Tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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

/*
 *HTTPS PIC DOWNLOAD httpsͼƬ���س���
 *2020.5.1 3:01 ��ʱ11Сʱ.
*/
public class HPDownLoad {


	public static String main(String url) {
		/* ��ȡ��ǰ���¼�  */
		Date date = new Date(); // this object contains the current date value
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		String HMreturn = null;
		//String url = "https://i.pixiv.re/img-master/img/2020/08/31/23/59/55/84073331_p0_master1200.jpg";
		//String dir = "/TestPic";

		/* �����ص��ļ��ƶ���jarĿ¼�µ�TestPic */
		String dir = System.getProperty("user.dir")+"\\TestPic";
		/* ͼƬ���� */
		String fileName = "TestС�������("+formatter.format(date)+").jpg";
		String s = downloadHttpUrl(url, dir, fileName);

		/* �ж�s�Ƿ�Ϊnull */
		if(s!=null){
			HMreturn ="Error";
		}else{
			HMreturn = s;
		}
		System.out.println(HMreturn+" ���ⲻ��");
		return HMreturn;

	}



    /**
	 * �����ļ�---�������غ���ļ��洢·��
	 *
	 * @param url �ļ���ַ
	 * @param dir �洢Ŀ¼
	 * @param fileName �洢�ļ���
	 * @return
	 */
	public static String return2 = null ;
	public static String downloadHttpUrl(String url, String dir, String fileName) {
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
			HPreturn = "TestTool/HPDowlLoad/�޷�������LoliconAPI���ص�ɬͼ����";
		}
		return HPreturn;
	}


}