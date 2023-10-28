package com.kurve.repository;

import com.kurve.models.RootServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootRepository extends JpaRepository<RootServer, Long> {
}
