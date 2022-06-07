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
 * LoliconAPI������
 * CSDN����һ������
 * */

public class CusTomLoliconAPI {
	 public static String Main(String Cu) {
		 	String CTLreturn = null;
	        try {
	            //��������
	            URL url = new URL(Cu);
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            //αװһ�֣���װ���Ǹ������
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
	            try {
	            data = loliconAPIchuli.loliconChuli(data);
	            System.out.println("ͼƬ����:"+data);
	            }catch (Exception e) {
	            	//Testû����������tag���tag������1";
	            	System.out.println("per.wjy.test.Main.CusTomLoliconAPI Error!");
				}
	            CTLreturn = HPDownLoad.main(data);

	            // �ͷ���Դ
	            br.close();
	            read.close();
	            input.close();
	            httpUrlConn.disconnect();
	        } catch (MalformedURLException e) {
	            if (CTLreturn != null) {
			        return CTLreturn = "errorMessage";
			    } else {
			        return "�ִ�����";
			    } // ����ʧ�ܣ���Ѵ����ԭ���ó��������ظ�ǰ�ˡ�
	        } catch (IOException e) {
	        	 if (CTLreturn != null) {
				        return CTLreturn = "errorMessage";
				    } else {
				        return "�ִ�����";
				    } // ����ʧ�ܣ���Ѵ����ԭ���ó��������ظ�ǰ�ˡ�
	        }
	        System.out.println(CTLreturn+"����");
	        return CTLreturn;
	    }


}
