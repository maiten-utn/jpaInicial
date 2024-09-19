package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private int subtotal;
    @ManyToOne
    @JoinColumn(name = "fk_factura")
    private Factura factura;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;
}
