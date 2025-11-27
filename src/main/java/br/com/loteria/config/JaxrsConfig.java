package br.com.loteria.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.reflections.Reflections;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/api")
public class JaxrsConfig extends Application {

	public JaxrsConfig() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("API Loteria Lotofácil");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("br.com.loteria.endpoint");
        beanConfig.setDescription("Documentação da API Loteria");
        beanConfig.setScan(true);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> resources = new HashSet<>();
        
        // Recursos do Swagger para gerar o JSON
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        
        Reflections reflections = new Reflections("br.com.loteria.endpoint");
        Set<Class<?>> endpoints = reflections.getTypesAnnotatedWith(Path.class);
        resources.addAll(endpoints);
        
        return resources;
    }
    
    
    
}
