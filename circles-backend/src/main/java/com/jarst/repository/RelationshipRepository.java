package com.jarst.repository;

import com.jarst.domain.Relationship;
import com.jarst.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    Optional<Relationship> findOneByFollowerAndFollowed(User follower, User followed);
}
