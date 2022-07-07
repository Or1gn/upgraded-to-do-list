package com.project.Batnik.model.RQ;

import com.project.Batnik.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRQ {

    @Size(min = 10, message = Constants.NOT_VALID_TEXT_MESSAGE)
    private String text;

    private Timestamp dateOfDeadline;
}
