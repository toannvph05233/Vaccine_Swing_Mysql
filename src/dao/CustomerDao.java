package dao;


import entity.Customer;
import entity.Vaccine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    Connection connection = ConnectMySQL.getConnection();

    public List<Customer> getAll() {
        try {
            String sql = "select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                byte age = resultSet.getByte("age");
                String address = resultSet.getString("address");
                String phone = resultSet.getNString("phone");
                Customer customer = new Customer(id, name, age, address, phone);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Customer getById(int idC) {
        try {
            String sql = "select * from customer where id=" + idC;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                byte age = resultSet.getByte("age");
                String address = resultSet.getString("address");
                String phone = resultSet.getNString("phone");
                Customer customer = new Customer(id, name, age, address, phone);
                return customer;
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean create(Customer customer) {
        try {
            String sql = "INSERT INTO `vaccine`.`Customer` (`name`, `age`, `address`, `phone`) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean edit(Customer customer) {
        try {
            String sql = "UPDATE `vaccine`.`Customer` SET `name` = ?, `age` = ?, `address` = ?, `phone` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setInt(5, customer.getId());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM `vaccine`.`customer` WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
