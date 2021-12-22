package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.request.UserAuthenticationRequestDto;
import nl.serkanertas.filmspringserver.dto.response.UserAuthenticationResponseDto;
import nl.serkanertas.filmspringserver.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtl;

    public UserAuthenticateService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtl) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtl = jwtUtl;
    }

    public UserAuthenticationResponseDto authenticateUser(UserAuthenticationRequestDto authenticationRequestDto) {

        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String jwt = jwtUtl.generateToken(userDetails);

        return new UserAuthenticationResponseDto(jwt);
    }

}
