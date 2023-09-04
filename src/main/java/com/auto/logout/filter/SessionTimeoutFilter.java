package com.auto.logout.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

import com.auto.logout.model.Session;
import com.auto.logout.service.SessionService; // Import the SessionService class

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@WebFilter
public class SessionTimeoutFilter extends OncePerRequestFilter {
    private final SessionService sessionService;

    @Autowired
    public SessionTimeoutFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        Session session = sessionService.getSessionBySessionId(sessionId);
        
        if(session == null) {
        	//sessionService.createSession(sessionId, LocalDateTime.now(), null);
        }
        if (session != null) {
            LocalDateTime expirationTime = session.getExpirationTime();

            // Check if the session has expired
            if (expirationTime.isBefore(LocalDateTime.now())) {
                // Session has expired, log out the user
                logoutUser(request);
                sessionService.deleteSession(sessionId);
                response.sendRedirect("/logout");
                return;
            }

            // Extend the session timeout (e.g., by updating the expiration time)
            // The actual implementation may vary depending on how you manage sessions
            // For example, you can update the session's expiration time in the database.
        }

        filterChain.doFilter(request, response);
    }

    private void logoutUser(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            request.getSession().invalidate();
        }
    }
}
