package com.kurve.repository;

import com.kurve.models.NameServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameServerRepository extends JpaRepository<NameServer, Long> {
}
