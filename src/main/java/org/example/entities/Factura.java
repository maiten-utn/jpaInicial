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
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fecha;
    private int numero;
    private int total;

    @Builder.Default
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleFactura> detallesFactura =new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_cliente")
    private Cliente cliente;

//    public int setTotal(){
//        total = 0;
//        for(DetalleFactura df : detallesFactura){
//            total += df.getSubtotal();
//        }
//
//        return this.total;
//    }
}
