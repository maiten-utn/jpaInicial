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
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String denominacion;
    private int precio;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name= "articulo_categoria",
            joinColumns = @JoinColumn(name="articulo_id"),
            inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "articulo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<DetalleFactura> detalles = new HashSet<>();
}
