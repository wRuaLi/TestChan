package per.wjy.test.Tool;

import per.wjy.test.Main.loliconAPI;

/** 
 * TestThread 一个莫名其妙的多线程，用来调用loliconAPi类里的方法  
 * */
public class TestThread extends Thread {
   public void run() {
	   loliconAPI.LoliconMain();
   }

}