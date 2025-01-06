package Chess_Business.Database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private final int id;
    private final String username, password;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
