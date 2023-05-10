package ma.ehtp.gestionrisqueit.phase4.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 64)
    private String photos;

    // other fields and getters, setters are not shown



    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/photos/" + organization.getName() + "_" + organization.getId() + "/" + photos;
    }
}


