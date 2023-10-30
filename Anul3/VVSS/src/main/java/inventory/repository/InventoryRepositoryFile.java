package inventory.repository;


import inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.StringTokenizer;

public class InventoryRepositoryFile {

    private static String filename = "data/items.txt";

    private PartRepository partRepository;
    private ProductRepository productRepository;


    public InventoryRepositoryFile() {
        this.partRepository = new PartRepository();
        this.productRepository = new ProductRepository();
        readParts();
        readProducts();
    }

    public void readParts() {
        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        ObservableList<Part> listP = FXCollections.observableArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Part part = getPartFromString(line);
                if (part != null)
                    listP.add(part);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        partRepository.setAllParts(listP);
    }

    private Part getPartFromString(String line) {
        Part item = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("I")) {
            int id = Integer.parseInt(st.nextToken());
            partRepository.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            int idMachine = Integer.parseInt(st.nextToken());
            item = new InhousePart(id, name, price, inStock, minStock, maxStock, idMachine);
        }
        if (type.equals("O")) {
            int id = Integer.parseInt(st.nextToken());
            partRepository.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String company = st.nextToken();
            item = new OutsourcedPart(id, name, price, inStock, minStock, maxStock, company);
        }
        return item;
    }

    public void readProducts() {
        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        ObservableList<Product> listP = FXCollections.observableArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Product product = getProductFromString(line);
                if (product != null)
                    listP.add(product);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.setProducts(listP);
    }

    private Product getProductFromString(String line) {
        Product product = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("P")) {
            int id = Integer.parseInt(st.nextToken());
            productRepository.setAutoProductId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String partIDs = st.nextToken();

            StringTokenizer ids = new StringTokenizer(partIDs, ":");
            ObservableList<Part> list = FXCollections.observableArrayList();
            while (ids.hasMoreTokens()) {
                String idP = ids.nextToken();
                Part part = partRepository.get(Integer.valueOf(idP));
                if (part != null)
                    list.add(part);
            }
            product = new Product(id, name, price, inStock, minStock, maxStock, list);
            product.setAssociatedParts(list);
        }
        return product;
    }

    public void writeAll() {

        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        BufferedWriter bw = null;
        ObservableList<Part> parts = partRepository.getAll();
        ObservableList<Product> products = productRepository.getAll();

        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Part p : parts) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }

            for (Product pr : products) {
                String line = pr.toString() + ",";
                ObservableList<Part> list = pr.getAssociatedParts();
                int index = 0;
                while (index < list.size() - 1) {
                    line = line + list.get(index).getPartId() + ":";
                    index++;
                }
                if (index == list.size() - 1)
                    line = line + list.get(index).getPartId();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPart(Part part) {
        partRepository.add(part);
        writeAll();
    }

    public void addProduct(Product product) {
        productRepository.add(product);
        writeAll();
    }

    public void addProductInMemory(Product product) {
        productRepository.add(product);
    }

    public int getAutoPartId() {
        return partRepository.getAutoPartId();
    }

    public int getAutoProductId() {
        return productRepository.getAutoProductId();
    }

    public ObservableList<Part> getAllParts() {
        return partRepository.getAll();
    }

    public ObservableList<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public Part lookupPart(String search) {
        return partRepository.getByName(search);
    }

    public Product lookupProduct(String search) {
        return productRepository.getByName(search);
    }

    public void updatePart(int partIndex, Part part) {
        partRepository.update(part, partIndex);
        writeAll();
    }

    public void updateProduct(int productIndex, Product product) {
        productRepository.update(product, productIndex);
        writeAll();
    }

    public void deletePart(Part part) {
        partRepository.remove(part.getPartId());
        writeAll();
    }

    public void deleteProduct(Product product) {
        productRepository.remove(product.getProductId());
        writeAll();
    }

}

//    public Inventory getInventory() {
//        return inventory;
//    }

//    public void setInventory(Inventory inventory) {
//        this.inventory = inventory;
//    }
//}