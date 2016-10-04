package com.jarst.repository;

import com.jarst.domain.User;
import com.jarst.dto.RelatedUserDTO;
import com.jarst.dto.UserDTO;
import com.jarst.dto.PageParams;

import java.util.List;
import java.util.Optional;

interface UserRepositoryCustom {

    List<RelatedUserDTO> findFollowings(User user, User currentUser, PageParams pageParams);

    List<RelatedUserDTO> findFollowers(User user, User currentUser, PageParams pageParams);

    Optional<UserDTO> findOne(Long userId, User currentUser);
}
