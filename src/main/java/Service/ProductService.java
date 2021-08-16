package Service;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> list = new ArrayList<>();
    public List<Product> finAll(){
        return list;
    }
    public void save(Product product) {
        list.add(product);
    }
    public Product findById(int id) {
        return list.get(id);
    }
    public void update(int id, Product product) {
        for (Product p : list) {
            if (p.getId() == id) {
                p = product;
                break;
            }
        }
    }
    public void remove(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                break;
            }
        }
    }
}
