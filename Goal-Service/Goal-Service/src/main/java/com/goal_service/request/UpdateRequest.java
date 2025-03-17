package com.goal_service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private String goalName;
    private Double currentAmount= 0.0;
    private Double goalAmount= 0.0;
    private String targetDate;
}
