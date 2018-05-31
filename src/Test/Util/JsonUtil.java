package Test.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by cch on 2017/5/15.
 */
public class JsonUtil {
    public static String jsondata(String json,String key1,String value){
        JSONObject s1 = JSONObject.fromObject(json);
        s1.put(key1,value);
        String injson = s1.toString();
        return injson;
    }
    public static String getjsondata(String json,String key1){
        JSONObject s1 = JSONObject.fromObject(json);
        String value=s1.getString(key1).toString();
        return value;
    }
    public static String getjsondata(String json,String key1,String key2){
        JSONObject s1 = JSONObject.fromObject(json);
        JSONObject s2 = JSONObject.fromObject(s1.get(key1));
        String value=s2.getString(key2).toString();
        return value;
    }
    public static String jsondata(String json,String key1,String key2,String value){
        JSONObject s1 = JSONObject.fromObject(json);
        JSONObject s2 = JSONObject.fromObject(s1.get(key1));
        s2.put(key2,value);
        String send_request=s2.toString();
        s1.put(key1,send_request);
        String injson = s1.toString();
        return injson;
    }
    public static String jsonArrayData(String json,String arraykey,String key,String value){
        JSONObject s1 = JSONObject.fromObject(json);
        JSONArray jsonArray =s1.getJSONArray(arraykey);
        String injson=null;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jo = jsonArray.getJSONObject(i);
            jo.put(key,value);
            System.out.println("*************"+jo);
            jsonArray.set(0,jo);
            String ss=jsonArray.toString();
            s1.put(arraykey,ss);
            injson = s1.toString();
            System.out.println("发送报文：" + injson);

        }
        return injson;
    }

}
