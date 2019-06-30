package java.com.qa.test;



import java.com.qa.baseclass.BaseClass;
import java.com.qa.data.User;
import java.com.qa.restClient.RestClient;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PostAPITest extends BaseClass{
	
	RestClient restclient;
	String url;
	String servicesUrl;
	String uri;
	@BeforeMethod
	public void setup() {
		url=prop.getProperty("url");
		servicesUrl=prop.getProperty("servicesurl");
	    uri=url+servicesUrl;
	}
	
	@Test
	public void postCall() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		restclient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//converting java object to Json object
		//Marshling 
		ObjectMapper objmapper= new ObjectMapper();
		User user=new User("morpheus","leader");
		
		//object to json file conversion
		objmapper.writeValue(new File("D:\\JavaProject\\RestWebServicesAutomation\\src\\main\\java\\com\\qa\\config\\user.json"), user);
		
		String userJsonString = objmapper.writeValueAsString(user);
		System.out.println(userJsonString);
		
		CloseableHttpResponse responce=restclient.post(uri, userJsonString, headerMap);
		
		int statuscode=responce.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 201);
		
		String jsonresponce=EntityUtils.toString(responce.getEntity(),"UTF-8");
		
		JSONObject jsonobject=new JSONObject(jsonresponce);
		 
		//Json to Java Object
		//Unmarshling
		User userresponce=objmapper.readValue(jsonresponce, User.class);
		System.out.println(userresponce);
		
		Assert.assertEquals(user.getName(), userresponce.getName());
		
		Assert.assertEquals(user.getJob(),userresponce.getJob());
		
	}

}
