package org.example.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String denominacion;

    @ManyToMany(mappedBy = "categorias")
    private Set<Articulo> articulos = new HashSet<>();

    @Builder
    public Categoria(int id, String denominacion, Set<Articulo> articulos) {
        this.id = id;
        this.denominacion = denominacion;
        this.articulos = (articulos != null) ? articulos : new HashSet<>(); // Inicializar si es null
    }
}
