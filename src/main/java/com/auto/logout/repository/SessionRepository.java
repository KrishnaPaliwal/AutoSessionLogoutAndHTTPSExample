package com.auto.logout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auto.logout.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}