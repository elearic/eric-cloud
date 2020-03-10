package common.utils;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUtil {
	public static String post(String url, Map<String,String> headers, Map<String,String> params){
		 	String body = "";
	        try {
				//创建httpclient对象
				CloseableHttpClient client = HttpClients.createDefault();
				//创建post方式请求对象
				HttpPost httpPost = new HttpPost(url);
				List<NameValuePair> nameValuePairs = Lists.newArrayList();
				if(params!=null){
					Set<Entry<String, String>> entrySet = params.entrySet();
					for (Entry<String, String> entry : entrySet) {
						nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}
				}
				//解决中文乱码问题
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs ,"utf-8");
				entity.setContentEncoding("UTF-8");    
				entity.setContentType("application/x-www-form-urlencoded;charset=utf-8");    
				httpPost.setEntity(entity);
				if(headers!= null){
					Set<Entry<String, String>> entrySet = headers.entrySet();
					for (Entry<String, String> entry : entrySet) {
						httpPost.setHeader(entry.getKey(),entry.getValue());
					}
				}
				//执行请求操作，并拿到结果（同步阻塞）
				CloseableHttpResponse response = client.execute(httpPost);
				//获取结果实体
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
				    //按指定编码转换结果实体为String类型
				    body = EntityUtils.toString(responseEntity, "utf-8");
				}
				EntityUtils.consume(entity);
				//释放链接
				response.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return body;
	}
}


