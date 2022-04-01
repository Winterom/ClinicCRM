package auth_service.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
@Entity
public class AppAuthority implements GrantedAuthority {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private AppAuthoritiesEnum authority;


    @Setter @Getter
    @ManyToOne
    @JoinColumn(name = "role",referencedColumnName = "id",nullable = false)
    private AppRole roles;


    @Override
    public String getAuthority() {
        return this.authority.name();
    }


}
