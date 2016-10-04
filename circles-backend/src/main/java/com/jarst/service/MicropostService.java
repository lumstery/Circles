package com.jarst.service;

import com.jarst.dto.PostDTO;
import com.jarst.domain.User;
import com.jarst.dto.PageParams;

import java.util.List;

public interface MicropostService {

    void delete(Long id);

    List<PostDTO> findAsFeed(PageParams pageParams);

    List<PostDTO> findByUser(User user, PageParams pageParams);
}
