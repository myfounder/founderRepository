package com.founder.apiserve.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.apiserve.domain.Interfaces;
import com.founder.apiserve.domain.InterfacesQC;
import com.founder.apiserve.service.InterfacesPojoStore;
import com.founder.apiserve.service.InterfacesService;
import com.founder.base.annotation.Token;
import com.founder.base.controller.BaseController;
import com.founder.base.page.Page;
import com.founder.base.utils.Constants;
import com.founder.base.utils.ResultObj;

/**
 * 
 * @description:接口管理控制类
 * @author: YeJianPing
 * @date：2015-3-10
 */
@Controller
public class InterfacesController extends BaseController {
	@Autowired
	private InterfacesService interfacesService;

	/**
	 * 
	 * @description:接口管理首页,动态条件
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午03:05:53
	 */
	@RequestMapping("/interfaces/findpage")
	public String findpage(HttpServletRequest request, Page page, Model model) {
		// List<Interfaces> interList = interfacesService.findInterfacesName();
		// model.addAttribute("interList", interList);
		// model.addAttribute("interAllMap", InterfacesPojoStore.getInstance()
		// .getIntfAllDataMap());
		Map<String, String> speciesMap = InterfacesPojoStore.getInstance()
				.getWordbookMap(Constants.WORDBOOK_TYPE_SERVICE_TYPE);
		model.addAttribute("speciesMap", speciesMap);

		Map<String, String[]> parameterMap = request.getParameterMap();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			hashMap.put(entry.getKey(), entry.getValue()[0]);
		}
		page.setParams(hashMap);
		List<Interfaces> list = interfacesService.findPage(page, hashMap);
		page.setResults(list);
		model.addAttribute("page", page);
		InterfacesQC interfacesQC = new InterfacesQC();
		interfacesQC.setCode((String) hashMap.get("code"));// 接口编码，模糊查询
		interfacesQC.setSpecies((String) hashMap.get("species"));// 接口种类
		model.addAttribute("interfacesQC", interfacesQC);
		return "apiserve/jianjie";
	}

	/**
	 * 
	 * @description:toAdd
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-16 下午04:02:13
	 */
	@Token(save = true)
	@RequestMapping("/interfaces/toAdd")
	public String toAdd(Model model, HttpServletRequest request) {
		saveToken(request);
		Map<String, String> speciesMap = InterfacesPojoStore.getInstance()
				.getWordbookMap(Constants.WORDBOOK_TYPE_SERVICE_TYPE);
		model.addAttribute("speciesMap", speciesMap);
		return "/apiserve/jianjieAdd";
	}

	/**
	 * 
	 * @description:save
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午03:04:08
	 */
	@RequestMapping("/interfaces/save")
	@ResponseBody
	public ResultObj<Map<String, Object>> save(Interfaces interfaces,
			HttpServletRequest request) {
		ResultObj<Map<String, Object>> resultObj = new ResultObj<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!isTokenValid(request)) {
			map.put("token", Constants.MESSAGE_REPETITIVE_OPERATION);
			resultObj.setData(map);
			resultObj.setSuccess(false);
			return resultObj;
		}
		try {
			interfacesService.insert(interfaces);
			InterfacesPojoStore.getInstance().init();
			resultObj.setSuccess(true);
		} catch (Exception e) {
			resultObj.setSuccess(false);
		}
		return resultObj;
	}

	/**
	 * 
	 * @description:details
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-11 下午02:03:37
	 */
	@RequestMapping("/interfaces/details")
	public String details(Integer id, Model model) {
		Interfaces interfaces = interfacesService.findById(id);
		model.addAttribute("interfaces", interfaces);
		return "apiserve/jianjiedetail";
	}

	/**
	 * 
	 * @description:toupdate
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-11 下午03:18:17
	 */
	@RequestMapping("/interfaces/toupdate")
	public String toupdate(Integer id, Model model, HttpServletRequest request) {
		saveToken(request);
		Interfaces interfaces = interfacesService.findById(id);
		model.addAttribute("interfaces", interfaces);
		Map<String, String> speciesMap = InterfacesPojoStore.getInstance()
				.getWordbookMap(Constants.WORDBOOK_TYPE_SERVICE_TYPE);
		model.addAttribute("speciesMap", speciesMap);
		return "/apiserve/jianjieUpdate";
	}

	/**
	 * 
	 * @description:update
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 上午10:57:39
	 */
	@RequestMapping("/interfaces/update")
	@ResponseBody
	public ResultObj<Map<String, Object>> update(Interfaces interfaces,
			HttpServletRequest request) {
		ResultObj<Map<String, Object>> resultObj = new ResultObj<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!isTokenValid(request)) {
			map.put("token", Constants.MESSAGE_REPETITIVE_OPERATION);
			resultObj.setData(map);
			resultObj.setSuccess(false);
			return resultObj;
		}
		try {
			interfacesService.update(interfaces);
			InterfacesPojoStore.getInstance().init();
			resultObj.setSuccess(true);
		} catch (Exception e) {
			resultObj.setSuccess(false);
		}
		return resultObj;
	}

	/**
	 * 检查接口编码是否存在
	 * 
	 * @param checkCode
	 * @throws IOException
	 */
	@RequestMapping("/interfaces/checkCode")
	public void checkCode(String checkCode, HttpServletResponse response)
			throws IOException {
		Interfaces inter = interfacesService
				.findInterfacesNameByCode(checkCode);
		if (inter != null) {
			response.getWriter().write("yes");
		} else {
			response.getWriter().write("no");
		}
	}
}
