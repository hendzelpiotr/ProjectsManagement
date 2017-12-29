package com.project.java.prz.common.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Piotr on 24.06.2017.
 */
@Getter
@Setter
public class MailDTO {

    private String emailOfRecipient;
    private String subject;
    private String body;

}
