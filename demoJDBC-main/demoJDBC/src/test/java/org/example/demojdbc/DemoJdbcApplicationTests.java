package org.example.demojdbc;

import jakarta.transaction.Transactional;
import org.example.demojdbc.model.Producto;
import org.example.demojdbc.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // Esto limpia cada test al terminar
@Sql({"/schema.sql"}) // Asegúrate de que esta crea la tabla PERSONA pero no inserta datos
class DemoJdbcApplicationTests {

    @Autowired
    ProductoRepository repositorio;

    @Test
    void borrarTodos() {
        repositorio.borrarTodos();
        List<Producto> lista = repositorio.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    void insertarPersona() {
        repositorio.borrarTodos();
        repositorio.insertar(new Producto("pedro", "perez", 18));
        repositorio.insertar(new Producto("javier", "sanchez", 20));
        List<Producto> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void buscarTodos() {
        // Limpiamos primero la tabla para asegurarnos de que no haya datos anteriores
        repositorio.borrarTodos();

        // Insertamos dos personas
        repositorio.insertar(new Producto("Pepe", "Garcia", 30));
        repositorio.insertar(new Producto("Juan", "Lopez", 28));

        // Obtenemos la lista y verificamos su tamaño
        List<Producto> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size(), "Debe haber exactamente 2 personas en la base de datos");
    }


    @Test
    void borrarPersona() {
        repositorio.borrarTodos();
        repositorio.insertar(new Producto("pedro", "perez", 18));
        repositorio.insertar(new Producto("javier", "sanchez", 20));
        repositorio.borrar(new Producto("pedro"));
        List<Producto> lista = repositorio.buscarTodos();
        assertEquals(1, lista.size());
    }

    @Test
    void buscarUno() {
        repositorio.borrarTodos();
        repositorio.insertar(new Producto("javier", "sanchez", 20));
        Producto producto = repositorio.buscarUno("javier");
        assertEquals("javier", producto.getNombre());
    }
}
