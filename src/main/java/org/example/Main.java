package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        

        try {
            // Persistir una nueva entidad Factura
            entityManager.getTransaction().begin();
            Factura factura1 = Factura.builder().numero(232).fecha("9/10/2024").build();
            
            Domicilio domicilio1 = Domicilio.builder().nombreCalle("Mitre").numero(321).build();
            Cliente cliente1 = Cliente.builder().nombre("Juan").apellido("Perez").dni(45626123).domicilio(domicilio1).build();
            domicilio1.setCliente(cliente1);
            
            factura1.setCliente(cliente1);
            
            Categoria cat1 = Categoria.builder().denominacion("Almacen").build();
            Categoria cat2 = Categoria.builder().denominacion("Bebidas").build();
            Categoria cat3 = Categoria.builder().denominacion("Ropa").build();
            
            Articulo art1 = Articulo.builder().denominacion("Leche").precio(200).cantidad(50).build();
            Articulo art2 = Articulo.builder().denominacion("Medias").precio(250).cantidad(10).build();
            
            art1.getCategorias().add(cat1);
            art1.getCategorias().add(cat2);
            
            cat1.getArticulos().add(art1);
            cat2.getArticulos().add(art1);
            
            art2.getCategorias().add(cat3);
            cat3.getArticulos().add(art2);
            
            DetalleFactura detalle1 = DetalleFactura.builder().articulo(art1).cantidad(3).subtotal(600).build();
            art1.getDetalle().add(detalle1);
            factura1.getDetalle().add(detalle1);
            detalle1.setFactura(factura1);
            
            DetalleFactura detalle2 = DetalleFactura.builder().articulo(art2).cantidad(2).subtotal(500).build();
            art2.getDetalle().add(detalle2);
            factura1.getDetalle().add(detalle2);
            detalle2.setFactura(factura1);
            
            factura1.setTotal(1100);
            
            entityManager.persist(factura1);

            entityManager.getTransaction().commit();


            Factura retrievedFactura = entityManager.find(Factura.class, factura1.getId());
            System.out.println("Retrieved Factura: " + retrievedFactura.getNumero());

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
