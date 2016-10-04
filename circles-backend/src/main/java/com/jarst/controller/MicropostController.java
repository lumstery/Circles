package com.jarst.controller;

import com.jarst.domain.Micropost;
import com.jarst.domain.User;
import com.jarst.repository.MicropostRepository;
import com.jarst.service.MicropostService;
import com.jarst.service.NotPermittedException;
import com.jarst.service.SecurityContextService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/microposts")
public class MicropostController {

    private final MicropostRepository micropostRepository;
    private final MicropostService micropostService;
    private final SecurityContextService securityContextService;

    @Autowired
    public MicropostController(MicropostRepository micropostRepository,
                               MicropostService micropostService,
                               SecurityContextService securityContextService) {
        this.micropostRepository = micropostRepository;
        this.micropostService = micropostService;
        this.securityContextService = securityContextService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Micropost create(@RequestBody MicropostParam param) {
        User currentUser = securityContextService.currentUser();
        return micropostRepository.save(new Micropost(currentUser, param.getContent()));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        micropostService.delete(id);
    }

    @Data
    private static class MicropostParam {
        private String content;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotPermittedException.class)
    public void handleNoPermission() {
    }
}
