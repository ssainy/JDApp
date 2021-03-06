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
public class Test_ddhError_illegal {
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
                    {"0","你好你好你好你好你好"},
            });
        }
        private String id;
        private String ddh;

        public TestClass(String id,String ddh)
        {
            this.id=id;
            this.ddh=ddh;
        }
        @Test
        public void test_ddhError_illegal() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"ddh",ddh);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("ddh不能包含汉字!",result);
        }
    }
}
