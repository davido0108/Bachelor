package inventory;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static inventory.validator.PartValidator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.*;
public class InventoryServiceTest {
    InventoryService service;
    InventoryRepositoryFile repo;

    @BeforeEach
    void setUp() {
        repo = mock(InventoryRepositoryFile.class);
        service = new InventoryService(repo);
    }

    @Test
    void testGetAllProducts(){
        Product prod=mock(Product.class);
        Mockito.when(repo.getAllProducts()).thenReturn(FXCollections.observableArrayList(prod));
        assertEquals(service.getAllProducts().size(),1);
    }

    @Test
    void testGetAllParts(){
        Part part=mock(Part.class);
        Mockito.when(repo.getAllParts()).thenReturn(FXCollections.observableArrayList(part));
        assertEquals(service.getAllParts().size(),1);
    }
}
