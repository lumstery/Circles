package com.jarst.controller;

import com.jarst.domain.Relationship;
import com.jarst.domain.User;
import com.jarst.repository.RelationshipRepository;
import com.jarst.repository.UserRepository;
import com.jarst.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relationships")
public class RelationshipController {

    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;
    private final SecurityContextService securityContextService;

    @Autowired
    public RelationshipController(UserRepository userRepository,
                                  RelationshipRepository relationshipRepository,
                                  SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.relationshipRepository = relationshipRepository;
        this.securityContextService = securityContextService;
    }

    @RequestMapping(value = "/to/{followedId}", method = RequestMethod.POST)
    public void follow(@PathVariable("followedId") Long followedId) {
        final User followed = userRepository.findOne(followedId);
        final User currentUser = securityContextService.currentUser();
        final Relationship relationship = new Relationship(currentUser, followed);
        // TODO unless followed

        relationshipRepository.save(relationship);
    }

    @RequestMapping(value = "/to/{followedId}", method = RequestMethod.DELETE)
    public void unfollow(@PathVariable("followedId") Long followedId) {
        final User followed = userRepository.findOne(followedId);
        final User currentUser = securityContextService.currentUser();
        final Relationship relationship = relationshipRepository
                .findOneByFollowerAndFollowed(currentUser, followed)
                .orElseThrow(RelationshipNotFoundException::new);

        relationshipRepository.delete(relationship);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No relationship")
    private class RelationshipNotFoundException extends RuntimeException {
    }

}
