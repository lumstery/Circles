package com.jarst.service;

import com.jarst.domain.User;
import com.jarst.dto.PageParams;
import com.jarst.dto.RelatedUserDTO;
import com.jarst.dto.UserDTO;
import com.jarst.dto.UserParams;

import java.util.List;
import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {

    User update(User user, UserParams params);

    List<RelatedUserDTO> findFollowings(User user, PageParams pageParams);

    List<RelatedUserDTO> findFollowers(User user, PageParams pageParams);

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findMe();
}
