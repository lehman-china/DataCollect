package main.amos.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.amos.vo.ResponseResutlVo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private CloseableHttpClient httpClient = HttpClients.createDefault();

	public ResponseResutlVo get( String url, Map<String, String> params ) {

		ResponseResutlVo responseResutlVo = new ResponseResutlVo();// 响应结果 vo
		try {
			HttpGet httpGet = new HttpGet( url );
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 3000 ).setConnectTimeout( 3000 ).build();
			httpGet.setConfig( requestConfig );
			// 设置get 的参数
			if ( params != null ) {
				// post 参数
				StringBuffer parameters = new StringBuffer();
				for ( Entry<String, String> entry : params.entrySet() ) {
					parameters.append( entry.getKey() + "=" + entry.getValue() + "&" );
				}
				url += "?" + params.toString();
			}

			HttpResponse httpResponse = httpClient.execute( httpGet );// 执行请求
			if ( httpResponse.getStatusLine().getStatusCode() == 200 ) {
				StringBuffer headr = new StringBuffer();
				for ( Header head : httpResponse.getAllHeaders() ) {
					headr.append( head + "\n" );
				}
				HttpEntity entity = httpResponse.getEntity();
				String content = EntityUtils.toString( entity );
				responseResutlVo.setCloseableHttpClient( httpClient );
				responseResutlVo.setHttpResponse( httpResponse );
				responseResutlVo.setHeadr( headr.toString() );
				responseResutlVo.setContent( content );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return responseResutlVo;
	}

	public ResponseResutlVo post( String url, Map<String, String> params ) {
		ResponseResutlVo responseResutlVo = new ResponseResutlVo();// 响应结果 vo
		try {
			HttpPost httpPost = new HttpPost( url );
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 3000 ).setConnectTimeout( 3000 ).build();
			httpPost.setConfig( requestConfig );
			// 设置post 的参数
			if ( params != null ) {
				// post 参数
				List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
				for ( Entry<String, String> entry : params.entrySet() ) {
					parameters.add( new BasicNameValuePair( entry.getKey(), entry.getValue() ) );
				}
				HttpEntity httpEntity = new UrlEncodedFormEntity( parameters, "UTF-8" );
				httpPost.setEntity( httpEntity );
			}

			HttpResponse httpResponse = httpClient.execute( httpPost );// 执行请求
			if ( httpResponse.getStatusLine().getStatusCode() == 200 ) {
				StringBuffer headr = new StringBuffer();
				for ( Header head : httpResponse.getAllHeaders() ) {
					headr.append( head + "\n" );
				}
				HttpEntity entity = httpResponse.getEntity();
				String content = EntityUtils.toString( entity );
				responseResutlVo.setCloseableHttpClient( httpClient );
				responseResutlVo.setHttpResponse( httpResponse );
				responseResutlVo.setHeadr( headr.toString() );
				responseResutlVo.setContent( content );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return responseResutlVo;
	}

	public ResponseResutlVo put( String url, Map<String, String> params ) {
		ResponseResutlVo responseResutlVo = new ResponseResutlVo();// 响应结果 vo
		try {
			HttpPut httpPut = new HttpPut( url );
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 3000 ).setConnectTimeout( 3000 ).build();
			httpPut.setConfig( requestConfig );
			// 设置post 的参数
			if ( params != null ) {
				// post 参数
				List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
				for ( Entry<String, String> entry : params.entrySet() ) {
					parameters.add( new BasicNameValuePair( entry.getKey(), entry.getValue() ) );
				}
				HttpEntity httpEntity = new UrlEncodedFormEntity( parameters, "UTF-8" );
				httpPut.setEntity( httpEntity );
			}

			HttpResponse httpResponse = httpClient.execute( httpPut );// 执行请求
			if ( httpResponse.getStatusLine().getStatusCode() == 200 ) {
				StringBuffer headr = new StringBuffer();
				for ( Header head : httpResponse.getAllHeaders() ) {
					headr.append( head + "\n" );
				}
				HttpEntity entity = httpResponse.getEntity();
				String content = EntityUtils.toString( entity );
				responseResutlVo.setCloseableHttpClient( httpClient );
				responseResutlVo.setHttpResponse( httpResponse );
				responseResutlVo.setHeadr( headr.toString() );
				responseResutlVo.setContent( content );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return responseResutlVo;
	}
}
