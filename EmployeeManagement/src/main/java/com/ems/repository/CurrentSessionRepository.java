package com.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Model.CurerrentSession;

public interface CurrentSessionRepository extends JpaRepository<CurerrentSession, String> {
	public Optional<CurerrentSession> findBySessionId(String sessionId);
}
