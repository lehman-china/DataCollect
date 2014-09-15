package main.amos.vo;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResponseResutlVo {

	private CloseableHttpClient closeableHttpClient;
	private HttpResponse httpResponse;
	private String headr;
	private String content;
	private JSONObject jsonContent;

	public ResponseResutlVo() {
	}

	public ResponseResutlVo( CloseableHttpClient closeableHttpClient, HttpResponse httpResponse, String headr, String content, JSONObject jsonContent ) {
		super();
		this.closeableHttpClient = closeableHttpClient;
		this.httpResponse = httpResponse;
		this.headr = headr;
		this.content = content;
		this.jsonContent = jsonContent;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse( HttpResponse httpResponse ) {
		this.httpResponse = httpResponse;
	}

	public String getHeadr() {
		return headr;
	}

	public void setHeadr( String headr ) {
		this.headr = headr;
	}

	public String getContent() {
		return content;
	}

	public void setContent( String content ) {
		try {
			jsonContent = JSON.parseObject( content );
		} catch ( Exception e ) {
		}
		this.content = content;
	}

	public CloseableHttpClient getCloseableHttpClient() {
		return closeableHttpClient;
	}

	public void setCloseableHttpClient( CloseableHttpClient closeableHttpClient ) {
		this.closeableHttpClient = closeableHttpClient;
	}

	public JSONObject getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent( JSONObject jsonContent ) {
		this.jsonContent = jsonContent;
	}

}
