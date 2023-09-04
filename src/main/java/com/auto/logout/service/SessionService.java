package com.auto.logout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auto.logout.model.Session;
import com.auto.logout.model.User;
import com.auto.logout.repository.SessionRepository;
import java.time.LocalDateTime;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(String sessionId, LocalDateTime expirationTime, User user) {
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setExpirationTime(expirationTime);
        session.setUser(user);

        return sessionRepository.save(session);
    }

    public Session getSessionBySessionId(String sessionId) {
        return sessionRepository.findBySessionId(sessionId);
    }

    public void deleteSession(String sessionId) {
        sessionRepository.deleteBySessionId(sessionId);
    }

    public void updateSessionExpirationTime(String sessionId, LocalDateTime expirationTime) {
        Session session = getSessionBySessionId(sessionId);
        if (session != null) {
            session.setExpirationTime(expirationTime);
            sessionRepository.save(session);
        }
    }

    // Add additional methods for session management as needed
}
