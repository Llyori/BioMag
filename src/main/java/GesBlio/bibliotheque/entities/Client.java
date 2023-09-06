package GesBlio.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String firstName;
    private String secondName;
    private Long numIdCart;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    @Transient
    private String confPassword;
    @Column(columnDefinition = "varchar(64)")
    private String codeVerification;
    @Column(columnDefinition = "boolean default false")
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER) // A chaque fois que je charge un user, j'ai ses roles donc on utilise eager
    @Nullable
    private Collection<AppRoles> appRoles = new ArrayList<>();
}
