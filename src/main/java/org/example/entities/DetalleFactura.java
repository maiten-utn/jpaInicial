package org.example.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private int subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_factura")
    private Factura factura;

//    public int setSubtotal(){
//        subtotal = articulo.getPrecio() * this.cantidad;
//        return this.subtotal;
//    }
}
