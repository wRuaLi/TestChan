package com.mineir.wjy.testchan;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestApplication extends Application {
    /* 控件初始化 */
    public static String returnTest = null;
    public static JFXTextArea textArea = new JFXTextArea("-------------------------------------------------------------------------------"+"\n"+"当前版本:TestChan2.0"+"\n"+"更新日志:https://wruali.github.io/"+"\n"+"联系方式:wruali123@gmail.com"+"\n"+"bilibili:椎名真羽RuaLi"+"\n"+"-------------------------------------------------------------------------------"+"\n"+"tip:"+"\n"+"图片下载速度取决于网速。");//79
    JFXButton jfxButton = new JFXButton("来点色图");
    JFXButton jfxButton2 = new JFXButton("选择路径");
    JFXCheckBox jfxCheckBox = new JFXCheckBox("R18开关");
    JFXCheckBox jfxCheckBox2 = new JFXCheckBox("备用线路(较慢)");
    JFXCheckBox jfxCheckBox3 = new JFXCheckBox("图片详细信息");
    TextField tag = new TextField();
    TextField searchVolume = new TextField("1");
    Label label = new Label("tag:");
    Label label2 = new Label("数量:");
    Label label3 = new Label(System.getProperty("user.dir")+"\\TestPic");
    Label label4 = new Label("涩图保存路径：");

    String path = System.getProperty("user.dir")+"\\TestPic";


    @Override
    public void start(Stage stage) throws IOException {
        //创建Test所需文件夹
        File file=new File(System.getProperty("user.dir")+"\\TestPic");
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }

        MenuBar menuBar = new MenuBar();
        BorderPane root = new BorderPane();
        Pane root2 = new Pane();
        // 创建菜单
        menuBar.getStyleClass().add("zkh_MenuBar");
//        Menu menu1 = new Menu("文件");
        Menu menu2 = new Menu("   其他   ");
        MenuItem menuItem3 = new MenuItem("   关于   ");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("关于");
                alert.headerTextProperty().set("Test温馨提示：");
                alert.setWidth(350);
                alert.setHeight(400);
                textArea.setPrefWidth(427);//宽
                alert.setContentText("1.所有图片均来自 Pixiv，版权归作品的原作者所有\n2.所有涩图属于广义的涩图，你不喜欢那真是抱歉了\n3.使用tag搜索时，可能会出现重复图片，因为库里就那几张\n4.tag请尽量使用作品名而非角色名，否则可导致重复图片\n5.该程序使用LoliconAPI及pixiv.re反代制作\n6.当前图库作品数在65000左右\n7.小橹怡情，大橹伤身，强橹灰飞烟灭\n8.该软件仅供学习使用，请于下载后24小时内删除，若使用该软件造成任何法律问题与本人无关");
                alert.showAndWait();
            }
        });
        MenuItem menuItem4 = new MenuItem("   了解更多   ");
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Runtime.getRuntime().exec(
                            "cmd   /c   start https://space.bilibili.com/393070214/ ");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        menu2.getItems().addAll(menuItem3, menuItem4);

        Image testimg = new Image(getClass().getResource("/img/Test_img.jpg").toExternalForm(),270, 450, false, false);
        ImageView img = new ImageView();
        img.setImage(testimg);
        img.setLayoutX(0);
        img.setLayoutY(0);
        img.setStyle("-fx-border-color:#CACACA");
        // 创建多级菜单的父级
        Menu menu1 = new Menu("   文件   ");
        MenuItem menuItem1 = new MenuItem("打开程序根目录");
        MenuItem menuItem2 = new MenuItem("打开涩图目录");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    String[] cmd = new String[5];
                    cmd[0] = "cmd";
                    cmd[1] = "/c";
                    cmd[2] = "start";
                    cmd[3] = " ";
                    cmd[4] = System.getProperty("user.dir");
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    String[] cmd = new String[5];
                    cmd[0] = "cmd";
                    cmd[1] = "/c";
                    cmd[2] = "start";
                    cmd[3] = " ";
                    cmd[4] = path;
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//         将子菜单项添加进父级菜单
        menu1.getItems().addAll(menuItem1, menuItem2);

        // 将菜单添加进菜单栏
        menuBar.getMenus().addAll(menu1, menu2);

        /* textArea控制台样式 */
        //位置
        textArea.setLayoutX(271);
        textArea.setLayoutY(0);
        //尺寸
        textArea.setPrefHeight(250);
        textArea.setPrefWidth(427);//宽
        //设置为不可调整窗口大小
        textArea.setEditable(false);
        //自动移动到最新位置
        textArea.positionCaret(textArea.getText().length());
        textArea.setStyle("-fx-border-color:#CACACA");
