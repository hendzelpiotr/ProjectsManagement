package com.project.java.prz.common.core.domain.general;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Piotr on 25.03.2017.
 */
@Entity
@Getter
@Setter
public class UserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String mark;
    @Column(name = "scheduled_completion_date")
    private LocalDateTime scheduledCompletionDateTime;
    @Column(name = "datetime_of_project_selection")
    private LocalDateTime dateTimeOfProjectSelection;
    private String programmingLanguage;
    private String technologies;
    @Column(name = "`database`")
    private String database;
    private String additionalInformation;
    private String repositoryLink;
    private boolean sourceFilesUploaded;
    @OneToOne
    @JoinColumn(name = "user_login")
    private UserDetails user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
