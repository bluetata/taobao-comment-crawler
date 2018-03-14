package org.youchien.taobao_comment;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AppRun {

    public static void main(String[] args) {
        SearchComment searchComment = new SearchComment();
        List<String> commList = searchComment.findId("小红书");
        for (String str : commList) {
            System.out.println(str);
            JSONObject parseObject = JSONObject.parseObject(str);
            JSONArray jsonArray = parseObject.getJSONArray("comments");

            if (jsonArray.size() > 0) {
                for (int j = 0; j < jsonArray.size(); j++) {

                    System.out.println(jsonArray.getJSONObject(j).getString("content"));
                }
            } else {
                System.out.println("暂无评论");
            }
        }
    }

}
