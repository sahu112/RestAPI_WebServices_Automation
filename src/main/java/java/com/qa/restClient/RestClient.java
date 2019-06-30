package java.com.qa.restClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {
	
	public  CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		HttpGet httpget=new HttpGet(url);
		
		CloseableHttpResponse httpresponce=httpclient.execute(httpget);
		
		return httpresponce;

}
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);//url
		httpPost.setEntity(new StringEntity(entityString));//payload
		
		for(Map.Entry<String, String> entry:headermap.entrySet()) {
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse httpResponce=httpclient.execute(httpPost);
		
		return httpResponce;
		
	}
	
	
}
