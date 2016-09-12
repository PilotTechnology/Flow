package com.flow.api.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具包
 */
public class HttpClientUtil {
	private static PoolingHttpClientConnectionManager manager;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";

	private static void init() {
		if (manager == null) {
			manager = new PoolingHttpClientConnectionManager();
			manager.setMaxTotal(200);// 整个连接池最大连接数
			manager.setDefaultMaxPerRoute(manager.getMaxTotal());// 每路由最大连接数，默认值是2,目前为单路由，设置最大
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(manager).build();
	}

	/**
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	/**
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, String> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		return getResult(httpGet);
	}

	/**
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url,
			Map<String, String> headers, Map<String, String> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		for (Map.Entry<String, String> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), param.getValue());
		}
		return getResult(httpGet);
	}

	/**
	 * @param url
	 * @return
	 */
	public static String httpPostRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	/**
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, String> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		return getResult(httpPost);
	}

	/**
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url,
			Map<String, String> headers, Map<String, String> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, String> param : headers.entrySet()) {
			httpPost.addHeader(param.getKey(), param.getValue());
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

		return getResult(httpPost);
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, String> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}

		return pairs;
	}

	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				response.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return EMPTY_STR;
	}

	/**
	 * 测试并发1000请求，5秒处理完成
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		long start = System.currentTimeMillis();
		int pagecount = 300;
		for (int i = 0; i < pagecount; i++) {
			// 启动线程抓取
			String ab = httpPostRequest("http://localhost:8080/portal/test!list.action");
			System.out.println(i + "aaaa >>>>>>>>>>>>>>" + ab);
		}

		long end = System.currentTimeMillis();
		System.out.println("consume -> " + (end - start) / 1000 + " 秒");
	}

}
