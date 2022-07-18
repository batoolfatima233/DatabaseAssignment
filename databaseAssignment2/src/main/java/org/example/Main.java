package org.example;

import java.sql.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        Date date = new Date(0);
        String id = null;
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/assignment";
            con = DriverManager.getConnection(url, "root", "root");

            //INSERT INTO RESERVATIONS
            String query1 = "INSERT INTO reservations(id,check_in_date,check_out_date,status)VALUES(?,?,?,?)";
            PreparedStatement st1=con.prepareStatement(query1);
            id=UUID.randomUUID().toString();
            st1.setObject(1, id);
            st1.setDate(2,date.valueOf("2012-12-17"));
            st1.setDate(3,date.valueOf("2012-12-20"));
            st1.setString(4,"hold");
            int i1 = st1.executeUpdate();
            if( i1 ==1)
            {
                System.out.println("successfully added in reservations");
            }
            else
            {
                throw new Exception();
            }

            //INSERT INTO QUESTS
            String query2 = "INSERT INTO guests(id,reservation_id,first_name,last_name)VALUES(?,?,?,?)";
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setString(1,id);
            st2.setObject(2,UUID.randomUUID().toString());
            st2.setString(3,"Tehreem");
            st2.setString(4,"Fatima");
            int i2 = st2.executeUpdate();
            if( i2 ==1)
            {
                System.out.println("successfully added in quest");
            }
            else
            {
                throw new Exception();
            }

            //TRAVERSE QUERY
            String query3 = "Select * from reservations;";
            PreparedStatement st3 = con.prepareStatement(query3);

            ResultSet rs1 =  st3.executeQuery();

            while(rs1.next())
            {
                System.out.println(rs1.getObject(1) + " " + rs1.getDate(2) + " "+ rs1.getDate(3)+ " "+ rs1.getString(4));
            }

            //UPDATE QUERY
            String query4 = "UPDATE reservations \n SET status = ? \n where id = ?";
            PreparedStatement st4 = con.prepareStatement(query4);
            st4.setString(1,"checked");
            st4.setString(2,"b301fd13-91ff-4081-a4ea-5bee04ca7831");
            int i4 = st4.executeUpdate();

            if(i4==1)
            {
                System.out.println("updated successfully");
            }
            else
            {
                System.out.println("couldn't update database");
                throw new Exception();
            }


            //DELETE QUERY
            String query5 = "DELETE FROM reservations WHERE reservations.id = ?";
            PreparedStatement st5 = con.prepareStatement(query5);
            st5.setString(1,"b301fd13-91ff-4081-a4ea-5bee04ca7831");
            int i5 = st5.executeUpdate();

            if(i5==1)
            {
                System.out.println("deleted successfully");
            }
            else
            {
                System.out.println("couldn't delete database");
                throw new Exception();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        con.close();
}
}