//        textArea.appendText("\n\n"+"["+time+"]");
//        textArea.appendText("\nTest：内容初始化完毕！");

        jfxButton.setLayoutX(595);
        jfxButton.setLayoutY(410);
        //尺寸
        jfxButton.setPrefHeight(25);
        jfxButton.setPrefWidth(80);
        jfxButton.setStyle("-fx-background-color:#CACACA");
        //点击事件
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        jfxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                /* 获取时间 */
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                String time = formatter.format(date);

                //来张涩图！
                System.out.println();

                /**
                 * 线程池
                 */

                //获取数量
                String Volume = searchVolume.getText();
                int num = 1;
                try {
                    num = Integer.parseInt(Volume);
                }catch (Exception exception){
                    num = 0;
                    textArea.appendText("\n\n"+"["+time+"]");
                    textArea.appendText("\n"+"Test：抱歉先生,自定义下载数量只能为阿拉伯数字\n");
                }

                /**
                 * r18开关
                 * */
                String r18 = "0";
                if(jfxCheckBox.isSelected()){
                    r18 = "1";
                }

                /**
                 * tag处理
                 * */
                String Keyword = "";
                String SearchTag = tag.getText().trim();
                String type = "Image";
                try {
                    if("".equals(SearchTag)|SearchTag==null){
                        SearchTag = "";
                    }else{
                        type = "Search";
                    }
                    Keyword = URLEncoder.encode(tag.getText(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    textArea.appendText("\n\n"+"["+time+"]");
                    textArea.appendText("Test：tag搜索部分出现问题，请联系开发者！\n");
                }
                /**
                 * 图片详细信息处理
                 * */
//
                for (int i = 0; i <num; i++) {
                    final int j = i;      //关键是这一句代码，将 i 转化为  j，这样j 还是final类型的参与线程，final修饰，也保证了线程安全
                    String finalNum = 1+"";
                    String finalR18 = r18;
                    String finalKeyword = Keyword;
                    String finalType = type;
                    if(num<=0){
                        textArea.appendText("\n\n"+"["+time+"]");
                        textArea.appendText("\n"+"Test：抱歉先生,自定义下载数量不能为0或负数\n");
                        break;
                    }
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        textArea.appendText("\n\n"+"["+time+"]");
                        textArea.appendText("\nTest：出现异常，请联系开发者！["+j+"]\n");
                    }
                    new Thread(() -> {
                        executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            /**
                             * 1.获取PID
                             * 2.判断PID是否存在
                             * 3.判断线路
                             * 4.图片详细信息开关
                             * 5.使用PID下载图片
                             * */
                            //1
                            Map<String, String> keyMap = getPID.get(finalNum, finalR18, finalKeyword, finalType);
                            String ext = keyMap.get("ext");
                            if(ext==null){
                                ext = "jpg";
                            }
                            //2
                            if("NULL".equals(keyMap.get("pid"))){
                                textArea.appendText("\n\n"+"["+time+"]");
                                textArea.appendText("\nTest：您输入的tag不存在或者不合法！["+j+"]\n");
                            }else{
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    textArea.appendText("\n\n"+"["+time+"]");
                                    textArea.appendText("\nTest：出现异常，请联系开发者！["+j+"]\n");
                                }
                                if(jfxCheckBox2.isSelected()){
                                    new Thread(() -> {
                                    textArea.appendText("\n\n"+"["+time+"]");
                                    textArea.appendText("\n"+"Test：已连接到备用线路，正在下载...["+j+"]\n");
                                    }).start();
                                        if(jfxCheckBox3.isSelected()){
                                        textArea.appendText("\n标题："+keyMap.get("title")+"\npid："+keyMap.get("pid")+"\n作者："+keyMap.get("author")+"\ntags:"+keyMap.get("tags")+"\n");
                                        PicDownload.main("https://pximg.rainchan.win/img?img_id="+keyMap.get("pid"),path,keyMap.get("ext"));
                                            textArea.appendText("\n\n"+"["+time+"]");
                                            textArea.appendText("\n"+"Test：下载成功，涩图已保存！["+j+"]\n");
                                    }else{
                                        PicDownload.main("https://pximg.rainchan.win/img?img_id="+keyMap.get("pid"),path,keyMap.get("ext"));
                                            textArea.appendText("\n\n"+"["+time+"]");
                                            textArea.appendText("\n"+"Test：下载成功，涩图已保存！["+j+"]\n");
                                    }
                                }else{
                                    new Thread(() -> {
                                    textArea.appendText("\n\n"+"["+time+"]");
                                    textArea.appendText("\n"+"Test：已连接到主线路，正在下载...["+j+"]\n");
                                    }).start();
                                    if(jfxCheckBox3.isSelected()){
                                        textArea.appendText("\n标题："+keyMap.get("title")+"\npid："+keyMap.get("pid")+"\n作者："+keyMap.get("author")+"\ntags:"+keyMap.get("tags")+"\n");
                                        PicDownload.main("https://pixiv.re/"+keyMap.get("pid")+"."+keyMap.get("ext"),path,keyMap.get("ext"));
                                        textArea.appendText("\n\n"+"["+time+"]");
                                        textArea.appendText("\n"+"Test：下载成功，涩图已保存！["+j+"]\n");
                                    }else{
                                        PicDownload.main("https://pixiv.re/"+keyMap.get("pid")+"."+keyMap.get("ext"),path,keyMap.get("ext"));
                                        textArea.appendText("\n\n"+"["+time+"]");
                                        textArea.appendText("\n"+"Test：下载成功，涩图已保存！["+j+"]\n");
                                    }

                                }

                            }

                        }
                    });
                    }).start();
                }
            }
        });


        jfxButton2.setLayoutX(595);
        jfxButton2.setLayoutY(370);
        //尺寸
        jfxButton2.setPrefHeight(25);
        jfxButton2.setPrefWidth(80);
        jfxButton2.setStyle("-fx-background-color:#CACACA");
        //点击事件
        jfxButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //来张涩图！
                DirectoryChooser directoryChooser=new DirectoryChooser();
                File file = directoryChooser.showDialog(stage);
                try {
                    path = file.getPath();
                }catch (Exception e){
                    path = System.getProperty("user.dir")+"\\TestPic";
                }
                label3.setText(path);
                System.out.println(path);
            }
        });

        /* r18开关 */
        jfxCheckBox.setLayoutX(285);
        jfxCheckBox.setLayoutY(270);
        //尺寸
        jfxCheckBox.setPrefHeight(25);
        jfxCheckBox.setPrefWidth(80);

        /* 图片详细信息显示开关 */
        jfxCheckBox2.setLayoutX(390);
        jfxCheckBox2.setLayoutY(270);
        //尺寸
        jfxCheckBox2.setPrefHeight(25);
        jfxCheckBox2.setPrefWidth(80);

        /* 备用线路开关 */
        jfxCheckBox3.setLayoutX(528);
        jfxCheckBox3.setLayoutY(270);
        //尺寸
        jfxCheckBox3.setPrefHeight(25);
        jfxCheckBox3.setPrefWidth(80);

        /* 搜索框  */
        //位置
        tag.setLayoutX(315);//450
        tag.setLayoutY(410);
        //尺寸
        tag.setPrefHeight(25);
        tag.setPrefWidth(100);

        /* 搜索量框  */
        //位置
        searchVolume.setLayoutX(460);//450
        searchVolume.setLayoutY(410);
        //尺寸
        searchVolume.setPrefHeight(25);
        searchVolume.setPrefWidth(100);

        /* 文本"数量" */
        //位置
        label.setLayoutX(285);
        label.setLayoutY(410);
        //尺寸
        label.setPrefHeight(25);
        label.setPrefWidth(50);

        /* 文本"tag" */
        //位置
        label2.setLayoutX(425);
        label2.setLayoutY(410);
        //尺寸
        label2.setPrefHeight(25);
        label2.setPrefWidth(50);

        /* 文本"路径" */
        //位置
        label3.setLayoutX(285);
        label3.setLayoutY(370);
        //尺寸
        label3.setPrefHeight(25);
        label3.setPrefWidth(280);

        /* 文本"图片路径" */
        //位置
        label4.setLayoutX(285);
        label4.setLayoutY(350);
        //尺寸
        label4.setPrefHeight(25);
        label4.setPrefWidth(280);


        root2.getChildren().addAll(img,textArea,jfxButton,jfxButton2,jfxCheckBox,jfxCheckBox2,jfxCheckBox3,label,label2,label3,label4,tag,searchVolume);
        root.setTop(menuBar);
        root.setCenter(root2);
        Scene scene = new Scene(root, 700, 475, Color.WHITE);
//        scene.getStylesheets().add("file:src/main/resources/css/app.css");
        scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        stage.setTitle("TestChan|一个简单的涩图下载器！");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.print("监听到窗口关闭，关闭线程池。");
                //结束线程池，防止程序关闭后还在后台运行
                executor.shutdown();
            }
        });
        stage.setScene(scene);
        stage.setResizable(false);
//        stage.getIcons().add(new Image("file:src/main/resources/img/Test_ico.jpg"));
        stage.getIcons().add(new Image(getClass().getResource("/img/Test_ico.jpg").toExternalForm()));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}