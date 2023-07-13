package com.icia.project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "leave_table")
public class LeaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String groupName;

    @Column(nullable = false, length = 10)
    private String leaveUserName;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    private String reviewHistory;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private Long userId;


}
