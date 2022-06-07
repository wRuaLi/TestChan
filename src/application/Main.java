package application;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import per.wjy.test.Main.CusTomLoliconAPI;
import per.wjy.test.Main.OpenFile;
import per.wjy.test.Tool.TestThread;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * TestChan1.3 for JavaFX
 * 2022.6.7  @wRuaLi
 * 毕生所学了属于是，百度少说也用了上千次
 * */
public class Main extends Application {
	/* 控件初始化 */
	public static String returnTest = null;
	public static TextArea textArea = new TextArea("-------------------------------------------------------------------------------"+"\n"+"当前版本:TestChan1."+"\n"+"更新日志:https://wruali.github.io/"+"\n"+"联系方式:wruali123@gmail.com"+"\n"+"bilibili:椎名真羽RuaLi"+"\n"+"-------------------------------------------------------------------------------"+"\n"+"tip:"+"\n"+"图片下载速度取决于网速。"+"\n"+"使用tag搜索时需要的时间可能会更长，请耐心等待。");//79
    public static TextField textField = new TextField();
     Button b1 = new Button("来点涩图!");
     Button b2 = new Button("多来点!");
     Button b3 = new Button("查看图片");
     Label label1 = new Label("tag:");

	@Override
	public void start(Stage primaryStage) throws Exception {
			/* 布局初始化 */
			Pane root = new Pane();

			/* 程序左侧的图片 */
			Image testimg = new Image("application/res/Test_img2.jpg",270, 450, false, false);
			ImageView img1 = new ImageView();
			img1.setImage(testimg);

			/* textArea控制台样式 */
			//位置
			textArea.setLayoutX(270);
			textArea.setLayoutY(0);
			//尺寸
			textArea.setPrefHeight(300);
		    textArea.setPrefWidth(430);
		    //设置为不可调整窗口大小
		    textArea.setEditable(false);
		    //自动移动到最新位置
		    textArea.positionCaret(textArea.getText().length());


		    /* 确定按钮  来点色图  */
			//位置
			b1.setLayoutX(515);
			b1.setLayoutY(400);
			//尺寸
			b1.setPrefHeight(25);
			b1.setPrefWidth(80);
			//点击事件
			b1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
						   Ghs();//来张涩图！
				}
			});

			/* 确定按钮 多来点*/
			//位置
			b2.setLayoutX(600);
			b2.setLayoutY(400);
			//尺寸
			b2.setPrefHeight(25);
			b2.setPrefWidth(80);

			b2.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					try {
						more();
					} catch (Exception e) {
						System.out.println("application.Main.Start.b2 Error");
					}
				}
			});

			/* 确定按钮 查看图片*/
			//位置
			b3.setLayoutX(600);
			b3.setLayoutY(365);
			//尺寸
			b3.setPrefHeight(25);
			b3.setPrefWidth(80);
			//点击事件
			b3.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					OpenFile();//打开下载图片所在的文件夹
				}
			});

		    /* 搜索框  */
			//位置
		    textField.setLayoutX(360);//450
		    textField.setLayoutY(400);
		    //尺寸
		    textField.setPrefHeight(25);
		    textField.setPrefWidth(150);

		    /* 文本"tag" */
		    //位置
		    label1.setLayoutX(330);
		    label1.setLayoutY(400);
		    //尺寸
		    label1.setPrefHeight(25);
		    label1.setPrefWidth(50);

		    /* JavaFX 场景部分 */
			root.getChildren().addAll(img1,textArea,b1,textField,label1,b2,b3);
			Scene scene = new Scene(root,700,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("TestChan for JavaFX");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("application/res/Test_Logo.jpg"));
			primaryStage.show();


	}

	/**
	 * Ghs 来一张涩图
	 * 勉强还行，就这一点点花了一个月才玩明白的
	 * */
	public void Ghs(){
		new Thread(() -> {
		if(textField.getText().trim().equals("")){

				/*获取当前日期*/
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
				String time = formatter.format(date);

				textArea.appendText("\n\n"+"["+time+"]");
				textArea.appendText("\n"+"Test正在努力下载！");

				/* 开个线程搞张涩图 */
				TestThread sese = new TestThread();
			       sese.start();

			       /* 在外面这圈大的线程这里整个判断  */
			       while(true){
			    	   if(sese.isAlive()==false){
			    		   textArea.appendText("\n\n"+"["+time+"]");
			    	       textArea.appendText("\n"+"Test：完成，已保存。");
			    	       break;//下载完之后推出循环
			    	   }
			       }


		}else{

				/* 获取当前日期 */
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
				String time = formatter.format(date);

				/* 获取用户输入的tag */
				String tag = textField.getText();
				String scc;
				try {
					scc = URLEncoder.encode(tag,"UTF-8");//把中文转换成UTF-8编码，不然程序看不懂
					textArea.appendText("\n\n"+"["+time+"]");
					textArea.appendText("\n"+"请稍后，Test正在检索...");
					String CuUrl = "https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re&tag="+scc;
					String CTLA = CusTomLoliconAPI.Main(CuUrl);
					String CTLA2 = CTLA;
					if("Error".equals(CTLA2)){//当用户输入的tag不存在或不正确
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test：抱歉先生,您提供的tag不存在或不正确");
					}else{//当用户输入的tag存在或正确
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test："+"完成，已经按照先生您的要求做了...");
					}

				}catch (Exception e) {
					System.out.println("application.Main.ifTag Error!");
				}
		}
		}).start();


	}

	/**
	 * more 多来点
	 * 屎山代码警告！ */
	public void more() throws Exception{
		new Thread(() -> {
			/* 获取时间 */
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			String time = formatter.format(date);

			textArea.appendText("\n\n"+"["+time+"]");
			textArea.appendText("\n"+"Test：正在下载多张图片，请稍后");
			if(textField.getText().trim().equals("")){

			/* 十个线程！ */
			try{
		       TestThread sese1 = new TestThread();
		       TestThread sese2 = new TestThread();
		       TestThread sese3 = new TestThread();
		       TestThread sese4 = new TestThread();
		       TestThread sese5 = new TestThread();
		       TestThread sese6 = new TestThread();
		       TestThread sese7 = new TestThread();
		       TestThread sese8 = new TestThread();
		       TestThread sese9 = new TestThread();
		       TestThread sese10 = new TestThread();
				sese1.start();
				Thread.sleep(1500);
				sese2.start();
				Thread.sleep(1500);
				sese3.start();
				Thread.sleep(1500);
				sese4.start();
				Thread.sleep(1500);
				sese5.start();
				Thread.sleep(1500);
				sese6.start();
				Thread.sleep(1500);
				sese7.start();
				Thread.sleep(1500);
				sese8.start();
				Thread.sleep(1500);
				sese9.start();
				Thread.sleep(1500);
				sese10.start();

				/* 判断线程是否完成 */
				 while(true){
			    	   if(sese1.isAlive()==false && sese2.isAlive()==false && sese3.isAlive()==false && sese4.isAlive()==false && sese5.isAlive()==false){
			    		   textArea.appendText("\n\n"+"["+time+"]");
			    	       textArea.appendText("\n"+"Test：完成，已经保存好了先生。");
			    	       textArea.appendText("\n"+"Test：小撸怡情，大撸伤身，强撸灰飞烟灭哦！");
			    	       break;//完成后退出判断
			    	   }



			}
		}catch (Exception e) {
				System.out.println("application.Main.more() Error!");
		}




		}else{
			/* 通过tag多来点 */
			String tag = textField.getText();//拿到用户输入的tag
			String scc;
				try {
					for(int i = 1;i<=10;i++){
						scc = URLEncoder.encode(tag,"UTF-8");
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test：正在检索并下载多张图片，请稍后");
						String CuUrl = "https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re&tag="+scc;
						String CTLA = CusTomLoliconAPI.Main(CuUrl);
						String CTLA2 = CTLA;

						if("Error".equals(CTLA2)){//当tag不存在或不正确
							textArea.appendText("\n\n"+"["+time+"]");
							textArea.appendText("\n"+"Test：抱歉先生，您提供的tag不存在或不正确");
							break;
						}else{//当tag存在或正确
							textArea.appendText("\n\n"+"["+time+"]");
							textArea.appendText("\n"+"Test：完成，已保存");
							textArea.appendText("\n"+"Test：小撸怡情，大撸伤身，强撸灰飞烟灭哦！");
						}

					}
				}catch (Exception e) {
					System.out.println("application.Main.more() Error!");
				}
		}
		}).start();


	}

	/**
	 * OpenFile
	 * 打开下载图片所在文件夹 */
	public static void OpenFile(){
		OpenFile.main(null);
	}



	/** 创建图片文件夹 */
	public static void main(String[] args) {

		//创建Test所需文件夹
		File file=new File(System.getProperty("user.dir")+"\\TestPic");
		if(!file.exists()){//如果文件夹不存在
			file.mkdir();//创建文件夹
		}
		launch(args);

	}
}
