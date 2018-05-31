package Test.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import com.sun.deploy.net.HttpResponse;
import com.swetake.util.Qrcode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.imageio.ImageIO;

public class HttpManager {
	public static String getGetResponse(String url) {
		String html = "";
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			// 处理内容
			html = getMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return html;
	}
	public static String HttpC(String url,String  str_send){
		PostMethod postMethod = null;
		HttpClient httpClient = new HttpClient();

		postMethod  = new PostMethod(url);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000000);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000000);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		try{

			httpClient.getParams().setContentCharset("UTF-8");
			RequestEntity request = new StringRequestEntity(str_send,"textml","UTF-8");
			//System.out.println(request);
			postMethod.setRequestEntity(request);

			int statusCode = httpClient.executeMethod(postMethod);
			if (200 == statusCode) {
				byte[] responseBody = postMethod.getResponseBody();

				// 处理内容
				String respXML = new String(responseBody, "UTF-8");
				return respXML;

			}else{

				return("POST请求失败，返回状态："+ statusCode);
			}
		}
		catch(Exception e){
			return("请求出错：" + e.toString());
		}
	}
	public static void encoderQRCode(String content, String imgPath) {
		try {

			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(15);

			System.out.println(content);
			byte[] contentBytes = content.getBytes("gb2312");

			BufferedImage bufImg = new BufferedImage(240, 240,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 250, 250);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 420) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+contentBytes.length + " not in [ 0,120 ]. ");
			}

			gs.dispose();
			bufImg.flush();

			File imgFile = new File(imgPath);

			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "jpg", imgFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
