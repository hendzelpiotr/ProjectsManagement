package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.server.core.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by Piotr on 09.06.2017.
 */
@RestController
@RequestMapping("api/user-settings")
public class UserSettingController {

    @Autowired
    private UserSettingService userSettingService;

    @GetMapping("my")
    public ResponseEntity<List<UserSettingDTO>> getMyUserSettings(Principal principal) {
        List<UserSettingDTO> userSettingDTOs = userSettingService.getUserSettings(principal.getName());
        return ResponseEntity.ok(userSettingDTOs);
    }

}
