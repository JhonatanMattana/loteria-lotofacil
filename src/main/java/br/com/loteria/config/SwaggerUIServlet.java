package br.com.loteria.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/swagger")
public class SwaggerUIServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Redireciona para o Swagger UI com a URL do swagger.json
        String swaggerUrl = req.getContextPath() + 
            "/webjars/swagger-ui/3.52.5/index.html?url=" + 
            req.getContextPath() + "/api/swagger.json";
        
        resp.sendRedirect(swaggerUrl);
    }
}
