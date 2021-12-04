package edu.fa.security;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import edu.fa.constant.Constant;
import edu.fa.model.Users;
import edu.fa.repository.UserAuthenticateRepository;
import edu.fa.util.SecurityUtils;

@Component
public class UserSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UserAuthenticateRepository userAuthenticateRepository;
	
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if(response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	private String determineTargetUrl(Authentication authentication) {
		String url = "/guest/home";
		try {
			String username = SecurityUtils.getPrincipal().getUsername();
			Users user = userAuthenticateRepository.findOneByUsername(username);
			if(user != null) {
				int status = user.getEnabled();
				
				if(status == Constant.ACCOUNT_ACTIVE_STATUS) {
					List<String> roles = SecurityUtils.getAuthorities();
					if(isAdmin(roles)) {
						url="/admin/admin-Home";
					}else if(isTrainer(roles)) {
						url="/trainer/trainer-Home";
					}else if(isTrainee(roles)) {
						url="/trainee/trainee-Home";
					}
				}else if(status == Constant.ACCOUNT_INACTIVE_STATUS) {
				
					url = "/guest/verifyAccountAgain?email="+user.getUsername();
				}else if(status == Constant.ACCOUNT_DELETE_STATUS) {
					url = "/guest/formLogin1?DELETE_STATUS=2";
				}
			}
		} catch (Exception e) {
			
		}
		return url;
	}
	
	
	private boolean isAdmin(List<String> roles) {
		if(roles.contains(Constant.ACCOUNT_ROLE_ADMIN)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isTrainer(List<String> roles) {
		if(roles.contains(Constant.ACCOUNT_ROLE_TRAINER)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isTrainee(List<String> roles) {
		if(roles.contains(Constant.ACCOUNT_ROLE_TRAINEE)) {
			return true;
		}else {
			return false;
		}
	}
}
