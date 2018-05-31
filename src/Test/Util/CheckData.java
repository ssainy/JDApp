package Test.Util;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by cch on 2017/1/4.
 */
public class CheckData {
    public static String dealData(String json) throws IOException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String starttime = sdf1.format(new Date());
        String fpqqlsh = "123" + (int) (Math.random() * 9000 + 1000) + starttime;
        String dataExchangeId="test123" +(int)(Math.random()*9000+1000) + starttime;
        String injson = JsonUtil.jsondata(json,"lsh",fpqqlsh);
        System.out.println("the injson is :" + injson);
        String send_content = Base64Util.encodeutf8(injson);
        String path = System.getProperty("user.dir");
        File pathjson = new File(path + "\\global_model.json"); //获取外层报文所在地址
        File gf = new File(pathjson.toString());
        FileInputStream inner = new FileInputStream(gf);
        String outjson = IOUtils.toString(inner, "UTF-8");
        //将内层报文的数据加密后放到外层报文的content中
        String send_json1=JsonUtil.jsondata(outjson,"data","content",send_content);
        String send_request1=JsonUtil.jsondata(send_json1,"globalInfo","dataExchangeId",dataExchangeId);
        System.out.println("传送的数据为："+send_request1);
        return send_request1;
    }
    public static void dealResult(){

    }
    }
