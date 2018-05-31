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
public class Test_nsrsbhRight {
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
                    {"0","123456789456123"},
                    {"1","1234567890aaaaa"},
                    {"2","11111111111111111"},
                    {"3","1111111111aaaaaaa"},
                    {"4","123456789123456789"},
                    {"5","11111111111aaaaaaa"},
                    {"6","12345678965412365412"},
                    {"7","1111111111aaaaaaaaaa"},
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
        public void test_nsrsbhRight() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"nsrsbh",nsrsbh);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("数据正常!",result);
        }
    }
}
