package per.wjy.test.Tool;
import java.lang.String;
/**
 * loliconAPIchuli LoliconAPI����json������
 * API���ص���һ��json�����Ҳ���json��һ�������
 * json������ѧ����ֱ����String��ɱ��!
 * */
public class loliconAPIchuli {
	public static String loliconChuli(String data) {

		/* ��������  */
		StringBuffer sb = new StringBuffer(data);//��������data
		sb = sb.reverse();//��ת����
		String url = sb.toString();//ת����String
		int index1 = url.indexOf('g');//�жϵ�һ��g���ĸ�λ��
		int index2 = url.indexOf(':')+1;//�жϵ�һ��:���Ǹ�λ�� ������ȡ����λ������+1
		StringBuffer sb2 = new StringBuffer(url.substring(index1, index2));//����ȡ���������������ַ�����ȡ
		String url2 ="https"+sb2.reverse().toString();//�ٴη�תΪ�������Ӳ�����https
		System.out.println("url2"+url2);

		/* ���ش���֮������� */
		return url2;

	}
}


