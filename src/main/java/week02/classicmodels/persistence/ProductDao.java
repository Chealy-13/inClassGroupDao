package week02.classicmodels.persistence;

import week02.classicmodels.business.Employee;
import week02.classicmodels.business.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getAllProducts();
}
