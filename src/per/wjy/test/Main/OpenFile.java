
package per.wjy.test.Main;

import java.io.IOException;

/**
 * ��ͼƬ���ڵ��ļ���
 * CSDN����õ� ���Լ������󿴶�
 * */
public class OpenFile {
	private static final String FILE_PATH = System.getProperty("user.dir")+"\\TestPic";
	public static void main(String [] args) {
		try {
            String[] cmd = new String[5];
            cmd[0] = "cmd";
            cmd[1] = "/c";
            cmd[2] = "start";
            cmd[3] = " ";
            cmd[4] = FILE_PATH;
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
