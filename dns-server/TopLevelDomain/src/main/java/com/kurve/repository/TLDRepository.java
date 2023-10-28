package com.kurve.repository;

import com.kurve.models.TLD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLDRepository extends JpaRepository<TLD, Long> {
}
