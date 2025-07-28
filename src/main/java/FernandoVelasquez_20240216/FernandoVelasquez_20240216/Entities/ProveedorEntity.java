package FernandoVelasquez_20240216.FernandoVelasquez_20240216.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBPROVIDER")
@Getter @Setter @ToString @EqualsAndHashCode
public class ProveedorEntity {
    //Atributos(campos) de la tabla tbProvider

    @Id //Llave primaria
    @Column(name = "PROVIDERID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    @SequenceGenerator(name = "seq_provider", sequenceName = "seq_provider", allocationSize = 1)
    private Long providerID;


    @Column(name = "PROVIDERNAME", unique = true)
    private String providerName;

    @Column(name = "PROVIDERPHONE")
    private String providerPhone;

    @Column(name = "PROVIDERADDRESS")
    private String providerAddress;

    @Column(name = "PROVIDEREMAIL")
    private String providerEmail;

    @Column(name = "PROVIDERCODE", unique = true)
    private String providerCode;

    @Column(name = "PROVIDERSTATUS")
    private Integer providerStatus;

    @Column(name = "PROVIDERCOMMENTS")
    private String providerComments;
}
