import Test.Util.Base64Util;
import Test.Util.CheckData;
import com.dxhy.openApi.SdkException;
import com.dxhy.openApi.bean.protocol.Data;
import com.dxhy.openApi.bean.protocol.GlobalInfo;
import com.dxhy.openApi.service.OpenApiSdk;
import com.dxhy.openApi.service.SdkInitialize;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cch on 2016/12/18.
 */
public class OpenAPISdkTest {
    public static String GlobalInfo1;
    public static String GlobalInfo2;
    public static String enterpriseInfo1;
    public static String enterpriseInfo2;
    public static String userInfo1;
    public static String userInfo2;

    private static Logger logger= LoggerFactory.getLogger(OpenAPISdkTest.class);
    public static String[] doApi(String url,String send_json,String lsh){
        String[] arr = new String[2];
        try{
            GlobalInfo globalInfo=new GlobalInfo(GlobalInfo1,GlobalInfo2).enterpriseInfo(enterpriseInfo1,enterpriseInfo2).userInfo(userInfo1,userInfo2);
            SdkInitialize sdkInitialize=SdkInitialize.getInstance();
            sdkInitialize.init(globalInfo);
            Data data=new Data(send_json);
            OpenApiSdk openApiSdk=OpenApiSdk.getInstance();
            String str= openApiSdk.deal(url,data,lsh);
            System.out.println("返回数据为："+str);
            arr[0]="0000";
            arr[1]=str;
            return arr;
        }catch(SdkException e){
            //e.printStackTrace();
            System.out.println( e.toString());
            String str=e.toString();
            arr[0]="9999";
            arr[1]=str;
            return arr;
        }
    }
    public static void main(String args[]){
        //String json="{\"fpkjxx_fptxx\":{\"bmb_bbh\":\"\",\"bz\":\"无\",\"chyy\":\"\",\"ddh\":\"lc20161218020\",\"ddsj\":\"2016-12-18 12:12:32\",\"dsptbm\":\"11109190\",\"fhr\":\"刘老二\",\"fpqqlsh\":\"11109190lc2016121864\",\"ghf_dh\":\"18510936612\",\"ghf_dz\":\"海淀\",\"ghf_email\":\"\",\"ghf_yhzh\":\"662626265665656\",\"ghfmc\":\"刘老三\",\"kphjje\":\"2373.93\",\"kplx\":\"1\",\"kprq\":\"2016-05-16 12:15:22\",\"kpxm\":\"笔记本电脑\",\"kpy\":\"刘老大\",\"nsrdzdah\":\"565656565\",\"nsrmc\":\"51性能测试2\",\"nsrsbh\":\"150001205010278267\",\"qd_bz\":\"0\",\"qdxmmc\":\"就对啦发\",\"xhf_dz\":\"京东\",\"xhf_dh\":\"18555555555\",\"xhf_yhzh\":\"666226265646626656\",\"ghf_nsrsbh\":\"123456789098765\",\"sky\":\"刘老四\",\"yfp_dm\":\"65656\",\"yfp_hm\":\"56566\"},\"fpkjxx_xmxxs\":[{\"fphxz\":\"1\",\"ggxh\":\"thinkpad e40\",\"hsbz\":\"1\",\"lslbs\":\"1\",\"sl\":\"0.17\",\"spbm\":\"0003\",\"xmdj\":\"2029.01\",\"xmdw\":\"台\",\"xmje\":\"2029.00\",\"xmmc\":\"笔记本电脑\",\"xmsl\":\"1\",\"yhzcbs\":\"1\",\"zxbm\":\"113\",\"zzstsgl\":\"0\"}]}";
       // String json="{\"fpkjxx_fptxx\":{\"bmb_bbh\":\"\",\"bz\":\"\",\"ddh\":\"qhc2016121614\",\"chyy\":\"\",\"ddsj\":\"2016-12-31 10:12:32\",\"dsptbm\":\"11109190\",\"fhr\":\"祁宏策\",\"fpqqlsh\":\"11109190711222151150\",\"ghf_dh\":\"15811406913\",\"ghf_dz\":\"佟家坟甲18号\",\"ghf_email\":\"526765854@qq.com\",\"ghf_yhzh\":\"\",\"ghfmc\":\"祁少\",\"kphjje\":\"10000.00\",\"kplx\":\"1\",\"kprq\":\"2016-12-31 10:12:32\",\"kpxm\":\"外围足彩\",\"kpy\":\"祁宏策\",\"nsrdzdah\":\"45632849548\",\"nsrmc\":\"51性能测试2\",\"nsrsbh\":\"150001205010278267\",\"qd_bz\":\"0\",\"qdxmmc\":\"\",\"xhf_dz\":\"北京市丰台区首经贸地铁站\",\"xhf_dh\":\"15811406911\",\"xhf_yhzh\":\"666226265646626656\",\"ghf_nsrsbh\":\"\",\"sky\":\"阿拉妈妈\",\"yfp_dm\":\"\",\"yfp_hm\":\"\"},\"fpkjxx_xmxxs\":[{\"fphxz\":\"0\",\"ggxh\":\"竞彩足球2串1\",\"hsbz\":\"1\",\"lslbs\":\"\",\"sl\":\"0.17\",\"spbm\":\"\",\"xmdj\":\"10000.00\",\"xmdw\":\"台\",\"xmje\":\"10000.00\",\"xmmc\":\"项目1\",\"xmsl\":\"1\",\"yhzcbs\":\"\",\"zxbm\":\"\",\"zzstsgl\":\"\"}]}";
       String json="{\n" +
               "\"appid\":\"6543241223\",\n" +
               "\"ddh\":\"\",\n" +
               "\"lsh\":\"wss20160103000000001\",\n" +
               "\"nsrsbh\":\"110106198808012713\",\n" +
               "\"fpdm\":\"111222055569\",\n" +
               "\"fphm\":\"56421541\",\n" +
               "\"appType\":\"0\",\n" +
               "\"grantType\":\"0\"\n" +
               "}";
        OpenAPISdkTest.GlobalInfo1 = "00";
        OpenAPISdkTest.GlobalInfo2 = "2.0";
        OpenAPISdkTest.enterpriseInfo1 = "APP000000000001";
        OpenAPISdkTest.enterpriseInfo2 = "jd000001";
        OpenAPISdkTest.userInfo1 = "p0000008";
        OpenAPISdkTest.userInfo2 = "10000000";
        //String[] resstr = OpenAPISdkTest.doApi(str_Url, injson,qqlsh);
        String[] resstr = doApi("http://192.168.8.147:9090/api3.0/outerdispatcher/orderSl", json,"test123901320161222151447");
        System.out.println("返回数据为："+resstr[1]);
        JSONObject responsedatacontent = JSONObject.fromObject(resstr);String url=responsedatacontent.get("content").toString();

        String urlnew= Base64Util.decodeutf8(url);

        JSONObject reply_message = JSONObject.fromObject(urlnew);
        System.out.println("the reply_message is :"+reply_message.get("reply_message"));

    }
}
