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
import java.util.List;

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
    @OneToMany(mappedBy = "client")
    private List<Emprunt> emprunt;
    @OneToMany(mappedBy = "client")
    private List<Souscription> souscription;

    public Client(Long idClient, String firstName, String secondName, Long numIdCart, String email, String password, String phoneNumber, String confPassword, String codeVerification, boolean enabled, @Nullable Collection<AppRoles> appRoles) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.secondName = secondName;
        this.numIdCart = numIdCart;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.confPassword = confPassword;
        this.codeVerification = codeVerification;
        this.enabled = enabled;
        this.appRoles = appRoles;
    }
}
