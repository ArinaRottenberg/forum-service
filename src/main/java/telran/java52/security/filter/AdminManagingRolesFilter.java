package telran.java52.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java52.security.model.User;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminManagingRolesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
			Principal principal = request.getUserPrincipal();
            if (principal instanceof User) {
				Set<String> roles = ((User) principal).getRoles();
				if (!roles.contains("ADMINISTRATOR")) {
					response.sendError(403, "You are not allowed to access this resource");
					return;
				}
			} else {
				response.sendError(401, "Unauthorized");
				return;
			}
        }
		
		chain.doFilter(request, response);
	}
	
	private boolean checkEndpoint(String method, String path) {
      return path.matches("/account/user/\\w+/role/\\w+");
  }

}
