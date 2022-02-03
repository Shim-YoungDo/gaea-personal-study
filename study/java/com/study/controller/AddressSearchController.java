package com.study.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class AddressSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AddressSearchController.class); 
	
	@RequestMapping(value="/address/search", method=RequestMethod.GET)
	public String getSearcPage() {
		return "/address/search";
	}

	@RequestMapping(value="/address/getAddrApi.do", method=RequestMethod.POST)
	public void getAddrApi(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String currentPage = request.getParameter("currentPage");
		String countPerPage = request.getParameter("countPerPage");
		String resultType = request.getParameter("resultType");
		String confmKey = "devU01TX0FVVEgyMDIyMDEyODA5NDA1OTExMjE5MTU=";
		String keyword = request.getParameter("keyword");
		
		LOG.info("[/address/search] PARAM KEYWORD :"+keyword);
		
		String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage
				+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")
				+"&confmKey="+confmKey+"&resultType="+resultType;
		URL jusoApiUrl = new URL(apiUrl);
		LOG.info("[/address/search] PARAM JUSO_API_URL:"+jusoApiUrl);
		BufferedReader urlRead = new BufferedReader(
							new InputStreamReader(
							jusoApiUrl.openStream(),"UTF-8"));
		StringBuffer addressData = new StringBuffer();
		String getAddressData = null;
		while(true) {
			getAddressData = urlRead.readLine(); // 주소api에서 주소데이터 한행씩 가져옴
			LOG.info("[/address/search] PARAM ADDRESS DATA:"+getAddressData);
			if(getAddressData == null) {
				break;
			}
			addressData.append(getAddressData);
		}
		urlRead.close();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(addressData.toString());
		
	}
}
