package com.lhy.commons.template;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
@Component("templateProcessor")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FreeMarkerProcessor {
	private Configuration configuration;
	public Configuration getConfiguration() {
		return configuration;
	}
	@Value("#{freemarkerConfiguration}")
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public String process(String templatePath,Map<String,Object> model){
		String returnValue=null;
		try {
			returnValue= FreeMarkerTemplateUtils.processTemplateIntoString(this.configuration.getTemplate(templatePath), model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(),e);
		}		
		return StringUtils.strip(returnValue);
	}
}
