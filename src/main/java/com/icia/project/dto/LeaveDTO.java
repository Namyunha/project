package com.icia.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LeaveDTO {
    private Long id;
    private String groupName;
    private String leaveUserName;
    private String reason;
    private Long groupId;
    private Long userId;
    private String reviewHistory;
}
