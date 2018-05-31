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
 * Created by cch on 2017/1/5.
 */
public class Test_grantTypeError {
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
                    {"0","4"},
                    {"1","a"},
                    {"2","#"},
                    {"3","##"},
                    {"4","牛"},
                    {"5","33"},
                    {"6","aa"},
                    {"7","-1"}
            });
        }
        private String id;
        private String grantType;

        public TestClass(String id,String grantType)
        {
            this.id=id;
            this.grantType=grantType;
        }
        @Test
        public void test_grantTypeError() throws Exception {
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"grantType",grantType);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("GrantType长度为1且值必须为0,1,2,3!",result);
        }
    }
}
