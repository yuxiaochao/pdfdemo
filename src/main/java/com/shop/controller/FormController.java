package com.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.shop.utils.BaseController;
import com.shop.utils.PDFUtil;
import com.shop.utils.Req;
import com.shop.utils.ReturnMsg;

@Controller
@RequestMapping("/form")
public class FormController extends BaseController{

	@RequestMapping("/index")
	public String index() {
		System.out.println("此类被加载！");
		return "form/form_index";
	}
	
	@RequestMapping("/formtable")
	@ResponseBody
	public ReturnMsg form(Req req) throws IOException, DocumentException {
		ReturnMsg msg = new ReturnMsg(FAIL);
		Map<String, Object> parameterMap = req.getParameterMap();
		Map<String,String> data=new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
			data.put(entry.getKey(), entry.getValue().toString());
		}
		PDFUtil.getPDF(data);
		msg.setStatus(SUCCESS);
		return msg;
	}
}
