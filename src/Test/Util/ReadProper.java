package Test.Util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;

/**
 * Created by cch on 2017/4/12.
 */
public class ReadProper {
    public static Properties readproper(String filepath) {
        InputStream resourceAsStream = Class.class.getResourceAsStream(filepath);
        if (resourceAsStream == null) {
            return null;
        }
        Properties properties = new Properties();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
            properties.load(reader);
        } catch (IOException e) {
        }
        return properties;
    }
    public static String readFile(String path) throws IOException {
        File pathjson = new File(path); //获取外层报文所在地址
        FileInputStream inner = new FileInputStream(pathjson);
        File gf = new File(pathjson.toString());
        inner = new FileInputStream(gf);
        String content = IOUtils.toString(inner, "UTF-8");
        return content;
    }
}
