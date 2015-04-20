package com.founder.apiserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.apiserve.domain.InterfaceGrant;
import com.founder.apiserve.domain.InterfaceGrantQC;
import com.founder.apiserve.service.InterfaceGrantService;
import com.founder.apiserve.service.InterfacesPojoStore;
import com.founder.apiserve.service.InterfacesService;
import com.founder.base.annotation.Token;
import com.founder.base.controller.BaseController;
import com.founder.base.page.Page;
import com.founder.base.utils.Constants;
import com.founder.base.utils.ResultObj;

/**
 * 
 * @description:接口授权管理控制类
 * @author: YeJianping
 * @date：2015-2-12
 */
@Controller
public class InterfaceGrantController extends BaseController {
	@Autowired
	private InterfaceGrantService interfaceGrantService;
	@Autowired
	private InterfacesService interfacesService;

	/**
	 * 
	 * @description:授权管理首页
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 下午01:39:07
	 */
	@RequestMapping("/interfacegrant/findpage")
	public String findpage(HttpServletRequest request,
			Page<InterfaceGrant> page, Model model) {
		// List<Interfaces> interList = interfacesService.findInterfacesName();
		// model.addAttribute("interList", interList);
		model.addAttribute("interOpenMap", InterfacesPojoStore.getInstance()
				.getIntfOpenDataMap());
		Map<String, String[]> parameterMap = request.getParameterMap();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			hashMap.put(entry.getKey(), entry.getValue()[0]);
		}
		page.setParams(hashMap);
		List<InterfaceGrant> list = interfaceGrantService.findPage(page,
				hashMap);
		for (InterfaceGrant pojo : list) {
			pojo.setName(InterfacesPojoStore.getInstance()
					.getIntfaceNameByCode(pojo.getName()));
		}
		page.setResults(list);
		model.addAttribute("page", page);
		InterfaceGrantQC grantQC = new InterfaceGrantQC();
		grantQC.setName((String) hashMap.get("name"));
		grantQC.setSystemName((String) hashMap.get("systemName"));
		model.addAttribute("grantQC", grantQC);
		return "apiserve/shouquan";
	}

	/**
	 * 
	 * @description:toAdd
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 下午01:37:00
	 */
	@Token(save = true)
	@RequestMapping("/interfacegrant/toAdd")
	public String toAdd(Model model, HttpServletRequest request) {
		saveToken(request);
		// List<Interfaces> interoList = interfacesService
		// .findInterfacesNameOpen();
		// model.addAttribute("interoList", interoList);
		model.addAttribute("interOpenMap", InterfacesPojoStore.getInstance()
				.getIntfOpenDataMap());

		return "/apiserve/shouquanAdd";
	}

	/**
	 * 
	 * @description:save
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 下午01:37:27
	 */
	@Token(save = true)
	@RequestMapping("/interfacegrant/save")
	@ResponseBody
	public ResultObj<Map<String, Object>> save(InterfaceGrant interfaceGrant,
			HttpServletRequest request) {
		ResultObj<Map<String, Object>> resultObj = new ResultObj<Map<String, Object>>();
		if (!super.isTokenValid(request)) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("token", Constants.MESSAGE_REPETITIVE_OPERATION);
			resultObj.setSuccess(false);
			resultObj.setData(hashMap);
			return resultObj;
		}
		try {
			interfaceGrantService.insert(interfaceGrant);
			resultObj.setSuccess(true);
		} catch (Exception e) {
			resultObj.setSuccess(false);
		}
		return resultObj;
	}

	/**
	 * 
	 * @description:toupdate
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 下午01:38:17
	 */
	@RequestMapping("/interfacegrant/toupdate")
	public String toupdate(Integer id, Model model, HttpServletRequest request) {
		saveToken(request);
		InterfaceGrant interfaceGrant = interfaceGrantService.get(id);
		model.addAttribute("interfaceGrant", interfaceGrant);
		// List<Interfaces> interList = interfacesService.findInterfacesName();
		// model.addAttribute("interList", interList);
		model.addAttribute("interOpenMap", InterfacesPojoStore.getInstance()
				.getIntfOpenDataMap());
		List<InterfaceGrant> ugrantsStatus = interfaceGrantService
				.findGrantStatus();
		model.addAttribute("ugrantsStatus", ugrantsStatus);
		return "/apiserve/shouquanUpdate";
	}

	/**
	 * 
	 * @description:update
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 下午01:38:31
	 */
	@RequestMapping("/interfacegrant/update")
	@ResponseBody
	public ResultObj<Map<String, Object>> update(InterfaceGrant interfaceGrant,
			HttpServletRequest request) {
		ResultObj<Map<String, Object>> resultObj = new ResultObj<Map<String, Object>>();
		if (!super.isTokenValid(request)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("token", Constants.MESSAGE_REPETITIVE_OPERATION);
			resultObj.setData(map);
			resultObj.setSuccess(false);
			return resultObj;
		}
		try {
			interfaceGrantService.update(interfaceGrant);
			resultObj.setSuccess(true);
		} catch (Exception e) {
			resultObj.setSuccess(false);
		}
		return resultObj;
	}
}
