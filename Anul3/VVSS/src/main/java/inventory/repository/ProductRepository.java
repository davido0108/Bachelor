package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProductRepository implements Repository<Product, Integer> {
    private ObservableList<Product> products;
    private int autoProductId;

    public ProductRepository() {
        this.products = FXCollections.observableArrayList();
    }

    @Override
    public void add(Product elem) {
        products.add(elem);
    }

    @Override
    public void remove(Integer id) {
        products.remove((int)id);
    }

    @Override
    public Product get(Integer id) {
        return products.get(id);
    }

    public Product getByName(String name){
        for(Product p: products) {
            if(p.getName().equals(name)) return p;
        }

        return null;
    }

    public Product getByNameOrId(String searchNameOrId){
        boolean isFound = false;
        for(Product p: products) {
            if(p.getName().equals(searchNameOrId))
                return p;
            if((p.getProductId()+"").equals(searchNameOrId))
                return p;
            isFound = true;
        }
        if(!isFound) {
            return new Product(0, null, 0.0, 0, 0, 0, null);
        }
        return null;
    }

    @Override
    public void update(Product elem, Integer id) {
        products.set(id, elem);
    }

    @Override
    public ObservableList<Product> getAll() {
        return products;
    }

    public int getAutoProductId() {
        autoProductId++;
        return autoProductId;
    }

    public void setProducts(ObservableList<Product> list) {
        products=list;
    }

    public void setAutoProductId(int id){
        autoProductId=id;
    }
}
