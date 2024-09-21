package org.example.main;
import org.example.entities.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Factura factura1 = Factura.builder().fecha("21/09/24").numero(50).build();

            Domicilio domicilio1 = Domicilio.builder().nombreCalle("Calle").numero(123).build();

            Cliente cliente1 = Cliente.builder()
                    .nombre("Cristina")
                    .apellido("Lopez")
                    .dni(1111111)
                    .domicilio(domicilio1)
                    .build();

            domicilio1.setCliente(cliente1);
            factura1.setCliente(cliente1);

            Categoria perecederos = Categoria.builder().denominacion("Perecederos").build();
            Categoria lacteos = Categoria.builder().denominacion("Lacteos").build();
            Categoria limpieza = Categoria.builder().denominacion("Limpieza").build();

            Articulo leche = Articulo.builder()
                    .denominacion("Leche")
                    .precio(100)
                    .build();
            leche.getCategorias().add(perecederos);
            leche.getCategorias().add(lacteos);

            lacteos.getArticulos().add(leche);
            perecederos.getArticulos().add(leche);

            Articulo lustramuebles = Articulo.builder()
                    .denominacion("Lustramuebles")
                    .precio(250)
                    .build();
            lustramuebles.getCategorias().add(limpieza);
            limpieza.getArticulos().add(lustramuebles);

            DetalleFactura detalleFactura1 = DetalleFactura.builder()
                    .articulo(leche)
                    .cantidad(2)
                    .subtotal(200)
                    .build();

            leche.getDetalles().add(detalleFactura1);
            factura1.getDetallesFactura().add(detalleFactura1);
            detalleFactura1.setFactura(factura1);

            DetalleFactura detalleFactura2 = DetalleFactura.builder()
                    .articulo(lustramuebles)
                    .cantidad(3)
                    .subtotal(750)
                    .build();

            lustramuebles.getDetalles().add(detalleFactura2);
            factura1.getDetallesFactura().add(detalleFactura2);
            detalleFactura2.setFactura(factura1);

            factura1.setTotal(950);


        } catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println("Error en la transacción");
            System.out.println(e.getMessage());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}