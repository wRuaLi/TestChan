package com.mineir.wjy.testchan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class getPID {
    public static Map<String,String> get(String num,String r18,String tag,String type) {
        String data = null;
        try {
            //建立连接
            URL url = new URL("https://api.lolicon.app/setu/v2?size=original&size=regular&proxy=i.pixiv.re&tag=" + tag + "&r18=" + r18 + "&num=" + num);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            data = br.readLine();
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> keyMap = new HashMap<>();
        System.out.println(type);
        if (type.equals("Search")) {
            JSONObject json = JSONObject.parseObject(data);
            JSONArray APIdata = JSONObject.parseArray(json.getString("data"));
            if (APIdata.isEmpty()) {
                System.out.println("群友输入的tag不存在或者不合法！");
                keyMap.put("pid", "NULL");
                return keyMap;
//				return "NULL";
            } else {
                System.out.println("已搜索到tag");
            }
            JSONObject APIdataArray = JSONObject.parseObject(APIdata.getString(0));
            JSONObject urls = JSONObject.parseObject(APIdataArray.getString("urls"));
            String pid = APIdataArray.getString("pid");
            String ext = APIdataArray.getString("ext");
            String original = urls.getString("original");
            String tags = APIdataArray.getString("tags");
            String title = APIdataArray.getString("title");
            String author = APIdataArray.getString("author");
            System.out.println(original);
            keyMap.put("pid", pid);
            keyMap.put("ext", ext);
            keyMap.put("urls", original);
            keyMap.put("tags", tags);
            keyMap.put("title", title);
            keyMap.put("author", author);
            return keyMap;
            //type图片 命令“来点涩图”
        } else {
            //获取json字符串
            JSONObject json = JSONObject.parseObject(data);
            //解析字符串data
            JSONArray APIdata = JSONObject.parseArray(json.getString("data"));
            //解析数组dataArray
            JSONObject APIdataArray = JSONObject.parseObject(APIdata.getString(0));
            //解析字符串urls
            JSONObject urls = JSONObject.parseObject(APIdataArray.getString("urls"));
            String pid = APIdataArray.getString("pid");
            String ext = APIdataArray.getString("ext");
            //得到需要的图片链接
            String original = urls.getString("original");
            String tags = APIdataArray.getString("tags");
            String title = APIdataArray.getString("title");
            String author = APIdataArray.getString("author");
            keyMap.put("pid", pid);
            keyMap.put("ext", ext);
            keyMap.put("urls", original);
            keyMap.put("tags", tags);
            keyMap.put("title", title);
            keyMap.put("author", author);
            System.out.println(keyMap);
            return keyMap;
        }
    }
}
