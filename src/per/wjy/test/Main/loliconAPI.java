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
	            //��������
	            URL url = new URL("https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re");
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setRequestMethod("GET");
	            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            //��ȡ������
	            InputStream input = httpUrlConn.getInputStream();
	            //���ֽ�������ת��Ϊ�ַ�������
	            InputStreamReader read = new InputStreamReader(input, "utf-8");
	            //Ϊ�ַ���������ӻ���
	            BufferedReader br = new BufferedReader(read);


	            // ��ȡ���ؽ��
	            String data = br.readLine();

	            //�ѷ��ص�json�е�ɫͼ������ȡ����
	            data = loliconAPIchuli.loliconChuli(data);


	            try {
					HPDownLoad.main(data);
				} catch (Exception e) {
					e.printStackTrace();
				}

	            // �ͷ���Դ
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

