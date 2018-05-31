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
 * Created by cch on 2017/1/7.
 */
public class Test_AppIdError_illegal {
    public static Properties properties= ReadProper.readproper("/properties.properties");
    @RunWith(Parameterized.class)
    public static class TestClass {

        /**
         * 这里的返回至少是二维数组
         * @return
         */
        @Parameterized.Parameters
        public static List<String[]> getParams() {
            return Arrays.asList(new String[][]{
                    {"0","你好你好你好你好你好"},
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
        public void testAPPIDerror() throws Exception {
            //String innerjson="{\"appid\":\""+appid+"\"," + "\"ddh\":\"testlichen2233334463\"," + "\"lsh\":\"4141341lc12156434056\"," + "\"nsrsbh\":\"150001205010278267\"," + "\"fpdm\":\"161117103933\"," + "\"fphm\":\"10872725\"," + "\"appType\":\"0\"," + "\"grantType\":\"0\"}";
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"appid",appid);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("appid不能包含汉字!",result);
    }
    }
}
