package br.edu.ufrn.smartmenu.auth.components;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtAuthUtil jwtAuthUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        Boolean isValidToken = false;

        if (token != null) {
            isValidToken = jwtAuthUtil.isValidToken(token);
        }

        String email = null;

        if (isValidToken) {
            email = jwtAuthUtil.retrieveEmailFromToken(token);
        }

        User user = null;

        if (email != null) {
            user = userRepository.findByEmail(email).get();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (
            token != null
            && isValidToken
            && email != null
            && user != null
            && authentication == null
        ) {

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);
            
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

}
