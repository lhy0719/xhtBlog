package com.lhy.web.system;


import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.lhy.commons.rsautil.RSAUtil;
import com.lhy.modules.system.domain.CommonUser;
import com.lhy.modules.system.service.ICommonUserManager;
import com.lhy.web.WebConstants;


@Controller("commonUserController")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping(value = "/commonUser")
@SessionAttributes({WebConstants.LOGIN_USER_KEY})
public class CommonUserController {
	private static final String viewPath="user/data/commonUserManagerData";
	private static final String templateKey="templateKey";
	@Value("#{commonUserManager}")
	private ICommonUserManager commonUserManager;
	public enum LogInType{userIsNotExist,loginSuccess,pswIsWrong,userIsLocked}
	@RequestMapping(value = "/validateKaptchaKey", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView validateKaptchaKey(@RequestParam(value="kaptchaKey",required=true)String kaptchaKey,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String kaptchaSessionKey=(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptchaSessionKey!=null&&kaptchaSessionKey.equals(kaptchaKey)){
			mv.addObject("kaptchaResult","true");
		}else{
			mv.addObject("kaptchaResult","false");
		}
		mv.addObject(templateKey,"validateKaptchaKey");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/ceshi" ,method ={ RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody CommonUser ceshi(HttpServletRequest request){

		return (CommonUser)request.getSession().getAttribute(WebConstants.LOGIN_USER_KEY);
	}
	@RequestMapping(value = "/generateKeyPair", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView generateKeyPair(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		KeyPair keyPair = RSAUtil.generateKeyPair();
		RSAPublicKey rsap = (RSAPublicKey) keyPair.getPublic();
		String module = rsap.getModulus().toString(16);
		String empoent = rsap.getPublicExponent().toString(16);
		request.getSession().setAttribute("privateRsaKey", keyPair.getPrivate());
		mv.addObject(templateKey,"generateKeyPair");
		mv.addObject("rsamodule", module);
		mv.addObject("rsaempoent",empoent);
		mv.setViewName(viewPath);
		return mv;
	}

	@RequestMapping(value = "/test", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String test(@RequestParam(value="paras",required=true)String paras) {
		System.out.println(paras);
		Map obj = JSON.parseObject(paras, HashMap.class);
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("skuId",obj.get("mdi"));
		jsonObject.put("recommendPrice",2222);
		return "{'msg':'hello world'}";
	}

	@RequestMapping(value = "/goToPortalPage", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView goToPortalPage(@RequestParam(value="templateName",required=true)String templateName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(templateName);
		return mv;
	}
	@RequestMapping(value = "/goToExcludePortalPage", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView goToExcludePortalPage(@RequestParam(value="templateName",required=true)String templateName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(templateName);
		return mv;
	}
	
	@RequestMapping(value = "/goToFlowHistoryManager", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView goToFlowHistoryManager(@RequestParam(value="cityInfoOID", required=true) String cityInfoOID) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cityInfoOID",cityInfoOID);
		mv.setViewName("public/cityInfoHistory");
		return mv;
	}
	
	@RequestMapping(value = "/goToFlowHistoryManagerNoLogin", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView goToFlowHistoryManagerNoLogin(@RequestParam(value="cityInfoOID", required=true) String cityInfoOID) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cityInfoOID",cityInfoOID);
		mv.setViewName("public/cityInfoHistoryNoLogin");
		return mv;
	}
	
	
	@RequestMapping(value = "/registerCommonUser", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView registerCommonUser(CommonUser commonUser,Model model,SessionStatus session) {
		ModelAndView mv = new ModelAndView();
		String registerResult = this.commonUserManager.registerCommonUser(commonUser);
		if("registerSuccess".equals(registerResult)){
			CommonUser cUser = this.commonUserManager.getCommonUserByAccount(commonUser.getCommonUserAccount());
			model.addAttribute(WebConstants.LOGIN_USER_KEY,cUser);
		}else{
			session.setComplete();
		}
		mv.addObject(templateKey,"registerCommonUser");
		mv.addObject("registerResult",registerResult);
		mv.setViewName(viewPath);
		return mv;
	}
	
	@RequestMapping(value = "/validateUserAccount", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView validateUserAccount(@RequestParam(value="commonUserAccount",required=true)String commonUserAccount) {
		ModelAndView mv = new ModelAndView();
		CommonUser cUser = this.commonUserManager.getCommonUserByAccount(commonUserAccount);

		if(cUser==null){
			mv.addObject("validateResult","true");
		}else{
			mv.addObject("validateResult","false");
		}
		mv.addObject(templateKey,"validateUserAccount");
		mv.setViewName(viewPath);
		return mv;
	}
	
	@RequestMapping(value = "/commonUserLogin", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView commonUserLogin(CommonUser commonUser, Model model,SessionStatus session,HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		CommonUser cUser =new CommonUser();
		PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("privateRsaKey");
		String psw = decryptRSA(commonUser.getCommonUserPassword(),privateKey);
		LogInType loginResult = LogInType.valueOf(this.commonUserManager.commonUserLogin(commonUser.getCommonUserAccount(),psw));
		switch (loginResult){
			case userIsNotExist:
				session.setComplete();
				break;
			case pswIsWrong:
				session.setComplete();
				break;
			case userIsLocked:
				session.setComplete();
				break;
			case loginSuccess:
				cUser = this.commonUserManager.getCommonUserByAccount(commonUser.getCommonUserAccount());
				model.addAttribute(WebConstants.LOGIN_USER_KEY,cUser);
				break;
		}
		mv.addObject("loginResult", loginResult);
		mv.addObject(templateKey,"commonUserLogin");
	    mv.setViewName(viewPath);
		return mv;
	}
	
	@RequestMapping(value={"/commonUserLogout"}, method = { RequestMethod.GET,RequestMethod.POST })
	  public ModelAndView commonUserLogout(SessionStatus session) {
	    session.setComplete();
	    ModelAndView mv = new ModelAndView();
	    mv.addObject(templateKey,"commonUserLogout");
	    mv.setViewName(viewPath);
	    return mv;
	  }
	
	@RequestMapping(value={"/updateCommonUser"}, method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView updateCommonUser(CommonUser commonUser) {
		ModelAndView mv = new ModelAndView();
		this.commonUserManager.updateCommonUser(commonUser);
		mv.addObject(templateKey,"updateCommonUser");
		mv.setViewName(viewPath);
		return mv;
	}
	
	@RequestMapping(value={"/resetPsw"}, method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView resetPsw(CommonUser commonUser,SessionStatus session) {
		ModelAndView mv = new ModelAndView();
		this.commonUserManager.resetPsw(commonUser);
		 session.setComplete();
		mv.addObject(templateKey,"resetPsw");
		mv.setViewName(viewPath);
		return mv;
	}
	String decryptRSA(String psw,PrivateKey privateKey) throws Exception{
//		System.out.println("原文加密后为：");
//		System.out.println(psw);
		byte[] en_result = new BigInteger(psw, 16).toByteArray();
		byte[] de_result = RSAUtil.decrypt(privateKey,
				en_result);
//		System.out.println("还原密文：");
//		System.out.println(new String(de_result));
		StringBuffer sb = new StringBuffer();
		sb.append(new String(de_result));
		return URLDecoder.decode(sb.reverse().toString(),"UTF-8");
	}
}
