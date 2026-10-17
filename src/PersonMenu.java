import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PersonMenu extends JFrame{
    // Swing GUI Components
    private JTextField textNama;
    private JTextField textId;
    private JTextField textTahunLahir;
    private JButton deleteButton;
    private JButton cancelButton;
    private JButton addButton;
    private JTable personTable;
    private JComboBox CBKategori;
    private JPanel mainPane;
    private JComboBox CBWarna;

    // Table and List attributes
    private Database database;
    private int selectIdx;

    // constructor
    public PersonMenu(){
        database = new Database(); // database instantiation

        personTable.setModel(setTable()); // init table

        selectIdx = -1; // Unselect index

        // JComboBox initiation
        String[] listKategori = {"", "Crypto", "Forex", "Bitcoin"};
        CBKategori.setModel(new DefaultComboBoxModel<>(listKategori));
        String[] listWarna = {"", "Hijau", "Merah", "Biru", "Kuning", "Ungu"};
        CBWarna.setModel(new DefaultComboBoxModel<>(listWarna));

        deleteButton.setVisible(false); // hide delete button

        // addButton action declaration
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                insertUpdateData();
            }
        });

        // cancelButton action declaration
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                resetForm();
            }
        });

        // deleteButton action declaration
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                deleteData();
            }
        });

        // Adding mouse listener for selecting table data
        personTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                selectIdx = personTable.getSelectedRow(); // set selected index
                textId.setEditable(false);

                // fetch data from table
                String curId = personTable.getModel().getValueAt(selectIdx, 0).toString();
                String curNama = personTable.getModel().getValueAt(selectIdx, 1).toString();
                String curTahun = personTable.getModel().getValueAt(selectIdx, 2).toString();
                String curKategori = personTable.getModel().getValueAt(selectIdx, 3).toString();
                String curWarna = personTable.getModel().getValueAt(selectIdx, 4).toString();

                // set text field
                textId.setText(curId);
                textNama.setText(curNama);
                textTahunLahir.setText(curTahun);
                CBKategori.setSelectedItem(curKategori);
                CBWarna.setSelectedItem(curWarna);

                // change add to update and show delete button
                addButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    // Insert or update data method
    public void insertUpdateData(){
        String id = textId.getText();
        String nama = textNama.getText();
        String kategori = CBKategori.getSelectedItem().toString();
        String warna = CBWarna.getSelectedItem().toString();
        try{ // try catch to handle tahunLahir is not integer
            int tahunLahir = Integer.parseInt(textTahunLahir.getText());

            // check if each of form data is still empty
            if(id.equals("") || nama.equals("") || kategori.equals("") || warna.equals("")){
                JOptionPane.showMessageDialog(null, "Semua data perlu diisi!");
            }else {
                if(selectIdx != -1){ // if table data is selected then update data
                    String updateQuery = "UPDATE trader SET nama = '" + nama + "', tahunLahir = '" + tahunLahir + "', kategori = '" + kategori + "', warnaMcLaren = '" + warna + "' WHERE id = '" + id + "';";
                    database.ManipulateData(updateQuery);
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                }else{ // else add data
                    String insertQuery = "INSERT INTO trader VALUES('" + id + "','" + nama + "','" + tahunLahir + "','" + kategori + "','" + warna + "');";
                    database.ManipulateData(insertQuery);
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                }
                personTable.setModel(setTable());
                resetForm();
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus angka!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // delete data method
    public void deleteData(){
        // create confirmation form before deleting
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(yesOrNo == JOptionPane.YES_OPTION){
            String deleteQuery = "DELETE from trader WHERE id = '" + personTable.getModel().getValueAt(selectIdx, 0).toString() + "';";
            database.ManipulateData(deleteQuery);
            resetForm();
            personTable.setModel(setTable());
        }
    }

    // resetting the form method
    public void resetForm(){
        // empty all textfield
        textId.setText("");
        textNama.setText("");
        textTahunLahir.setText("");
        CBKategori.setSelectedItem("");
        CBWarna.setSelectedItem("");

        // reset form data
        selectIdx = -1;
        textId.setEditable(true);
        deleteButton.setVisible(false);
        addButton.setText("Add");
    }

    // setTable method
    public final DefaultTableModel setTable(){
        Object[] tHead = {"ID", "Nama", "Tahun Lahir", "Kategori", "WarnaMcLaren"};
        DefaultTableModel ret = new DefaultTableModel(null, tHead);

        try{
            ResultSet resultSet = database.Fetch("SELECT * from trader"); // fetch data from database
            while(resultSet.next()){ // fill the GUI table
                Object[] row = new Object[5];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getString("tahunLahir");
                row[3] = resultSet.getString("kategori");
                row[4] = resultSet.getString("warnaMcLaren");
                ret.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public static void main(String[] args){
        PersonMenu menu = new PersonMenu(); // menu instantiation
        menu.setSize(700,600); // menu set size
        menu.setContentPane(menu.mainPane); // set content menu
        menu.getContentPane().setBackground(Color.WHITE); // set background menu
        menu.setVisible(true); // set visibility menu
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
