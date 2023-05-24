package dao;


import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection connection = ConnectMySQL.getConnection();

    public User login(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User account = null;

            if (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                account = new User(username1, password1);
            }
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public User checkUserNam(String username) {
        try {
            String sql = "select * from user where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            User account = null;

            if (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                account = new User(username1, password1);
            }
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean create(User user) {
        try {
            String sql = "insert into user values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
