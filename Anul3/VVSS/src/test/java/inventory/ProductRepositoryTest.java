package inventory;

import inventory.model.Product;
import inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class ProductRepositoryTest {

    ProductRepository repo;
    ProductRepository emptyListRepo;

    Product prod;
    Product prod2;
    Product prod3;

    @BeforeEach
    void setUp() {
        repo= new ProductRepository();

//        prod=new Product(1, "nume_gasit", 1.0, 0, 0, 0, null);
//        prod2=new Product(2, "produs_second", 2.0, 0, 0, 0, null);
//        prod3=new Product(3, "prod", 3.0, 0, 0, 0, null);
        prod=mock(Product.class);
        prod2=mock(Product.class);
        prod3=mock(Product.class);

        Mockito.when(prod.getName()).thenReturn("prod");
        Mockito.when(prod2.getName()).thenReturn("prod2");
        Mockito.when(prod3.getName()).thenReturn("prod3");

        Mockito.when(prod.getProductId()).thenReturn(1);
        Mockito.when(prod2.getProductId()).thenReturn(2);
        Mockito.when(prod3.getProductId()).thenReturn(3);

        repo.add(prod);
        repo.add(prod2);
        repo.add(prod3);

        emptyListRepo= new ProductRepository();
    }

    @Test
    void lookupProductByNameOk() {
        Product productFound=repo.getByNameOrId("prod");

        assertEquals(prod,productFound);
    }

    @Test
    void lookupProductByIdOk() {
        Product productFound=repo.getByNameOrId("2");

        assertEquals(prod2,productFound);
    }

    @Test
    void lookupProductNotOk() {
        Product productFound=repo.getByNameOrId("notFound");

        assertNull(productFound);
    }

    @Test
    void lookupProductListEmpty() {
        Product emptyProduct=new Product(0,null,0.0,0,0,0,null);

        Product productFound=emptyListRepo.getByNameOrId("prod");

        assertEquals(emptyProduct,productFound);
    }

    @Test
    void lookupProduct2ndElement() {
        Product productFound=repo.getByNameOrId("prod2");

        assertEquals(prod2,productFound);
    }
}
