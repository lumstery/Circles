package com.jarst.service;

import com.jarst.domain.User;

public interface SecurityContextService {
    User currentUser();
}
