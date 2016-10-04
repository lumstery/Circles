package com.jarst.repository;

import com.jarst.domain.User;
import com.jarst.dto.PostDTO;
import com.jarst.domain.Micropost;
import com.jarst.dto.PageParams;

import java.util.List;

interface MicropostRepositoryCustom {

    List<PostDTO> findAsFeed(User user, PageParams pageParams);

    List<Micropost> findByUser(User user, PageParams pageParams);
}
