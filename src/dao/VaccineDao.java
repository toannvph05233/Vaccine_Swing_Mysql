package dao;



import entity.Vaccine;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VaccineDao {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Connection connection = ConnectMySQL.getConnection();

    public List<Vaccine> getAll() {
        try {
            String sql = "select * from vaccine where count>0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Vaccine> vaccines = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String vacDate = resultSet.getString("vaccinDate");
                int count = resultSet.getInt("count");
                java.util.Date vaccinDate = format.parse(vacDate);
                Vaccine vaccine = new Vaccine(id, name, price, count, vaccinDate);
                vaccines.add(vaccine);
            }
            return vaccines;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Vaccine getById(int idV) {
        try {
            String sql = "select * from vaccine where id=" + idV;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String vacDate = resultSet.getString("vaccinDate");
                int count = resultSet.getInt("count");
                java.util.Date vaccinDate = format.parse(vacDate);
                Vaccine vaccine = new Vaccine(id, name, price, count, vaccinDate);
                return vaccine;
            }
            return null;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean create(Vaccine vaccine) {
        try {
            String sql = "INSERT INTO `vaccine`.`Vaccine` (`name`, `price`, `vaccinDate`,`count`) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vaccine.getName());
            preparedStatement.setDouble(2, vaccine.getPrice());
            preparedStatement.setString(3, format.format(vaccine.getVaccinDate()));
            preparedStatement.setInt(4, vaccine.getCount());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean edit(Vaccine vaccine) {
        try {
            String sql = "UPDATE `vaccine`.`Vaccine` SET `name` = ?, `price` = ?, `vaccinDate` = ?, `count` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vaccine.getName());
            preparedStatement.setDouble(2, vaccine.getPrice());
            preparedStatement.setString(3, format.format(vaccine.getVaccinDate()));
            preparedStatement.setInt(4, vaccine.getCount());
            preparedStatement.setInt(5, vaccine.getId());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM `vaccine`.`Vaccine` WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
