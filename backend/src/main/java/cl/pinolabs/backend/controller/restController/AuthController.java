package cl.pinolabs.backend.controller.restController;


import cl.pinolabs.backend.controller.security.jwt.JwtUtils;
import cl.pinolabs.backend.controller.security.jwt.exceptions.TokenRefreshException;
import cl.pinolabs.backend.controller.security.servicios.RefreshTokenService;
import cl.pinolabs.backend.controller.security.servicios.UserDetailsImpl;
import cl.pinolabs.backend.dto.entity.request.LoginRequest;
import cl.pinolabs.backend.dto.entity.request.RegistroRequest;
import cl.pinolabs.backend.dto.entity.response.MessageResponse;
import cl.pinolabs.backend.dto.entity.response.UserInfoResponse;
import cl.pinolabs.backend.modelo.entity.ERole;
import cl.pinolabs.backend.modelo.entity.RefreshToken;
import cl.pinolabs.backend.modelo.entity.Role;
import cl.pinolabs.backend.modelo.entity.Usuario;
import cl.pinolabs.backend.controller.security.repository.RoleRepository;
import cl.pinolabs.backend.controller.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.print("el usuario "+loginRequest.getUsername()+" esta intentando ingresar");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }


    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistroRequest registroRequest) {
        //consultamos si el nombre a sido utilizado
        if (userRepository.existsByUsuario(registroRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Este nombre de usuario ya tiene una cuenta!"));
        }
        //consultamos si el email existe
        if (userRepository.existsByCorreo(registroRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Este correo ya tiene una cuenta!"));
        }

        // Creando un nuevo usuario
        Usuario user = new Usuario(registroRequest.getUsername(),
                registroRequest.getEmail(),
                encoder.encode(registroRequest.getPassword()));

        Set<String> strRoles = registroRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        System.out.print("se a registrado el usuario"+user.getUsuario());
        return ResponseEntity.ok(new MessageResponse("Usuario registrado con exito!"));
    }
    @PostMapping("/salir")
    public ResponseEntity<?> logoutUser() {
        System.out.print("Un usuario ha salido del sistema");
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle.toString() != "anonymousUser") {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("Has salido con exito!"));
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);
        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUsuario)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .header(HttpHeaders.SET_COOKIE, refreshToken)
                                .body(new MessageResponse("El Token a sido refrescado con exito!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "El refresh no se puede aplicar a este token!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token esta vacio!"));
    }
}