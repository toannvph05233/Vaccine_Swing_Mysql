package controller;

import dao.CustomerVaccineDao;
import entity.Customer;
import entity.CustomerVaccine;
import services.CustomerService;
import view.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class CustomerController {
    private CustomerService studentDao;
    private CustomerView customerView;
    private CustomerVaccineDao customerVaccineDao;

    public CustomerController(CustomerView view) {
        this.customerView = view;
        studentDao = new CustomerService();
        customerVaccineDao = new CustomerVaccineDao();

        view.addAddStudentListener(new AddStudentListener());
        view.searchCustomerListener(new searchCustomerListener());
        view.showAllCustomerListener(new showAllCustomerListener());
        view.addAddVaccineListener(new AddVaccineListener());

        view.addEdiStudentListener(new EditStudentListener());
        view.addEdiVaccineListener(new EditVaccineListener());

        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addDeleteVaccineListener(new DeleteVaccineListener());

        view.addClearListener(new ClearStudentListener());
        view.addClearVaccineListener(new ClearVaccineListener());

        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
        view.addListVaccineSelectionListener(new ListVaccineSelectionListener());
        view.addShowVaccineByComboBoxListener(new ShowVaccineByComboBoxListener());
    }

    public void showStudentView() {
        List<Customer> customerList = studentDao.getListStudents();
        customerView.setVisible(true);
        customerView.showListCustomers(customerList);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.add(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Thêm thành công!");
            }
        }
    }

    class searchCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = customerView.getNameSearch();
            if (name != null) {
                customerView.showListCustomers(studentDao.findAllByName(name));
            } else {
                customerView.showMessage("chưa nhập name search!");
            }
        }
    }

    class showAllCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.showListCustomers(studentDao.getListStudents());

        }
    }

    class AddVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CustomerVaccine customerVaccine = customerView.getCustomerVaccineInfo();
            if (customerVaccine != null) {
                customerVaccineDao.create(customerVaccine);
                customerView.showListCustomerVaccine(customerVaccineDao.getAllByIdCustomer(customerVaccine.getCustomer().getId()));
                customerView.showMessage("Thêm Vaccine thành công!");
                customerView.showComboBox();
            } else {
                customerView.showMessage("Lỗi vì chưa Chọn Customer hoặc vaccine hoặc sai định dạng ngày VD: 22/2/2023");

            }
        }
    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.edit(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class EditVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CustomerVaccine customerVaccine = customerView.getCustomerVaccineInfo();
            if (customerVaccine != null) {
                if (customerVaccine.getId() != 0) {
                    customerVaccineDao.edit(customerVaccine);
                    customerView.showListCustomerVaccine(customerVaccineDao.getAllByIdCustomer(customerVaccine.getCustomer().getId()));
                    customerView.showMessage("Sửa Vaccine thành công!");
                    customerView.showComboBox();
                } else {
                    customerView.showMessage("Không thể Sửa vì chưa Chọn CustomerVaccine");

                }
            } else {
                customerView.showMessage("Lỗi vì chưa Chọn Customer hoặc vaccine hoặc sai định dạng ngày VD: 22/2/2023");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.delete(customer);
                customerView.clearStudentInfo();
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Xóa thành công!");
            }
        }
    }

    class DeleteVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CustomerVaccine customerVaccine = customerView.getCustomerVaccineInfo();
            if (customerVaccine != null) {
                customerVaccineDao.delete(customerVaccine.getId());
                customerView.showListCustomerVaccine(customerVaccineDao.getAllByIdCustomer(customerVaccine.getCustomer().getId()));
                customerView.showMessage("Xóa Vaccine thành công!");
                customerView.showComboBox();
            } else {
                customerView.showMessage("Không thể Xóa vì chưa Chọn CustomerVaccine");

            }
        }
    }


    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearStudentInfo();
        }
    }

    class ClearVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearVaccineInfo();
        }
    }


    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortCustomerByName();
            customerView.showListCustomers(studentDao.getListStudents());
        }
    }

    class ShowVaccineByComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.showVaccineByComboBox();
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillCustomerFromSelectedRow();
        }
    }

    class ListVaccineSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillVaccineFromSelectedRow();
        }
    }
}