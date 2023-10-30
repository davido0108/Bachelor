package inventory.repository;

import javafx.collections.ObservableList;

import java.util.List;

public interface Repository<T, TID> {
    void add(T elem);
    void remove(TID id);
    T get(TID id);
    void update(T elem, TID id);
    ObservableList<T> getAll();
}
