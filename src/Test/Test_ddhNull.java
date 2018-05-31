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
public class Test_ddhNull {
    public static Properties properties= ReadProper.readproper("/properties.properties");
        @Test
        public void testddhnull_fpdmnull_fphmnull() throws Exception {
            String json="{\"appid\":\"12345678965412312365\",\"ddh\":\"\",\"lsh\":\"4141341lc12156434056\",\"nsrsbh\":\"150001205010278267\",\"fpdm\":\"\",\"fphm\":\"\",\"appType\":\"1\",\"grantType\":\"1\"}";
            String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(json));
            String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
            Assert.assertEquals("订单号为空时fpdm不能为空且长度为10或12!",result);
        }
    @Test
    public void testddhnull_fpdmnull_fphm() throws Exception {
        String json="{\"appid\":\"12345678965412312365\",\"ddh\":\"\",\"lsh\":\"4141341lc12156434056\",\"nsrsbh\":\"150001205010278267\",\"fpdm\":\"\",\"fphm\":\"10872725\",\"appType\":\"1\",\"grantType\":\"1\"}";
        String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(json));
        String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
        Assert.assertEquals("订单号为空时fpdm不能为空且长度为10或12!",result);
    }
    @Test
    public void testddhnull_fpdm_fphmnull() throws Exception {
        String json="{\"appid\":\"12345678965412312365\",\"ddh\":\"\",\"lsh\":\"4141341lc12156434056\",\"nsrsbh\":\"150001205010278267\",\"fpdm\":\"161117103933\",\"fphm\":\"\",\"appType\":\"1\",\"grantType\":\"1\"}";
        String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(json));
        String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
        Assert.assertEquals("订单号为空时fphm不能为空且长度为8!",result);
    }
    @Test
    public void testddh_fpdmnull_fphmnull() throws Exception {
        String json="{\"appid\":\"12345678965412312365\",\"ddh\":\"testlichen2233334463\",\"lsh\":\"4141341lc12156434056\",\"nsrsbh\":\"150001205010278267\",\"fpdm\":\"\",\"fphm\":\"\",\"appType\":\"1\",\"grantType\":\"1\"}";
        String ret = HttpManager.HttpC(properties.getProperty("url"), CheckData.dealData(json));
        String result= JsonUtil.getjsondata(ret,"returnStateInfo","returnMessage");
        Assert.assertEquals("数据正常!",result);
    }
    }

