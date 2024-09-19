package org.example;

import lombok.AccessLevel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "dni", unique = true)
    private int dni;
    private String apellido;
    private String nombre;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;
    
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas =  new ArrayList<Factura>();
    @Builder
    public Cliente(Long id, int dni, String apellido, String nombre, Domicilio domicilio, List<Factura> facturas) {
        this.id = id;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.facturas = (facturas != null) ? facturas : new ArrayList<>();
    }
}
