/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzadeliverycart;
import static java.lang.Math.round;
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AadarshSam
 */
public class PizzaDeliveryCart {

    // THIS IS THE MAIN PIZZA DELIVERY CART CLASS
    
     // JDBC driver name and database URL
           static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
           static final String DB_URL = "jdbc:mysql://localhost/Pizza_Delivery_Project";
              
           //  Database credentials
           static final String USER = "root";
           static final String PASS = "Aadarshsam123";
           
           public void createcon()
           {
           Connection conn = null;
           Statement stmt = null;
           try{
              //Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //Open a connection
              System.out.println("Connecting to database...");
               try {
                   conn = DriverManager.getConnection(DB_URL,USER,PASS);
               } catch (SQLException ex) {
                   Logger.getLogger(PizzaDeliveryCart.class.getName()).log(Level.SEVERE, null, ex);
               }
               

               
           }
           catch(Exception e)
           {
               System.out.println("error!");    
           }
           }
           
           
           public boolean validate_login(String username,String password) throws ClassNotFoundException, SQLException
           
           {
           try{    
               Connection conn=null;
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
       String u_id = null;
              PreparedStatement ps = conn.prepareStatement("Select Cust_id from Customers  where Cust_name=? and pass=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet r = ps.executeQuery();
        if(r.next())
        {
          u_id =r.getString(1);
        }
               System.out.println(u_id);
      
              
                PreparedStatement pst = conn.prepareStatement("Select * from Customers  where Cust_name=? and pass=?");
       pst.setString(1, username); 
       pst.setString(2, password);
           ResultSet rs = pst.executeQuery();                        
       if(rs.next())            
           return true;    
       else
           return false;            
           
           }
           catch(Exception e)
           {
               e.printStackTrace();
               return false;
           }
      
          
           
           }
           /*
           public void vieworders()
           {
           try{    
               Connection conn=null;
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
                   PreparedStatement pst = conn.prepareStatement("Select * from Orders  where Cust_name=? and pass=?");
           
           }
           */
           
           public void insertcust(String cust_id,String a, String b, int c, String d) throws ClassNotFoundException, SQLException
           {
               
           try{    
               
               
               Connection conn=null;
              //STEP 2: Register JDBC driver
              
               Class.forName("com.mysql.jdbc.Driver");
             // String u = UUID.randomUUID().toString().substring(0, 5);
              // cust_id = u;
              String pay_id=UUID.randomUUID().toString().substring(0, 4);
              
              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              Statement stat =conn.createStatement();
                
              stat.executeUpdate("insert into Customers values('"+cust_id+"','"+a+"','"+c+"','"+pay_id+"','"+b+"','"+d+"')"); 
           
           
           }
           catch(ClassNotFoundException | SQLException q)
           {
              q.printStackTrace();
              
           }
           
           }
           public void insertorder(int total_pri,String u_id,String order_id)
           {
              
           try{    
               
               Connection conn=null;
              //STEP 2: Register JDBC driver
              
               Class.forName("com.mysql.jdbc.Driver");
                  String e = UUID.randomUUID().toString().substring(0, 5);
           String emp_id = e;
           
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              
              Statement stat =conn.createStatement();
              
              /*
              PreparedStatement ps = conn.prepareStatement("select Cust_id from Customers where Orders.Cust_id=Customers.Cust_id");
           //stat.executeQuery("select Cust_id from Customers where Orders.Cust_id=Customers.Cust_id");
           String my_cust_id = null;
             ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                my_cust_id=rs.getString(1);
            }
              
            */
            stat.executeUpdate("insert into Orders values('"+order_id+"','"+total_pri+"','"+u_id+"','"+emp_id+"')");
           
              
     
           } 
           
           catch(ClassNotFoundException | SQLException q)
           {
              q.printStackTrace();
              
           }
               
               
               
           }
           
           public void delorder()
           {
               
           }
           
           public static void main(String[] args) {
               
               
           Connection conn = null;
           Statement stmt = null;
           try{
             //STEP 2: Register JDBC driver
             Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);

              //STEP 4: Execute a query
              System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql;
           //   sql = "SELECT * FROM Customers";
              sql = "SELECT Addr_id, place, zip FROM Address";
              ResultSet rs = stmt.executeQuery(sql);

              //STEP 5: Extract data from result set
              while(rs.next()){
                 //Retrieve by column name
                 String addr_id  = rs.getString("Addr_id");
                 int zip = rs.getInt("zip");
                 String place = rs.getString("place");
                 //String last = rs.getString("last");

                 //Display values
                 System.out.println("Address: " + addr_id);
                 System.out.println(" place: " + place);
                 System.out.println(", ZIP: " + zip);
                 //System.out.println(", Last: " + last);
              }
              //STEP 6: Clean-up environment
              rs.close();
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }//end try
           System.out.println("Goodbye!");
        }//end main
        }//end FirstExample

