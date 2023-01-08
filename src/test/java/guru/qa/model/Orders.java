package guru.qa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Orders {
    private String message;
    private int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
