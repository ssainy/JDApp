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
public class Test_fphmRight {
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
                    {"0","11111111"},
                    {"1","00011111"},
            });
        }
        private String id;
        private String fphm;

        public TestClass(String id,String fphm)
        {
            this.id=id;
            this.fphm=fphm;
        }
        @Test
        public void test_fphmRight() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"fphm",fphm);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("数据正常!",result);
        }
    }
}
