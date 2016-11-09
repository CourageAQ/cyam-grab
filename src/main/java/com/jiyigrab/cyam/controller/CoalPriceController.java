package com.jiyigrab.cyam.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jiyigrab.cyam.model.CoalPrice;
import com.jiyigrab.cyam.service.CoalPriceService;


public class CoalPriceController {
	public  void getHtml(String datetime) {
		Date console = new Date();
		CoalPriceService coalPriceService = new CoalPriceService();
		//1.创建HttpClient对象
		HttpClientBuilder  httpClientBuilder = HttpClientBuilder.create();
		//2.创建请求方法的实例，并制定请求URL。如果需要发送GRT请求，创建HttpGet对象;如果需要发送POST请求，创建HttpPost对象。
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet("http://www.cqcoal.com/Trade/Price/2015/price_qhd_page.jsp?action="+datetime);
		//定义集合返回需要的文本数据
		//List<TableBean> tableBeans = new ArrayList<TableBean>();
		HttpResponse httpResponse;
		try {
			//3执行get请求
			httpResponse = closeableHttpClient.execute(httpGet);
			//4获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				//以UTF-8的格式返回Html文本信息
				String response = EntityUtils.toString(entity,"UTF-8");
				//解析页面内容
				Document document = Jsoup.parse(response);
				Elements elementhead =document.select("head").select("title");
				if(!elementhead.text().equals("用户登录")){
					String coalDate = coalPriceService.selectCoal(datetime).trim();
					if (!coalDate.equals(datetime) || coalDate.equals("")) {
					//获取table元素，从加载的信息中查找table标签
					Elements element =document.select("table");
					//获取table标签中的tr标签
					Elements tr = element.select("tr");
					for (int i = 1; i < tr.size(); i++) {
						String[] tds =new String[7];
						//获取单行
						Element trs = tr.get(i);
						//获取单行中的列
						Elements td = trs.select("td");
						//解析每一行中所需的数据
						for (int m = 0; m<td.size() ; m++) {
							Element tdpojo = td.get(m);
							tds[m]=tdpojo.text();
						}
						CoalPrice coalPrice = new CoalPrice();
						coalPrice.setHeat(Integer.valueOf(tds[0]));
						coalPrice.setNowpeace(tds[1]);
						coalPrice.setLastpeace(tds[2]);
						coalPrice.setDegree(tds[3]);
						coalPrice.setHuanbi(tds[4]);
						coalPrice.setLasttime(tds[5]);
						coalPrice.setTongbi(tds[6]);
						coalPriceService.saveCoal(coalPrice);
						}
					}else{
						System.out.println(console + "当前日期已获得数据！");
					}
				}else {
						System.out.println(console + "当前日期没有发布数据！");
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("网络好像出了问题呀！请检查网络连接！");
		}finally {
			try {
				//5关闭连接
				closeableHttpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
