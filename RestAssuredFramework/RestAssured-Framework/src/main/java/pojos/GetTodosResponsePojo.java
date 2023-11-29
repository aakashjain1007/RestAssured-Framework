package pojos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetTodosResponsePojo {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;
}
