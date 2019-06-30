package java.com.qa.test;



import java.com.qa.baseclass.BaseClass;
import java.com.qa.restClient.RestClient;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class GetAPITest extends BaseClass {
	BaseClass bs;
	String baseurl;
	String serviceurl;
	String uri;
	RestClient rest;
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException, JSONException {
		bs=new BaseClass();
		baseurl=prop.getProperty("url");
		serviceurl=prop.getProperty("servicesurl");
		
		uri=baseurl+serviceurl;
		
	}
	
	@Test
	public void getTest() throws ClientProtocolException, IOException, JSONException {
		rest=new RestClient();
		
		
		CloseableHttpResponse httpresponce=rest.get(uri);
		
		int statusCode=httpresponce.getStatusLine().getStatusCode();
		System.out.println("Status code=="+statusCode);
		
		Assert.assertEquals(statusCode, ATATUS_CODE_200,"Status code ");
		
		String responceString=EntityUtils.toString(httpresponce.getEntity(),"UFT-8");
		JSONObject jsonresponce=new JSONObject(responceString);
		
		
		
		System.out.println("Json responce ==="+jsonresponce);
	
		Header[] header=httpresponce.getAllHeaders();
		HashMap<String,String> allheader=new HashMap<String ,String>();
		
		for(Header h:header) {
			allheader.put(h.getName(),h.getValue());	
		}
		
		System.out.println("Headers"+allheader);
	}

}
