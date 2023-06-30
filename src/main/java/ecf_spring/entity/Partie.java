package ecf_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partie")
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tournoi_id")
    private Tournoi tournoi;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private AppUser user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private AppUser user2;

    @Column(name = "date")
    private String datePartie;
    @Column(name = "heure")
    private String heurePartie;

    @Column(name = "vainqueur")
    private Integer vainqueur;
}
