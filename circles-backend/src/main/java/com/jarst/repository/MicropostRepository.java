package com.jarst.repository;

import com.jarst.domain.Micropost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicropostRepository extends JpaRepository<Micropost, Long>, MicropostRepositoryCustom {
}
