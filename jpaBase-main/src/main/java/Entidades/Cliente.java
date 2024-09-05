package Entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity // anotacion propia de lombok
@Table(name = "Cliente") //not mandatory
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // que sea incremental y autonumerica
    private Long id;
    private String apellido;
    private String nombre;

    @Column(name ="dni" , unique = true) //con el unique hacemos que sea único el dni
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    //No propietaria
    @OneToMany(mappedBy = "cliente")
    private Set<Factura> factura;


}
