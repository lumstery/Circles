package com.jarst.controller;

import com.jarst.service.UserService;
import com.jarst.domain.User;
import com.jarst.dto.PageParams;
import com.jarst.dto.RelatedUserDTO;
import com.jarst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}")
public class UserRelationshipController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserRelationshipController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/followings")
    public List<RelatedUserDTO> followings(@PathVariable("userId") long userId, PageParams pageParams) {
        final User user = userRepository.findOne(userId);
        return userService.findFollowings(user, pageParams);
    }

    @RequestMapping("/followers")
    public List<RelatedUserDTO> followers(@PathVariable("userId") long userId, PageParams pageParams) {
        final User user = userRepository.findOne(userId);
        return userService.findFollowers(user, pageParams);
    }
}
