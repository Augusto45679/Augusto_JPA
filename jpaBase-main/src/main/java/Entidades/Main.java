package Entidades;

import javax.persistence.EntityManager; //
import javax.persistence.EntityManagerFactory; //
import javax.persistence.Persistence; //


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Andando!");
        try{
            entityManager.getTransaction().begin();

            //Creo una Categoria
            Categoria categoriaCocina = Categoria.builder()
                    .denominacion("Cocina")
                    .build();

            //Creo un Articulo
            Articulo cuchillo = Articulo.builder()
                    .cantidad(220)
                    .denominacion("Cuchillo")
                    .precio(45)
                    .build();
            Articulo olla = Articulo.builder()
                    .cantidad(20)
                    .denominacion("Olla")
                    .precio(599)
                    .build();

            cuchillo.getCategoria().add( categoriaCocina); // Añado la categoria baño a shampoo
            olla .getCategoria().add( categoriaCocina); // Añado la categoria baño a shampoo


            //Creo un domicilio
            Domicilio domicilio1 = Domicilio.builder()
                    .numero(48)
                    .nombreCalle("Pasco")
                    .build();

            //Creo un cliente
            Cliente cliente1 = Cliente.builder()
                    .apellido("Fernandez")
                    .dni(2440000)
                    .nombre("Juan")
                    .domicilio(domicilio1)  //asigno domicilio en sobremonte
                    .build();

            //Creo una factura
            Factura factura1 = Factura.builder()
                    .fecha("10-02-2020")
                    .numero(2222232)
                    .cliente(cliente1)  //Relaciono el cliente a la factura
                    .total(6666)
                    .build();

            //Creo dos detalles
            DetalleFactura detalle1 = DetalleFactura.builder()
                    .cantidad(12)
                    .subtotal(200)
                    .articulo( cuchillo)    //asigno articulo shampoo
                    .factura(factura1)
                    .build();
            DetalleFactura detalle2 = DetalleFactura.builder()
                    .cantidad(10)
                    .subtotal(14)
                    .articulo(olla)    //asigno articulo jabon
                    .factura(factura1)
                    .build();

            factura1.getDetalleFactura().add(detalle1);
            factura1.getDetalleFactura().add(detalle2);


            entityManager.persist(factura1);


            entityManager.getTransaction().commit();


           // System.out.println("Cliente: "+cliente1+"\n"+"Domicilio"+domicilio1+"\n"+
                 //   "Factura"+factura1+"\n"+"DetalleeFactura:"+detalle1+"\n"+"Articulo"+articulo1+
                   // "\n"+ "Categoria"+categoria1);

        }catch(Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());

            System.out.println("No se pudo registrar !");

        }

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();


    }
}
