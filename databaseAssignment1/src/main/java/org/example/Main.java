package org.example;

import java.sql.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        Date date = new Date(0);
        String id = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/assignment";
            con = DriverManager.getConnection(url, "root", "root");

            Statement st = con.createStatement();
            String uuid = UUID.randomUUID().toString();
            String query1 = "INSERT INTO reservations(id,check_in_date,check_out_date,status)VALUES('"+ uuid+"','2001-07-07'"+",'2001-07-10'"+",'checkedIn')";
            int rs = st.executeUpdate(query1);

            if(rs==1 ) 
            {
                System.out.println("successfully entered in reservation");

            }


           else
            {
                System.out.println("couldn't entered data");
            }

            String query2 = "INSERT INTO guests(id,reservation_id,first_name,last_name)VALUES('"+uuid+"','"+UUID.randomUUID()+"','lal','chand')";

            Statement st2=con.createStatement();
            int rs2 = st2.executeUpdate(query2);

            if(rs2==1 ) //|| rs2 ==1
            {
                System.out.println("successfully entered in guests");
            }

            else
            {
                System.out.println("couldn't entered data");
            }



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        con.close();
    }
}