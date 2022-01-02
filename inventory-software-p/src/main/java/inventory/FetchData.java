/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author my_cheap_mac
 */
public class FetchData {
    public void getData(){
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "inventory");
            
            Statement st = conn.createStatement();
            
            String query = "select * from Products";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String sl_no = String.valueOf(rs.getInt("serial_number"));
                String name = rs.getString("name");
                String price = rs.getString("price");
                String date = String.valueOf(rs.getLong("entry_date"));
                String quantity = rs.getString("quantity");
                String size = rs.getString("category");
                
                System.out.println(date);
                
                String[]tbData = {sl_no, name, price, date, quantity, size};
                DefaultTableModel tbModel = (DefaultTableModel)product_table.getModel();
                tbModel.addRow(tbData);
            }

            
            
            st.close();
            conn.close();
                  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private javax.swing.JTable jTable1;
}
