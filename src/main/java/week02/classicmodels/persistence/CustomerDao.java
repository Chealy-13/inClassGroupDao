package week02.classicmodels.persistence;

import week02.classicmodels.business.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> selectCustomersByName(String name);
    public List<Customer> selectCustomersContainingName(String name);
    public Customer findCustomerById(int customerNumber);
    public boolean addCustomer(Customer customer);
}
