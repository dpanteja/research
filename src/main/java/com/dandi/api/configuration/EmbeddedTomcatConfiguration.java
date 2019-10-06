package com.dandi.api.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.mockito.internal.util.collections.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.util.StringUtils;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.embedded.tomcat.*;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.context.embedded.tomcat.*;
//.TomcatEmbeddedServletContainerFactory;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class EmbeddedTomcatConfiguration {

	@Value("${server.port}")

	  private String serverPort;


	  @Value("${management.port:${server.port}}")

	  private String managementPort;


	  @Value("${server.additionalPorts:null}")

	  private String additionalPorts;


	  @Bean
	  public TomcatServletWebServerFactory servletContainer() {
		  DispatcherServlet d = new DispatcherServlet();
		  
	      TomcatServletWebServerFactory tomcat =
	              new TomcatServletWebServerFactory() {

	                  @Override
	                  protected void postProcessContext(Context context) {
	                  }
	              };
	      tomcat.addAdditionalTomcatConnectors(additionalConnector());
	      return tomcat;
	  }
//	  @Bean
//	  
//	  
//	 
//
//	  public EmbeddedServletContainerFactory servletContainer() {
//
//	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//
//	    Connector[] additionalConnectors = this.additionalConnector();
//
//	    if (additionalConnectors != null && additionalConnectors.length > 0) {
//
//	      tomcat.addAdditionalTomcatConnectors(additionalConnectors);
//
//	    }
//
//	   return tomcat;
//
//	  }


	  private Connector[] additionalConnector() {

	    if (StringUtils.isBlank(this.additionalPorts)) {

	      return null;

	    }

	    Set<Object> defaultPorts = Sets.newSet(this.serverPort, this.managementPort);

	    String[] ports = this.additionalPorts.split(",");

	    List<Connector> result = new ArrayList<>();

	    for (String port : ports) {

	      if (!defaultPorts.contains(port)) {

	        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

	        connector.setScheme("http");

	        connector.setPort(Integer.valueOf(port));

	        result.add(connector);

	      }

	    }

	    return result.toArray(new Connector[]{});
	    
	  }

}
	
