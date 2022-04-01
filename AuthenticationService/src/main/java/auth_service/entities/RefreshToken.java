package auth_service.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token_storage")
public class RefreshToken {
    @Setter @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "app_user_id",referencedColumnName = "id")
    private AppUser appUser;

    @Setter @Getter
    @Column(name = "token")
    private String token;

    @Setter @Getter
    @Column(name = "expired_date")
    private LocalDateTime expiredDateTime;

    @Setter @Getter
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
