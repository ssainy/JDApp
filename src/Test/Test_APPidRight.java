package Test;

import Test.Util.CheckData;
import Test.Util.HttpManager;
import Test.Util.JsonUtil;
import Test.Util.ReadProper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by cch on 2017/1/6.
 */
public class Test_APPidRight {
    public static Properties properties= ReadProper.readproper("/properties.properties");
    @RunWith(Parameterized.class)
    public static class TestClass {
        String url=properties.getProperty("url");
        /**
         * 这里的返回至少是二维数组
         * @return
         */
        @Parameterized.Parameters
        public static List<String[]> getParams() {
            return Arrays.asList(new String[][]{
                    {"0","12345678901234567890"},
                    {"1","1234567894561236547"},
                    {"2","AAAAASSSSSDDDDDFFFF"},
                    {"4","AAAAASSSSSDDDDDFFFFF"},
                    {"5","1234567890aaaaa$$$$"},
                    {"6","1234567890aaaaa$$$$$"},
            });
        }
        private String id;
        private String appid;    //用户名

        public TestClass(String id,String appid)
        {
            this.id=id;
            this.appid=appid;
        }
        @Test
        public void test_APPidRight() throws Exception {
           // String json="{\"appid\":\""+appid+"\",\"ddh\":\"testlichen2233334463\",\"lsh\":\"4141341lc12156434056\",\"nsrsbh\":\"150001205010278267\",\"fpdm\":\"161117103933\",\"fphm\":\"10872725\",\"appType\":\"0\",\"grantType\":\"0\"}";
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"appid",appid);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("数据正常!",result);
        }
    }
}
