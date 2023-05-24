package view;



import dao.VaccineDao;
import entity.Vaccine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;


public class VaccineView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private JButton addVaccineBtn;
    private JButton editVaccineBtn;
    private JButton deleteVaccineBtn;
    private JButton clearVaccineBtn;

    private JButton logoutBtn;

    private JScrollPane jScrollPaneVaccineTable;

    private JTable VaccineTable;

    private JLabel idLabel;
    private JLabel VaccineLabel;
    private JLabel PriceVaccineLabel;
    private JLabel QuantityVaccineLabel;
    private JLabel dateVaccineLabel;

    private JTextField idVaccineField;
    private JTextField VaccineField;
    private JTextField PriceVaccineField;
    private JTextField QuantityVaccineField;
    private JTextField dateVaccineField;


    private JLabel titleVaccineLabel;


    private String[] columnVaccine = new String[]{
            "Id", "Name", "Price", "Quantity", "Date"};

    private Object data = new Object[][]{};

    public VaccineView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addVaccineBtn = new JButton("Add");
        editVaccineBtn = new JButton("Edit");
        deleteVaccineBtn = new JButton("Delete");
        clearVaccineBtn = new JButton("Clear");
        logoutBtn = new JButton("Logout");


        jScrollPaneVaccineTable = new JScrollPane();
        VaccineTable = new JTable();

        // khởi tạo các label
        idLabel = new JLabel("Id");
        VaccineLabel = new JLabel("Name");
        PriceVaccineLabel = new JLabel("Price");
        QuantityVaccineLabel = new JLabel("Quantity");
        dateVaccineLabel = new JLabel("DateVaccine");


        titleVaccineLabel = new JLabel("Danh Sách Vaccine");
        Font font = new Font("Arial", Font.BOLD, 24);
        titleVaccineLabel.setFont(font);

        idVaccineField = new JTextField(15);
        idVaccineField.setEditable(false);
        VaccineField = new JTextField(15);

        PriceVaccineField = new JTextField(15);
        QuantityVaccineField = new JTextField(15);
        dateVaccineField = new JTextField(15);

        // cài đặt các cột và data cho bảng Vaccine
        VaccineTable.setModel(new DefaultTableModel((Object[][]) data, columnVaccine));
        jScrollPaneVaccineTable.setViewportView(VaccineTable);
        jScrollPaneVaccineTable.setPreferredSize(new Dimension(550, 200));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Vaccine
        JPanel panel = new JPanel();
        panel.setSize(1100, 1000);
        panel.setLayout(layout);
        panel.add(jScrollPaneVaccineTable);

        panel.add(addVaccineBtn);
        panel.add(editVaccineBtn);
        panel.add(deleteVaccineBtn);
        panel.add(clearVaccineBtn);


        panel.add(logoutBtn);

        panel.add(idLabel);
        panel.add(VaccineLabel);
        panel.add(QuantityVaccineLabel);
        panel.add(dateVaccineLabel);

        panel.add(PriceVaccineLabel);

        panel.add(titleVaccineLabel);

        panel.add(idVaccineField);
        panel.add(VaccineField);
        panel.add(QuantityVaccineField);

        panel.add(PriceVaccineField);
        panel.add(QuantityVaccineField);
        panel.add(dateVaccineField);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, VaccineLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, VaccineLabel, 90, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PriceVaccineLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PriceVaccineLabel, 120, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, QuantityVaccineLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, QuantityVaccineLabel, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateVaccineLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateVaccineLabel, 180, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, titleVaccineLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleVaccineLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, logoutBtn, 790, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, logoutBtn, 25, SpringLayout.NORTH, panel);


        layout.putConstraint(SpringLayout.WEST, idVaccineField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idVaccineField, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, VaccineField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, VaccineField, 90, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, QuantityVaccineField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, QuantityVaccineField, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PriceVaccineField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PriceVaccineField, 120, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, dateVaccineField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateVaccineField, 180, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneVaccineTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneVaccineTable, 60, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addVaccineBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addVaccineBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editVaccineBtn, 70, SpringLayout.WEST, addVaccineBtn);
        layout.putConstraint(SpringLayout.NORTH, editVaccineBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteVaccineBtn, 70, SpringLayout.WEST, editVaccineBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteVaccineBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, clearVaccineBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearVaccineBtn, 75, SpringLayout.WEST, deleteVaccineBtn);


        this.add(panel);
        this.pack();
        this.setTitle("Quản lý vaccine");
        this.setSize(950, 380);
        // disable Edit and Delete buttons
        editVaccineBtn.setEnabled(false);
        deleteVaccineBtn.setEnabled(false);
        addVaccineBtn.setEnabled(true);

        editVaccineBtn.setEnabled(false);
        deleteVaccineBtn.setEnabled(false);
        addVaccineBtn.setEnabled(true);

        setLocationRelativeTo(null);

    }


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list Vaccine vào bảng VaccineTable
     *
     * @param list
     */
    public void showListVaccines(List<Vaccine> list) {
        int size = list.size();
        Object[][] Vaccines = new Object[size][5];
        for (int i = 0; i < size; i++) {
            Vaccines[i][0] = list.get(i).getId();
            Vaccines[i][1] = list.get(i).getName();
            Vaccines[i][2] = list.get(i).getPrice();
            Vaccines[i][3] = list.get(i).getCount();
            Vaccines[i][4] = formatter.format(list.get(i).getVaccinDate());

        }
        VaccineTable.setModel(new DefaultTableModel(Vaccines, columnVaccine));
    }

    public void clearVaccineInfo() {
        idVaccineField.setText("");
        VaccineField.setText("");
        PriceVaccineField.setText("");
        QuantityVaccineField.setText("");
        dateVaccineField.setText("");

        editVaccineBtn.setEnabled(false);
        deleteVaccineBtn.setEnabled(false);
        addVaccineBtn.setEnabled(true);
    }


    public Vaccine getVaccineInfo() {
        try {
            String name = VaccineField.getText();
            int price = Integer.parseInt(PriceVaccineField.getText());
            int quantity = Integer.parseInt(QuantityVaccineField.getText());
            String date = dateVaccineField.getText();
            String id = idVaccineField.getText();
            if (id.equals("")) {
                return new Vaccine(0,name, price, quantity, formatter.parse(date));
            }
            int idP = Integer.parseInt(id);
            return new Vaccine(idP,name, price, quantity, formatter.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }


    public void fillVaccineFromSelectedRow() {
        VaccineDao vaccineDao = new VaccineDao();
        // lấy chỉ số của hàng được chọn
        int row = VaccineTable.getSelectedRow();
        if (row >= 0) {
            idVaccineField.setText(VaccineTable.getModel().getValueAt(row, 0).toString());
            VaccineField.setText(VaccineTable.getModel().getValueAt(row, 1).toString());
            PriceVaccineField.setText(VaccineTable.getModel().getValueAt(row, 2).toString());
            QuantityVaccineField.setText(VaccineTable.getModel().getValueAt(row, 3).toString());
            dateVaccineField.setText(VaccineTable.getModel().getValueAt(row, 4).toString());

            editVaccineBtn.setEnabled(true);
            deleteVaccineBtn.setEnabled(true);
            addVaccineBtn.setEnabled(false);
        }
    }


    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddVaccineListener(ActionListener listener) {
        addVaccineBtn.addActionListener(listener);
    }

    public void addEditVaccineListener(ActionListener listener) {
        editVaccineBtn.addActionListener(listener);
    }

    public void addDeleteVaccineListener(ActionListener listener) {
        deleteVaccineBtn.addActionListener(listener);
    }

    public void addClearVaccineListener(ActionListener listener) {
        clearVaccineBtn.addActionListener(listener);
    }

    public void backVaccineListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }


    public void addListVaccineSelectionListener(ListSelectionListener listener) {
        VaccineTable.getSelectionModel().addListSelectionListener(listener);
    }


}
