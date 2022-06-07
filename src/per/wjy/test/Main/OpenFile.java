
package per.wjy.test.Main;

import java.io.IOException;

/**
 * 打开图片所在的文件夹
 * CSDN随便拿的 我自己都不大看懂
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
