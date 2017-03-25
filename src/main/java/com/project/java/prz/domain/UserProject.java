package com.project.java.prz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mark;
    @Column(name = "completion_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime completionDateTime;
    @OneToOne(mappedBy = "userProject", fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
