package br.com.loteria.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.loteria.endpoint.TesteEndPoint;
import br.com.loteria.endpoint.impl.BaixarResultadoEndPoint;
import br.com.loteria.endpoint.impl.DezenaSorteioLotofacilEndPoint;
import br.com.loteria.endpoint.impl.SorteioLotofacilEndPointImpl;
import br.com.loteria.security.endpoint.impl.AuthEndPointImpl;
import br.com.loteria.security.endpoint.impl.UsuarioEndPointImpl;
import br.com.loteria.security.service.AuthFilter;
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
        beanConfig.setResourcePackage("br.com.loteria.endpoint,br.com.loteria.security.endpoint");
        beanConfig.setDescription("Documentação da API Loteria");
        beanConfig.setScan(true);

        //Configuração de segurança do Swagger
        io.swagger.models.Swagger swagger = beanConfig.getSwagger();
        io.swagger.models.auth.ApiKeyAuthDefinition apiKeyAuth = new io.swagger.models.auth.ApiKeyAuthDefinition();
        apiKeyAuth.setName("Authorization");
        apiKeyAuth.setIn(io.swagger.models.auth.In.HEADER);
        swagger.securityDefinition("Bearer", apiKeyAuth);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> resources = new HashSet<>();

        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        resources.add(JacksonConfig.class);
        resources.add(LoteriaExceptionMapper.class);
        resources.add(AuthFilter.class);
        resources.add(TesteEndPoint.class);
        resources.add(BaixarResultadoEndPoint.class);
        resources.add(DezenaSorteioLotofacilEndPoint.class);
        resources.add(SorteioLotofacilEndPointImpl.class);
        resources.add(AuthEndPointImpl.class);
        resources.add(UsuarioEndPointImpl.class);

        return resources;
    }
    
    
    
}
