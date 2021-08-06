package dropwizard.security;

import java.util.Optional;

import dropwizard.model.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class SecurityAuthenticator implements Authenticator<BasicCredentials, User> {

	@Override
	public Optional<User> authenticate(BasicCredentials token) throws AuthenticationException {
	
		if("secret".equals(token.getPassword())) {
			return Optional.of(new User(token.getUsername()));
		}
		
		return Optional.empty();
	}

}
