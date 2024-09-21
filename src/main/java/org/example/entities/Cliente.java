package org.example.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private int dni;

    @Builder.Default
    @OneToMany(mappedBy = "cliente")
    private Set<Factura> facturas = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_domicilio")
    private Domicilio domicilio;
}
