package org.example;

import org.example.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaHibernatePU");
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crear Cliente
            Cliente cliente = Cliente.builder()
                    .nombre("Juan")
                    .listaFactura(new ArrayList<>())
                    .apellido("Robledo")
                    .dni(56392029)
                    .build();

            // Crear Domicilio
            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("San Juan")
                    .numero(2032)
                    .build();

            //Asociar cliente y  domicilio
            cliente.setDomicilio(domicilio);


            // Crear Factura
            Factura factura1 = Factura.builder()
                    .numero(1)
                    .listaDetalleFactura(new ArrayList<>())
                    .fecha("16/9/2024")
                    .build();

            Factura factura2 = Factura.builder()
                    .numero(2)
                    .listaDetalleFactura(new ArrayList<>())
                    .fecha("19/9/2024")
                    .build();

            //Crear Categoria
            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .listaArticulo(new ArrayList<>())
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lácteos")
                    .listaArticulo(new ArrayList<>())
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .listaArticulo(new ArrayList<>())
                    .build();
            Categoria repuestos = Categoria.builder()
                    .denominacion("Repuestos")
                    .listaArticulo(new ArrayList<>())
                    .build();

            // Crear Artículos
            Articulo articulo1 = Articulo.builder()
                    .denominacion("Leche")
                    .listaDetalleFactura(new ArrayList<>())
                    .cantidad(220)
                    .listaCategoria(new ArrayList<>())
                    .precio(900)
                    .build();
            Articulo articulo2 = Articulo.builder()
                    .denominacion("Limpia Piso")
                    .listaDetalleFactura(new ArrayList<>())
                    .cantidad(300)
                    .precio(3200)
                    .listaCategoria(new ArrayList<>())
                    .build();

            // Inicializar listas
            articulo1.setListaCategoria(new ArrayList<>());
            articulo2.setListaCategoria(new ArrayList<>());

            // Asociar categorías a artículos y viceversa
            articulo1.getListaCategoria().add(perecederos);
            articulo1.getListaCategoria().add(lacteos);
            perecederos.getListaArticulo().add(articulo1);
            lacteos.getListaArticulo().add(articulo1);

            articulo2.getListaCategoria().add(limpieza);
            articulo2.getListaCategoria().add(repuestos);
            limpieza.getListaArticulo().add(articulo2);
            repuestos.getListaArticulo().add(articulo2);

            // Crear DetalleFactura
            DetalleFactura detalle1 = DetalleFactura.builder()
                    .articulo(articulo1)
                    .cantidad(5)
                    .subtotal(70)
                    .build();
            articulo1.getListaDetalleFactura().add(detalle1);
            detalle1.setFactura(factura1);

            DetalleFactura detalle2 = DetalleFactura.builder()
                    .articulo(articulo1)
                    .cantidad(3)
                    .subtotal(40)
                    .build();
            articulo1.getListaDetalleFactura().add(detalle2);
            detalle2.setFactura(factura1);

            DetalleFactura detalle3 = DetalleFactura.builder()
                    .articulo(articulo2)
                    .cantidad(4)
                    .subtotal(60)
                    .build();
            articulo2.getListaDetalleFactura().add(detalle3);
            detalle3.setFactura(factura2);
            DetalleFactura detalle4 = DetalleFactura.builder()
                    .articulo(articulo2)
                    .cantidad(3)
                    .subtotal(60)
                    .build();
            detalle4.setFactura(factura2);


            articulo2.getListaDetalleFactura().add(detalle4);
            factura1.setTotal(470);
            factura1.getListaDetalleFactura().add(detalle1);
            factura1.getListaDetalleFactura().add(detalle2);
            factura2.setTotal(420);
            factura2.getListaDetalleFactura().add(detalle3);
            factura2.getListaDetalleFactura().add(detalle4);

            factura1.setCliente(cliente);
            factura2.setCliente(cliente);




            //Asociar factura creada por completo con cliente
            cliente.getListaFactura().add(factura1);
            cliente.getListaFactura().add(factura2);
            // Persistir la cliente (esto deberia persistir las clases asociadas, domicilio, factura y sus relaciones)
            em.flush();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            if (entityManagerFactory.isOpen()) {
                entityManagerFactory.close();
            }
        }
    }
}