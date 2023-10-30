package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import inventory.validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryServiceTest {
    InventoryService service;
    PartValidator partValidator;
    ProductValidator productValidator;
    ObservableList<Part> Parts;

    @BeforeEach
    void setUp() {
        partValidator = new PartValidator();
        productValidator = new ProductValidator();
        InventoryRepositoryFile repo = new InventoryRepositoryFile();
        Parts= FXCollections.observableArrayList() ;
        Parts.add(new InhousePart(2,"Surub", 1, 200, 4, 1000, 34));
        service = new InventoryService(repo);
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

    @Test
    void addProduct3() {
        try {
            productValidator.validate(new Product(1,"Ceas", 1, 200, -5, 1000, Parts));
            service.addProduct("Ceas", 1, 200, -5, 1000, Parts);
            assert (false);
        } catch (ValidationException err) {
            //assert (err.getMessage().equals(minErrMsg));
            System.out.println(err.getMessage());
            assert (true);
        }
    }

    @Test
    void addProduct4() {
        try {
            productValidator.validate(new Product(1,"Ceas", 500, 200, Integer.MAX_VALUE, 1000, Parts));
            service.addProduct("Ceas", 500, 200, Integer.MAX_VALUE, 1000, Parts);
            assert (false);
        } catch (ValidationException err) {
            //assert (err.getMessage().equals(minGreaterErrMsg+minGreaterMaxErrMsg+stockLowerErrMsg));
            System.out.println(err.getMessage());
            assert (true);
        }
    }

    @Test
    void addProduct5() {
        try {
            service.addProduct("A", 67, 20, 10, 30, Parts);
        } catch (ValidationException err) {
            assert (false);
        }
    }

    @Test
    void addProduct6() {
        try {
            service.addProduct("M", 67, 20, 1, 30, Parts);
        } catch (ValidationException err) {
            assert (false);
        }
    }

    @Test
    void addProduct7() {
        try {
            productValidator.validate(new Product(1,"M", 67, 20, 0, 30, Parts));
            service.addProduct("M", 67, 20, 0, 30, Parts);
            assert (false);
        } catch (ValidationException err) {
            //assert (err.getMessage().equals(minErrMsg));
            System.out.println(err.getMessage());
            assert (true);
        }
    }

    @Test
    void addProduct8() {
        try {
            productValidator.validate(new Product(1,"", 67, 20, 1, 30, Parts));
            service.addProduct("", 67, 20, 1, 30, Parts);
            assert (false);
        } catch (ValidationException err) {
            //assert (err.getMessage().equals(emptyNameErrMsg));
            System.out.println(err.getMessage());
            assert (true);
        }
    }
}