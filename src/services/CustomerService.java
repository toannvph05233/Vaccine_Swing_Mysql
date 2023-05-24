package services;

import dao.CustomerDao;
import dao.VaccineDao;
import entity.Customer;
import entity.Vaccine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * StudentFunc class
 *
 * @author viettuts.vn
 */
public class CustomerService {
    private List<Customer> listCustomers;
    private CustomerDao customerDao = new CustomerDao();
    private VaccineDao vaccineDao = new VaccineDao();

    public CustomerService() {
        this.listCustomers = customerDao.getAll();
    }


    public void add(Customer customer) {
        customerDao.create(customer);
        this.listCustomers = customerDao.getAll();
    }

    public void addVaccine(Vaccine vaccine) {
        vaccineDao.create(vaccine);
        this.listCustomers = customerDao.getAll();
    }

    public void editVaccine(Vaccine vaccine) {
        vaccineDao.edit(vaccine);
        this.listCustomers = customerDao.getAll();
    }

    public boolean deleteVaccine(int vaccineId) {
        boolean isDelete = vaccineDao.delete(vaccineId);
        this.listCustomers = customerDao.getAll();
        return isDelete;

    }

    public void edit(Customer customer) {
        customerDao.edit(customer);
        this.listCustomers = customerDao.getAll();
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     *
     * @param customer
     */
    public boolean delete(Customer customer) {
        boolean isDelete = customerDao.delete(customer.getId());
        this.listCustomers = customerDao.getAll();
        return isDelete;
    }

    public Customer findById(int id) {
        for (Customer c : listCustomers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> findAllByName(String name) {
        List<Customer> customers = new ArrayList<>();
        for (Customer c : listCustomers) {
            if (c.getName().contains(name)) {
                customers.add(c);
            }
        }
        return customers;
    }

    /**
     * sắp xếp danh sách student theo name theo tứ tự tăng dần
     */
    public void sortCustomerByName() {
        Collections.sort(listCustomers, new Comparator<Customer>() {
            public int compare(Customer customer1, Customer customer2) {
                return customer1.getName().compareTo(customer2.getName());
            }
        });
    }

    public List<Customer> getListStudents() {
        return listCustomers;
    }

    public void setListStudents(List<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }
}
