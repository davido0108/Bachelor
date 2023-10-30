package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PartRepository implements Repository<Part, Integer> {
    private ObservableList<Part> parts;
    private int autoPartId;

    public PartRepository() {
        this.parts = FXCollections.observableArrayList();
    }

    @Override
    public void add(Part elem) {
        parts.add(elem);
    }

    @Override
    public void remove(Integer id) {
        parts.remove((int)id);

    }

    @Override
    public Part get(Integer id) {
        return parts.get(id);
    }

    public Part getByName(String name){
        for(Part p: parts) {
            if(p.getName().equals(name)) return p;
        }

        return null;
    }

    @Override
    public void update(Part elem, Integer id) {
        parts.set(id, elem);

    }

    @Override
    public ObservableList<Part> getAll() {
        return parts;
    }

    public int getAutoPartId() {
        autoPartId++;
        return autoPartId;
    }

    public void setAllParts(ObservableList<Part> list) {
        parts=list;
    }

    public void setAutoPartId(int id){
        autoPartId=id;
    }

}
