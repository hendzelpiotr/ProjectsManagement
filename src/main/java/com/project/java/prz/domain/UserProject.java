package com.project.java.prz.domain;

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
    @Column(name = "completion_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime completionDateTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
