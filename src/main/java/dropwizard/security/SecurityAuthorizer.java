package dropwizard.security;

import java.util.Objects;

import dropwizard.model.User;
import io.dropwizard.auth.Authorizer;

public class SecurityAuthorizer implements Authorizer<User> {

	@Override
	public boolean authorize(User principal, String role) {
		
		if(Objects.nonNull(principal)) {
			return principal.getName().equals("anuj") && role.equals("ADMIN");
		}
		return false;
	}

}
