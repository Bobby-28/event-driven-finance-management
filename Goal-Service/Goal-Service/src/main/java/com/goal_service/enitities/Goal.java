package com.goal_service.enitities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    String goalId;
    String goalName;
    Double currentAmount=0.0;
    Double goalAmount=0.0;
    String targetDate;
    String userId;
    private boolean isComplete= false;
    public double getProgress(){
        return (currentAmount/goalAmount)*100;
    }

}
