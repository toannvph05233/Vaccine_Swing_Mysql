package controller;

import dao.VaccineDao;
import entity.Vaccine;
import view.LoginView;
import view.VaccineView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VaccineController {
    private VaccineDao vaccineDao;
    private VaccineView VaccineView;
    private LoginView loginView;

    public VaccineController(VaccineView VaccineView, LoginView loginView) {
        this.loginView =loginView;
        this.vaccineDao = new VaccineDao();
        this.VaccineView = VaccineView;
        VaccineView.addListVaccineSelectionListener(new ListVaccineSelectionListener());
        VaccineView.addAddVaccineListener(new AddVaccineListener());
        VaccineView.addClearVaccineListener(new ClearVaccineListener());
        VaccineView.addEditVaccineListener(new EditVaccineListener());
        VaccineView.addDeleteVaccineListener(new DeleteVaccineListener());
        VaccineView.backVaccineListener(new BackVaccineListener());
        VaccineView.showListVaccines(vaccineDao.getAll());

    }


    class ListVaccineSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            VaccineView.fillVaccineFromSelectedRow();
        }
    }


    class AddVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine Vaccine = VaccineView.getVaccineInfo();
            if (Vaccine != null) {
                vaccineDao.create(Vaccine);
                VaccineView.showListVaccines(vaccineDao.getAll());
                VaccineView.showMessage("Thêm thành công!");
            }
        }
    }


    class EditVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine Vaccine = VaccineView.getVaccineInfo();
            if (Vaccine != null) {
                vaccineDao.edit(Vaccine);
                VaccineView.showListVaccines(vaccineDao.getAll());
                VaccineView.showMessage("Sửa thành công!");
            }
        }
    }


    class ClearVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            VaccineView.clearVaccineInfo();
        }
    }

    class BackVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            VaccineView.setVisible(false);
            loginView.setVisible(true);
        }
    }


    class DeleteVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine Vaccine = VaccineView.getVaccineInfo();
            if (Vaccine != null) {
                vaccineDao.delete(Vaccine.getId());
                VaccineView.showListVaccines(vaccineDao.getAll());
                VaccineView.showMessage("Xóa thành công!");
            }
        }
    }
}
