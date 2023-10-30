package inventory.validator;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.ObservableList;

public class ProductValidator implements  Validator<Product>{
    @Override
    public void validate(Product entity) throws ValidationException {

        String errorMessage="";
        double sumOfParts = 0.00;

        for (int i = 0; i < entity.getAssociatedParts().size(); i++) {
            sumOfParts += entity.getAssociatedParts().get(i).getPrice();
        }
        if (entity.getName().equals("")) {
            errorMessage += "A name has not been entered. ";
        }
        if (entity.getMin() <= 0) {
            errorMessage += "The inventory level must be greater than 0. ";
        }
        if (entity.getPrice() < 0.01) {
            errorMessage += "The price must be greater than $0. ";
        }
        if (entity.getMin() > entity.getMax()) {
            errorMessage += "The Min value must be less than the Max value. ";
        }
        if(entity.getInStock() < entity.getMin()) {
            errorMessage += "Inventory level is lower than minimum value. ";
        }
        if(entity.getInStock() > entity.getMax()) {
            errorMessage += "Inventory level is higher than the maximum value. ";
        }
        if (entity.getAssociatedParts().isEmpty()) {
            errorMessage += "Product must contain at least 1 part. ";
        }
        if (sumOfParts > entity.getPrice()) {
            errorMessage += "Product price must be greater than cost of parts. ";
        }
        if(!errorMessage.equals(""))
            throw new ValidationException(errorMessage);
    }
}
