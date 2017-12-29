package com.project.java.prz.server.web.controller;

import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.common.core.exception.GeneralException;
import com.project.java.prz.server.core.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Piotr on 09.06.2017.
 */
@RestController
@RequestMapping("user-settings")
public class UserSettingController {

    @Autowired
    private UserSettingService userSettingService;

    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    @GetMapping("my")
    public ResponseEntity<List<UserSettingDTO>> getMyUserSettings(Principal principal) {
        List<UserSettingDTO> userSettingDTOs = userSettingService.getUserSettings(principal.getName());
        return ResponseEntity.ok(userSettingDTOs);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<UserSettingDTO>> getAllGlobalUserSettings() {
        List<UserSettingDTO> userSettingDTOs = userSettingService.getAllGlobalUserSettings();
        return ResponseEntity.ok(userSettingDTOs);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("user-details/{login}")
    public ResponseEntity<List<UserSettingDTO>> getUserSettingsByLogin(@PathVariable("login") String login) {
        List<UserSettingDTO> userSettings = userSettingService.getUserSettings(login);
        return ResponseEntity.ok(userSettings);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("user-details/{login}")
    public ResponseEntity<UserSettingDTO> updateUserSettingByUserDetailLogin(@PathVariable("login") String login, @RequestBody UserSettingDTO userSettingDTO) {
        UserSettingDTO updatedUserSettingDTO = userSettingService.saveOrUpdate(login, userSettingDTO);
        return ResponseEntity.ok(updatedUserSettingDTO);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<UserSettingDTO> updateUserSetting(@PathVariable("id") Integer id, @RequestBody UserSettingDTO userSettingDTO) {
        if (id.equals(userSettingDTO.getId())) {
            UserSettingDTO updatedUserSettingDTO = userSettingService.updateUserSetting(userSettingDTO, id);
            return ResponseEntity.ok(updatedUserSettingDTO);
        } else throw new GeneralException(GeneralException.FailReason.INVALID_IDS);
    }

}
