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
 * ������ѧ�������ǣ��ٶ���˵Ҳ������ǧ��
 * */
public class Main extends Application {
	/* �ؼ���ʼ�� */
	public static String returnTest = null;
	public static TextArea textArea = new TextArea("-------------------------------------------------------------------------------"+"\n"+"��ǰ�汾:TestChan1."+"\n"+"������־:https://wruali.github.io/"+"\n"+"��ϵ��ʽ:wruali123@gmail.com"+"\n"+"bilibili:׵������RuaLi"+"\n"+"-------------------------------------------------------------------------------"+"\n"+"tip:"+"\n"+"ͼƬ�����ٶ�ȡ�������١�"+"\n"+"ʹ��tag����ʱ��Ҫ��ʱ����ܻ�����������ĵȴ���");//79
    public static TextField textField = new TextField();
     Button b1 = new Button("����ɬͼ!");
     Button b2 = new Button("������!");
     Button b3 = new Button("�鿴ͼƬ");
     Label label1 = new Label("tag:");

	@Override
	public void start(Stage primaryStage) throws Exception {
			/* ���ֳ�ʼ�� */
			Pane root = new Pane();

			/* ��������ͼƬ */
			Image testimg = new Image("application/res/Test_img2.jpg",270, 450, false, false);
			ImageView img1 = new ImageView();
			img1.setImage(testimg);

			/* textArea����̨��ʽ */
			//λ��
			textArea.setLayoutX(270);
			textArea.setLayoutY(0);
			//�ߴ�
			textArea.setPrefHeight(300);
		    textArea.setPrefWidth(430);
		    //����Ϊ���ɵ������ڴ�С
		    textArea.setEditable(false);
		    //�Զ��ƶ�������λ��
		    textArea.positionCaret(textArea.getText().length());


		    /* ȷ����ť  ����ɫͼ  */
			//λ��
			b1.setLayoutX(515);
			b1.setLayoutY(400);
			//�ߴ�
			b1.setPrefHeight(25);
			b1.setPrefWidth(80);
			//����¼�
			b1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
						   Ghs();//����ɬͼ��
				}
			});

			/* ȷ����ť ������*/
			//λ��
			b2.setLayoutX(600);
			b2.setLayoutY(400);
			//�ߴ�
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

			/* ȷ����ť �鿴ͼƬ*/
			//λ��
			b3.setLayoutX(600);
			b3.setLayoutY(365);
			//�ߴ�
			b3.setPrefHeight(25);
			b3.setPrefWidth(80);
			//����¼�
			b3.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					OpenFile();//������ͼƬ���ڵ��ļ���
				}
			});

		    /* ������  */
			//λ��
		    textField.setLayoutX(360);//450
		    textField.setLayoutY(400);
		    //�ߴ�
		    textField.setPrefHeight(25);
		    textField.setPrefWidth(150);

		    /* �ı�"tag" */
		    //λ��
		    label1.setLayoutX(330);
		    label1.setLayoutY(400);
		    //�ߴ�
		    label1.setPrefHeight(25);
		    label1.setPrefWidth(50);

		    /* JavaFX �������� */
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
	 * Ghs ��һ��ɬͼ
	 * ��ǿ���У�����һ��㻨��һ���²������׵�
	 * */
	public void Ghs(){
		new Thread(() -> {
		if(textField.getText().trim().equals("")){

				/*��ȡ��ǰ����*/
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
				String time = formatter.format(date);

				textArea.appendText("\n\n"+"["+time+"]");
				textArea.appendText("\n"+"Test����Ŭ�����أ�");

				/* �����̸߳���ɬͼ */
				TestThread sese = new TestThread();
			       sese.start();

			       /* ��������Ȧ����߳����������ж�  */
			       while(true){
			    	   if(sese.isAlive()==false){
			    		   textArea.appendText("\n\n"+"["+time+"]");
			    	       textArea.appendText("\n"+"Test����ɣ��ѱ��档");
			    	       break;//������֮���Ƴ�ѭ��
			    	   }
			       }


		}else{

				/* ��ȡ��ǰ���� */
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
				String time = formatter.format(date);

				/* ��ȡ�û������tag */
				String tag = textField.getText();
				String scc;
				try {
					scc = URLEncoder.encode(tag,"UTF-8");//������ת����UTF-8���룬��Ȼ���򿴲���
					textArea.appendText("\n\n"+"["+time+"]");
					textArea.appendText("\n"+"���Ժ�Test���ڼ���...");
					String CuUrl = "https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re&tag="+scc;
					String CTLA = CusTomLoliconAPI.Main(CuUrl);
					String CTLA2 = CTLA;
					if("Error".equals(CTLA2)){//���û������tag�����ڻ���ȷ
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test����Ǹ����,���ṩ��tag�����ڻ���ȷ");
					}else{//���û������tag���ڻ���ȷ
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test��"+"��ɣ��Ѿ�������������Ҫ������...");
					}

				}catch (Exception e) {
					System.out.println("application.Main.ifTag Error!");
				}
		}
		}).start();


	}

	/**
	 * more ������
	 * ʺɽ���뾯�棡 */
	public void more() throws Exception{
		new Thread(() -> {
			/* ��ȡʱ�� */
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
			String time = formatter.format(date);

			textArea.appendText("\n\n"+"["+time+"]");
			textArea.appendText("\n"+"Test���������ض���ͼƬ�����Ժ�");
			if(textField.getText().trim().equals("")){

			/* ʮ���̣߳� */
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

				/* �ж��߳��Ƿ���� */
				 while(true){
			    	   if(sese1.isAlive()==false && sese2.isAlive()==false && sese3.isAlive()==false && sese4.isAlive()==false && sese5.isAlive()==false){
			    		   textArea.appendText("\n\n"+"["+time+"]");
			    	       textArea.appendText("\n"+"Test����ɣ��Ѿ��������������");
			    	       textArea.appendText("\n"+"Test��Сߣ���飬��ߣ����ǿߣ�ҷ�����Ŷ��");
			    	       break;//��ɺ��˳��ж�
			    	   }



			}
		}catch (Exception e) {
				System.out.println("application.Main.more() Error!");
		}




		}else{
			/* ͨ��tag������ */
			String tag = textField.getText();//�õ��û������tag
			String scc;
				try {
					for(int i = 1;i<=10;i++){
						scc = URLEncoder.encode(tag,"UTF-8");
						textArea.appendText("\n\n"+"["+time+"]");
						textArea.appendText("\n"+"Test�����ڼ��������ض���ͼƬ�����Ժ�");
						String CuUrl = "https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re&tag="+scc;
						String CTLA = CusTomLoliconAPI.Main(CuUrl);
						String CTLA2 = CTLA;

						if("Error".equals(CTLA2)){//��tag�����ڻ���ȷ
							textArea.appendText("\n\n"+"["+time+"]");
							textArea.appendText("\n"+"Test����Ǹ���������ṩ��tag�����ڻ���ȷ");
							break;
						}else{//��tag���ڻ���ȷ
							textArea.appendText("\n\n"+"["+time+"]");
							textArea.appendText("\n"+"Test����ɣ��ѱ���");
							textArea.appendText("\n"+"Test��Сߣ���飬��ߣ����ǿߣ�ҷ�����Ŷ��");
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
	 * ������ͼƬ�����ļ��� */
	public static void OpenFile(){
		OpenFile.main(null);
	}



	/** ����ͼƬ�ļ��� */
	public static void main(String[] args) {

		//����Test�����ļ���
		File file=new File(System.getProperty("user.dir")+"\\TestPic");
		if(!file.exists()){//����ļ��в�����
			file.mkdir();//�����ļ���
		}
		launch(args);

	}
}
