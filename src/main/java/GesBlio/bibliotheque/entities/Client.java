package GesBlio.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String firstname;
    private String secondName;
    private int numIdCart;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER) // A chaque fois que je charge un user, j'ai ses roles donc on utilise eager
    private Collection<AppRoles> appRoles = new ArrayList<>();
}
