package week02.classicmodels.persistence;

import week02.classicmodels.business.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends MySQLDao implements CustomerDao {

    @Override
    public List<Customer> selectCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE customerName = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public List<Customer> selectCustomersContainingName(String name) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE customerName LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer findCustomerById(int customerNumber) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE customerNumber = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer = mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customer.getCustomerNumber());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getContactLastName());
            ps.setString(4, customer.getContactFirstName());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddressLine1());
            ps.setString(7, customer.getAddressLine2());
            ps.setString(8, customer.getCity());
            ps.setString(9, customer.getState());
            ps.setString(10, customer.getPostalCode());
            ps.setString(11, customer.getCountry());
            ps.setInt(12, customer.getSalesRepEmployeeNumber());
            ps.setDouble(13, customer.getCreditLimit());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customerNumber"),
                rs.getString("customerName"),
                rs.getString("contactLastName"),
                rs.getString("contactFirstName"),
                rs.getString("phone"),
                rs.getString("addressLine1"),
                rs.getString("addressLine2"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postalCode"),
                rs.getString("country"),
                rs.getInt("salesRepEmployeeNumber"),
                rs.getDouble("creditLimit")
        );
    }
}
