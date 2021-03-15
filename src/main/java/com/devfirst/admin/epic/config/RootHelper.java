package com.devfirst.admin.epic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@RequiredArgsConstructor
@Component
@HandlebarsHelper
public class RootHelper {
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	public String contextPath() {
		return this.contextPath;
	}

}
