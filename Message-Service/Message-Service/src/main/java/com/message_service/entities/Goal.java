package com.message_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    String goalId;
    String goalName;
    Double currentAmount;
    Double goalAmount;
    String targetDate;
    String userId;
    private boolean isComplete;
    @JsonIgnoreProperties
    private double progress;
}
