package dao;

import entity.CustomerVaccine;
import entity.Vaccine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerVaccineDao {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Connection connection = ConnectMySQL.getConnection();
    CustomerDao customerDao = new CustomerDao();
    VaccineDao vaccineDao = new VaccineDao();

    public List<CustomerVaccine> getAllByIdCustomer(int idCustomer) {
        try {
            String sql = "select * from customervaccine where idcustomer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCustomer);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CustomerVaccine> customerVaccines = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vacDate = resultSet.getString("vaccinDate");
                String vacInject = resultSet.getString("injectAgain");
                int idvaccine = resultSet.getInt("idvaccine");
                java.util.Date vaccinDate = format.parse(vacDate);
                java.util.Date injectAgain = format.parse(vacInject);
                CustomerVaccine customerVaccine = new CustomerVaccine(id, vaccinDate, injectAgain, customerDao.getById(idCustomer), vaccineDao.getById(idvaccine));
                customerVaccines.add(customerVaccine);
            }
            return customerVaccines;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    public CustomerVaccine getById(int idCV) {
        try {
            String sql = "select * from customervaccine where id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCV);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CustomerVaccine> customerVaccines = new ArrayList<>();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vacDate = resultSet.getString("vaccinDate");
                String vacInject = resultSet.getString("injectAgain");
                int idvaccine = resultSet.getInt("idvaccine");
                int idCustomer = resultSet.getInt("idCustomer");
                java.util.Date vaccinDate = format.parse(vacDate);
                java.util.Date injectAgain = format.parse(vacInject);
                CustomerVaccine customerVaccine = new CustomerVaccine(id, vaccinDate, injectAgain, customerDao.getById(idCustomer), vaccineDao.getById(idvaccine));
                return customerVaccine;
            }
            return null;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean create(CustomerVaccine customerVaccine) {
        try {
            String sql = "INSERT INTO `vaccine`.`CustomerVaccine` (`vaccinDate`, `injectAgain`, `idcustomer`, `idvaccine`) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, format.format(customerVaccine.getVaccinDate()));
            preparedStatement.setString(2, format.format(customerVaccine.getInjectAgain()));
            preparedStatement.setInt(3, customerVaccine.getCustomer().getId());
            preparedStatement.setInt(4, customerVaccine.getVaccine().getId());
            preparedStatement.execute();
            Vaccine vaccine = vaccineDao.getById(customerVaccine.getVaccine().getId());
            vaccine.setCount(vaccine.getCount() - 1);
            vaccineDao.edit(vaccine);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean edit(CustomerVaccine customerVaccine) {
        try {
            String sql = "UPDATE `vaccine`.`CustomerVaccine` SET `vaccinDate` = ?, `injectAgain` = ?, `idcustomer` = ?, `idvaccine` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, format.format(customerVaccine.getVaccinDate()));
            preparedStatement.setString(2, format.format(customerVaccine.getInjectAgain()));
            preparedStatement.setInt(3, customerVaccine.getCustomer().getId());
            preparedStatement.setInt(4, customerVaccine.getVaccine().getId());

            preparedStatement.setInt(5, customerVaccine.getId());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM `vaccine`.`CustomerVaccine` WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            CustomerVaccine customerVaccine = getById(id);
            Vaccine vaccine = vaccineDao.getById(customerVaccine.getVaccine().getId());
            vaccine.setCount(vaccine.getCount() + 1);
            vaccineDao.edit(vaccine);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
