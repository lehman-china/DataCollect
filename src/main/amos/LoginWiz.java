package main.amos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.amos.util.HttpUtil;
import main.amos.vo.ResponseResutlVo;

import org.junit.Test;

/**
 * 登陆到wiz 笔记. 进行 查,改 笔记
 * 雷建军:2014-9-14 17:32:17
 */
public class LoginWiz {
	HttpUtil httpUtil = new HttpUtil();
	ResponseResutlVo loginRRVo = null;
	ResponseResutlVo queryRRVo = null;
	ResponseResutlVo changeRRVo = null;

	public LoginWiz() {
		loginRRVo = login();
	}
	/**
	 * @author 雷建军
	 * @time 2014年9月14日 下午7:29:03 
	 * @Description: 查询笔记
	 * @throws Exception void 
	 * throws
	 */
	@Test
	public void testQuery() throws Exception {

		String document_guid = "a399a4d1-26ef-aee8-3eaf-a6f537282abb";
		query( document_guid );
		String document_body = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_body" );
		System.out.println( document_body );
	}

	@Test
	public void testChange() throws Exception {
		String document_guid = "a399a4d1-26ef-aee8-3eaf-a6f537282abb";
		String content = "var users= {1:{name:'雷建军',age:22}}" + new Date().getTime();
		change( document_guid, content );
		System.out.println( changeRRVo.getContent() );
	}

	public ResponseResutlVo query( String document_guid ) {
		// 1. 登陆,(获得登录用户的 token)
		if ( loginRRVo == null ) {
			loginRRVo = login();
		}
		String token = loginRRVo.getJsonContent().getString( "token" );
		String kb_guid = loginRRVo.getJsonContent().getString( "kb_guid" );
		String queryUrl = "https://note.wiz.cn/api/document/info?client_type=web2.0&api_version=4&token=" + token + "&kb_guid=" + kb_guid + "&document_guid=" + document_guid + "&_=" + new Date().getTime();
		queryRRVo = httpUtil.get( queryUrl, null );
		return queryRRVo;
	}

	public ResponseResutlVo login() {
		String userName = "lehman_china@163.com";
		String password = "a4646120";
		String url = "https://note.wiz.cn/api/login";
		Map<String, String> params = new HashMap<String, String>();
		params.put( "client_type", "web2.0" );
		params.put( "api_version", "4" );
		params.put( "token", "" );
		params.put( "user_id", userName );
		params.put( "password", password );
		ResponseResutlVo rrVo = httpUtil.post( url, params );
		return rrVo;
	}

	public ResponseResutlVo change( String document_guid, String content ) {

		// 1查询 笔记,获得笔记修改的信息
		if ( queryRRVo == null ) {
			queryRRVo = query( document_guid );
		}

		// 2 修改笔记
		String changeUrl = "https://note.wiz.cn/api/document/data";
		Map<String, String> map = new HashMap<String, String>();

		String token = loginRRVo.getJsonContent().getString( "token" );
		map.put( "token", token );

		String kb_guid = loginRRVo.getJsonContent().getString( "kb_guid" );
		map.put( "kb_guid", kb_guid );

		map.put( "document_guid", document_guid );

		map.put( "document_body", content );

		map.put( "client_type", "web2.0" );

		String api_version = loginRRVo.getJsonContent().getJSONObject( "user" ).getString( "api_version" );
		map.put( "api_version", api_version );

		String document_protect = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_protect" );
		map.put( "document_protect", document_protect );

		String data_md5 = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "data_md5" );
		map.put( "data_md5", data_md5 );

		String displayname = loginRRVo.getJsonContent().getJSONObject( "user" ).getString( "displayname" );
		map.put( "document_author", displayname );

		String dt_created = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "dt_created" );
		map.put( "dt_created", dt_created );

		String document_tag_guids = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_tag_guids" );
		map.put( "document_tag_guids", document_tag_guids );

		String document_type = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_type" );
		map.put( "document_type", document_type );

		String version = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "version" );
		map.put( "version", version );

		String document_title = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_title" );
		map.put( "document_title", document_title );

		String document_location = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_location" );
		map.put( "document_location", document_location );

		String document_category = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_category" );
		map.put( "document_category", document_category );

		String style_guid = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "style_guid" );
		map.put( "style_guid", style_guid );

		String document_owner = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_owner" );
		map.put( "document_owner", document_owner );

		String dt_modified = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "dt_modified" );
		map.put( "dt_modified", dt_modified );

		String document_attachment_count = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_attachment_count" );
		map.put( "document_attachment_count", document_attachment_count );

		String dt_data_modified = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "dt_data_modified" );
		map.put( "dt_data_modified", dt_data_modified );

		String document_filetype = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_filetype" );
		map.put( "document_filetype", document_filetype );

		String document_url = queryRRVo.getJsonContent().getJSONObject( "document_info" ).getString( "document_url" );
		map.put( "document_url", document_url );

		changeRRVo = httpUtil.put( changeUrl, map );

		return changeRRVo;
	}

}