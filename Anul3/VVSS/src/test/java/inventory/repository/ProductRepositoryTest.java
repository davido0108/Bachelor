package inventory.repository;

import inventory.model.Product;
import inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository repo;
    ProductRepository emptyListRepo;

    Product prod;
    Product prod2;
    Product prod3;

    @BeforeEach
    void setUp() {
        repo= new ProductRepository();

        prod=new Product(1, "prod1", 1.0, 0, 0, 0, null);
        prod2=new Product(2, "prod2", 2.0, 0, 0, 0, null);
        prod3=new Product(3, "prod3", 3.0, 0, 0, 0, null);

        repo.add(prod);
        repo.add(prod2);
        repo.add(prod3);

        emptyListRepo= new ProductRepository();
    }

    @Test
    void lookupProductByNameOk() {
        Product productFound=repo.getByNameOrId("prod1");

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