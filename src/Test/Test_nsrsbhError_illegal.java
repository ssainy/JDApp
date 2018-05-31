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
public class Test_nsrsbhError_illegal {
    public static Properties properties= ReadProper.readproper("/properties.properties");
    @RunWith(Parameterized.class)
    public static class TestClass {
        String url=" http://192.168.8.147:9090/api3.0/outerdispatcher/orderReceive";
        /**
         * 这里的返回至少是二维数组
         * @return
         */
        @Parameterized.Parameters
        public static List<String[]> getParams() {
            return Arrays.asList(new String[][]{
                    {"0","####################"},
                    {"1","aaaaaaaaaaaaaaa"},
                    {"2","aaaaaaaaaaaaaaaaa"},
                    {"3","aaaaaaaaaaaaaaaaaa"},
                    {"4","aaaaaaaaaaaaaaaaaaaa"},
            });
        }
        private String id;
        private String nsrsbh;

        public TestClass(String id,String nsrsbh)
        {
            this.id=id;
            this.nsrsbh=nsrsbh;
        }
        @Test
        public void test_nsrsbhError_illegal() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"nsrsbh",nsrsbh);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("Nsrsbh不能包含特殊字符,只能由字母数字组成!",result);
        }
    }
}
