package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.UserAuthenticationRequestDto;
import nl.serkanertas.filmspringserver.dto.response.UserAuthenticationResponseDto;
import nl.serkanertas.filmspringserver.service.UserAuthenticateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    private final UserAuthenticateService userAuthenticateService;

    public AuthController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginAndSendToken(@RequestBody UserAuthenticationRequestDto
                                                                userAuthenticationRequestDto) {
        UserAuthenticationResponseDto userAuthenticationResponseDto =
        userAuthenticateService.authenticateUser(userAuthenticationRequestDto);

        return ResponseEntity.ok().body(userAuthenticationResponseDto);
    }
}
