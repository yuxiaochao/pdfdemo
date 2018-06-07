package com.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.shop.utils.BaseController;
import com.shop.utils.FileUtil;
import com.shop.utils.PDFUtil;
import com.shop.utils.Req;
import com.shop.utils.ReturnMsg;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

@Controller
public class FormController extends BaseController {

	@RequestMapping("/")
	public String index() {
		System.out.println("此类被加载！");
		return "form/form_index";
	}

	@RequestMapping("/formtable")
	@ResponseBody
	public ReturnMsg form(Req req) throws IOException, DocumentException {
		ReturnMsg msg = new ReturnMsg(FAIL);
		Map<String, Object> parameterMap = req.getParameterMap();
		Map<String, String> data = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
			data.put(entry.getKey(), entry.getValue().toString());
		}
		String path = PDFUtil.class.getResource("/").toString();
		String fileName = path + "static/表单.pdf"; // pdf模板
		long lasting = System.currentTimeMillis();
		//String filepath = "/usr/share/nginx/html/pdf/ceshi.pdf";
		String filepath = "D:/webfile/pdf/ceshi.pdf";
		String newname = "ceshi1";
		/*try {
			
			//File classPathFile = FileUtil.getClassPathFile("path.xml");
			File classPathFile= new File("path.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(classPathFile);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("VALUE"); i.hasNext();) {
				foo = (Element) i.next();
				filepath=foo.elementText("NO");
				newname=foo.elementText("ADDR");
				System.out.print(foo.elementText("ADDR")+":"+ foo.elementText("NO"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		FileUtil.rename(filepath, newname);
		//PDFUtil.getPDF(data, fileName, "D:/webfile/pdf/ceshi.pdf");
		PDFUtil.getPDF(data,fileName,filepath);
		msg.setStatus(SUCCESS);
		return msg;
	}
}
