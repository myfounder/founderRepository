package com.founder.apiserve.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.founder.apiserve.domain.InterfaceServiceLog;
import com.founder.apiserve.domain.InterfaceServiceLogQC;
import com.founder.apiserve.domain.InterfacesQC;
import com.founder.apiserve.service.InterfaceServiceLogService;
import com.founder.apiserve.service.InterfacesPojoStore;
import com.founder.apiserve.service.InterfacesService;
import com.founder.base.controller.BaseController;
import com.founder.base.page.Page;
import com.founder.base.utils.DateUtil;

/**
 * 
 * @description:接口服务日志控制类
 * @author: YeJianPing
 * @date：2015-3-17
 */
@Controller
public class InterfaceServiceLogController extends BaseController {
	@Autowired
	private InterfaceServiceLogService interfaceServiceLogService;
	@Autowired
	private InterfacesService interfacesService;

	/**
	 * 
	 * @description:接口管理首页,动态条件，分页
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午03:05:53
	 * @throws ParseException
	 */
	@RequestMapping("/interfaceservicelog/findpage")
	public String findpage(HttpServletRequest request, Page page, Model model)
			throws ParseException {
		// List<Interfaces> interList = interfacesService.findInterfacesName();
		// model.addAttribute("interList", interList);

		model.addAttribute("interAllMap", InterfacesPojoStore.getInstance()
				.getIntfAllDataMap());
		Map<String, String[]> parameterMap = request.getParameterMap();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			hashMap.put(entry.getKey(), entry.getValue()[0]);
		}
		page.setParams(hashMap);
		List<InterfaceServiceLog> list = interfaceServiceLogService.findPage(
				page, hashMap);
		for (InterfaceServiceLog pojo : list) {
			pojo.setName(InterfacesPojoStore.getInstance()
					.getIntfaceNameByCode(pojo.getName()));
		}
		page.setResults(list);
		model.addAttribute("page", page);

		InterfacesQC interfacesQC = new InterfacesQC();
		InterfaceServiceLogQC logQC = new InterfaceServiceLogQC();
		logQC.setName((String) hashMap.get("name"));
		logQC.setStartTime(DateUtil.stringToDate((String) hashMap
				.get("startTime")));
		logQC.setEndTime(DateUtil.stringToDate((String) hashMap.get("endTime")));
		model.addAttribute("logQC", logQC);
		return "apiserve/fuwurizhi";
	}
}
