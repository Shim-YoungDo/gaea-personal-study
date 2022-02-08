package com.study.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 도로주소 api를 사용해 주소정보를 가져오는 컨트롤러
 * @author ydshim
 *
 */
@Controller
@RequestMapping("/")
public class AddressSearchController {

	private static final Logger LOG = LoggerFactory.getLogger(AddressSearchController.class);

	@RequestMapping(value = "/address/search", method = RequestMethod.GET)
	public String getSearcPage() {
		return "/address/search";
	}

	@RequestMapping(value = "/address/juso", method = RequestMethod.GET)
	public String getJusoPage() {
		return "/address/juso";
	}

	/**
	 * 도로주소 api에 접근해 client에서 입력한 주소정보를 가져오는 메소드
	 * @param request client에서 입력한 요청정보를 가져옴
	 * @param response 도로주소 api에서 가져온 주소정보를 client에 응답
	 */
	@RequestMapping(value = "/address.do", method = RequestMethod.POST)
	public void getAddrApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String currentPage = request.getParameter("currentPage");
		String countPerPage = request.getParameter("countPerPage");
		String resultType = request.getParameter("resultType");
		String confmKey = "devU01TX0FVVEgyMDIyMDEyODA5NDA1OTExMjE5MTU="; //승인키
		String keyword = request.getParameter("keyword");

		LOG.info("[/address/search] KEYWORD :" + keyword);

		String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=" + currentPage + "&countPerPage="
				+ countPerPage + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&confmKey=" + confmKey
				+ "&resultType=" + resultType;
		URL jusoApiUrl = new URL(apiUrl);
		LOG.info("[/address/search] JUSO_API_URL:" + jusoApiUrl);
		
		HttpURLConnection connection = (HttpURLConnection) jusoApiUrl.openConnection();
		connection.setRequestMethod("POST");
		connection.setReadTimeout(1000);
		connection.connect();
		
//		BufferedReader dataRead = new BufferedReader(new InputStreamReader(jusoApiUrl.openStream(), "UTF-8"));
		BufferedReader dataRead = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		StringBuffer addressData = new StringBuffer();
		String getAddressData = null;
		
		while (true) {
			
			getAddressData = dataRead.readLine(); // 주소 api에서 주소데이터 한행씩 가져옴
			LOG.info("[/address/search] ADDRESS DATA:" + getAddressData);
			if (getAddressData == null) {
				break;
			}
			
			addressData.append(getAddressData);
		}
		
		dataRead.close();
		connection.disconnect();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(addressData.toString());

	}
}
