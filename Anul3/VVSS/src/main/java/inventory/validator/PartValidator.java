package inventory.validator;

import inventory.model.Part;

public class PartValidator implements Validator<Part> {
    @Override
    public void validate(Part entity) throws ValidationException {
        String errorMessage="";
        if(entity.getName().equals("")) {
            errorMessage += "A name has not been entered. ";
        }
        if(entity.getPrice() < 0.01) {
            errorMessage += "The price must be greater than 0. ";
        }
        if(entity.getInStock() < 1) {
            errorMessage += "Inventory level must be greater than 0. ";
        }
        if(entity.getMin() > entity.getMax()) {
            errorMessage += "The Min value must be less than the Max value. ";
        }
        if(entity.getMin() < 0) {
            errorMessage += "The Min value must be higher than zero.";
        }
        if(entity.getInStock() < entity.getMin()) {
            errorMessage += "Inventory level is lower than minimum value. ";
        }
        if(entity.getInStock() > entity.getMax()) {
            errorMessage += "Inventory level is higher than the maximum value. ";
        }
        if(!errorMessage.equals(""))
            throw new ValidationException(errorMessage);
    }
}
