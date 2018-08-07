package com.example.demoshiro.common.shiro;

import com.roncoo.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Component
class ShiroTagsConfig {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}  
}