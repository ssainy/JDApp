package Test;

import Test.Util.CheckData;
import Test.Util.HttpManager;
import Test.Util.JsonUtil;
import Test.Util.ReadProper;
import com.dxhy.openApi.SdkException;
import com.dxhy.openApi.bean.protocol.Data;
import com.dxhy.openApi.bean.protocol.GlobalInfo;
import com.dxhy.openApi.service.OpenApiSdk;
import com.dxhy.openApi.service.SdkInitialize;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by cch on 2016/12/6.
 */
public class Test_AppIdError_lengthError{
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
                    {"0",""},
                    {"1","123456789654123123651"},
                    {"2","asdfghjklmnbvcxzaqwer"},
                    {"3","$$$$$$$$$$$$$$$$$$$$$"},
                    {"4","1234567890asdfghh####"}
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
        public void test_AppIdError_lengthError() throws Exception {
          //  String innerjson="{\"appid\":\""+appid+"\"," + "\"ddh\":\"testlichen2233334463\"," + "\"lsh\":\"4141341lc12156434056\"," + "\"nsrsbh\":\"150001205010278267\"," + "\"fpdm\":\"161117103933\"," + "\"fphm\":\"10872725\"," + "\"appType\":\"0\"," + "\"grantType\":\"0\"}";
            String json=properties.getProperty("json");
            String innerjson= JsonUtil.jsondata(json,"appid",appid);
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(innerjson));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("appid不能为空且长度不超过20!",result);
        }
    }
    }

