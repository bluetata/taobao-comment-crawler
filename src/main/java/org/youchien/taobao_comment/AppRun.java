package org.youchien.taobao_comment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AppRun {

    public static void main(String[] args) {
        String filepath = "D:\\001_workspaces\\90_CrawlerData";
        String filename = "";
        
        SearchComment searchComment = new SearchComment();
        List<String> commList = searchComment.findId("java编程思想");
        for (String str : commList) {
            System.out.println(str);
            // good id :563647803186
            filename = str.substring(0, str.indexOf("#"));
            // write2File(filename, filepath, str);
            JSONObject parseObject = JSONObject.parseObject(str.substring(str.indexOf("#") + 1));
            JSONArray jsonArray = parseObject.getJSONArray("comments");

            if (jsonArray.size() > 0) {
                for (int j = 0; j < jsonArray.size(); j++) {

                    System.out.println(jsonArray.getJSONObject(j).getString("content"));
                    write2File(filename, filepath, jsonArray.getJSONObject(j).getString("content") + "\r\n");
                }
            } else {
                System.out.println("暂无评论");
            }
        }
    }

    public static void write2File(String filename, String path, String writenString) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path + "\\" + filename + ".txt", true));
            writer.write(writenString);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
