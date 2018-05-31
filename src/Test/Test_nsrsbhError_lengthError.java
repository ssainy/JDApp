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
 * Created by cch on 2017/1/4.
 */
public class Test_nsrsbhError_lengthError {
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
                    {"0",""},
                    {"1","12345678945612"},
                    {"2","1234567aaaaaaa"},
                    {"3","1234567894561230"},
                    {"4","123456789aaaaaaa"},
                    {"5","1234567894561230123"},
                    {"6","1234567890aaaaaaaaa"},
                    {"7","123456789456123456789"},
                    {"8","123456711890aaaaaaaaa"},
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
        public void test_nsrsbhError_lengthError() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"nsrsbh",nsrsbh);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("Nsrsbh不能为空且长度位15,17,18,20!",result);
        }
    }
}
