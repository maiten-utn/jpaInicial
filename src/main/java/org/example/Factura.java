package org.example;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incrementa el id en 1
    
    private Long id;
    private String fecha;
    private int numero;
    private int total;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy ="factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalle = new ArrayList<>();
    
    @Builder
    public Factura(Long id, String fecha, int numero, int total, Cliente cliente, List<DetalleFactura> detalle) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
        this.cliente = cliente;
        this.detalle = (detalle != null) ? detalle : new ArrayList<>();  // Inicializa si es null
    }
    
}

