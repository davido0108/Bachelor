package inventory;

import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class IntregrationRepositoryTest {
    InventoryService service;
    InventoryRepositoryFile repo;
    Product prod;
    Product prod2;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepositoryFile();
        service = new InventoryService(repo);

        prod=mock(Product.class);
        prod2=mock(Product.class);

        repo.addProductInMemory(prod);
        repo.addProductInMemory(prod2);

        Mockito.when(prod.getName()).thenReturn("prod1");
        Mockito.when(prod.getProductId()).thenReturn(1);

        Mockito.when(prod2.getName()).thenReturn("prod2");
        Mockito.when(prod2.getProductId()).thenReturn(2);
    }

    @Test
    void lookupProductByNameOk() {
        Product productFound=service.lookupProduct("prod1");
        assertEquals(prod,productFound);
    }

    @Test
    void lookupProductByIdOk() {
        Product productFound=service.lookupProduct("prod2");
        assertEquals(prod2,productFound);
    }
}
