package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegrationEntityTest {
    InventoryService service;
    ObservableList<Part> Parts;

    @BeforeEach
    void setUp() {
        InventoryRepositoryFile repo = new InventoryRepositoryFile();
        service = new InventoryService(repo);
        Parts= FXCollections.observableArrayList() ;
        Parts.add(new InhousePart(2,"Surub", 1, 200, 4, 1000, 34));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct1() {
        try {
            service.addProduct("Ceas", 0.5, 200, 5, 1000, Parts);
        } catch (ValidationException err) {
            assert (false);
        }
    }

    @Test
    void addProduct2() {
        try {
            service.addProduct("Ceas", 200, 20, 4, 1000, Parts);
        } catch (ValidationException err) {
            assert (false);
        }
    }
}
