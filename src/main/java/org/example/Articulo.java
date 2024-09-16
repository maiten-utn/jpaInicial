package org.example;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

@Data
@Builder
@Entity
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incrementa el id en 1
    private Long id;
    private int cantidad;
    private String denominacion;
    private int precio;
    
     @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    private List<DetalleFactura> detalle = new ArrayList<>();
     
     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
     @JoinTable( name = "articulo_categoria", joinColumns = @JoinColumn(name = "articulo_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
     private List<Categoria> categorias = new ArrayList<Categoria>();
    
    @Builder
    public Articulo(Long id, int cantidad, String denominacion, int precio, List<DetalleFactura> detalle, List<Categoria> categorias) {
        this.id = id;
        this.cantidad = cantidad;
        this.denominacion = denominacion;
        this.precio = precio;
        this.detalle = (detalle != null) ? detalle : new ArrayList<>();  // Inicializar si es null
        this.categorias = (categorias != null) ? categorias : new ArrayList<>();  // Inicializar si es null
    
}
}
