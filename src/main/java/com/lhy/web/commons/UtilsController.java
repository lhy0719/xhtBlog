package com.lhy.web.commons;

import java.util.Iterator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
@Controller("utilsController")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@RequestMapping(value = "/")
public class UtilsController {
	@RequestMapping(value="/goto/{templateName}",method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getTemplate(WebRequest request,@PathVariable("templateName") String templateName){			
		ModelAndView mv=new ModelAndView();
		Iterator<String> paramKeyIte=request.getParameterNames();
		while (paramKeyIte.hasNext()){
			String paramKey=paramKeyIte.next();
			mv.addObject(paramKey, request.getParameter(paramKey));
		}	
		mv.addObject("templateName",templateName);
		mv.setViewName("user/"+templateName);
		return mv;
	}
//	@RequestMapping(value = "/goto", method = { RequestMethod.GET,RequestMethod.POST })
//	public ModelAndView getTemplate(WebRequest request,@RequestParam(value="templateName",required=true)String templateName) {
//		ModelAndView mv=new ModelAndView();
//		Iterator<String> paramKeyIte=request.getParameterNames();
//		while (paramKeyIte.hasNext()){
//			String paramKey=paramKeyIte.next();
//			mv.addObject(paramKey, request.getParameter(paramKey));
//		}
//		mv.addObject("templateName",templateName);
//		mv.setViewName(templateName);
//		return mv;
//	}
}
