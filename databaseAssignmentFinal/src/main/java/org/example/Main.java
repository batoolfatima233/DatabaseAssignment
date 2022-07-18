package org.example;
import java.util.UUID;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        Date date = new Date(0);
        String id= null;
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/assignment";
            con =DriverManager.getConnection(url,"root","root");

            try
            {
                con.setAutoCommit(false);
                String query = "INSERT INTO reservations(id,check_in_date,check_out_date,status)VALUES(?,?,?,?)";
                PreparedStatement st=con.prepareStatement(query);
                st.setObject(1,UUID.randomUUID().toString());
                st.setDate(2,date.valueOf("2022-12-17"));
                st.setDate(3,date.valueOf("2022-12-20"));
                st.setString(4,"hold");
                int i = st.executeUpdate();
                if( i ==1)
                {
                    System.out.println("successfully added in reservations");
                }
                else
                {
                    throw new Exception();
                }


                String subquery = "SELECT id FROM reservations WHERE reservations.status = ? AND reservations.check_in_date = ?";
                PreparedStatement subst = con.prepareStatement(subquery);
                subst.setString(1,"hold");
                subst.setDate(2,date.valueOf("2022-12-17"));
                ResultSet rs = subst.executeQuery();

                if(rs != null)
                {
                    while(rs.next())
                    {
                        id = rs.getString("id");
                    }
                }
                else
                {
                    throw new Exception();
                }


                String query2 = "INSERT INTO guests(id,reservation_id,first_name,last_name)VALUES(?,?,?,?)";
                PreparedStatement st2 = con.prepareStatement(query2);
                st2.setString(1,id);
                st2.setObject(2,UUID.randomUUID().toString());
                st2.setString(3,"Tasmiyaa");
                st2.setString(4,"Zaidi");
                int i2 = st2.executeUpdate();


                st2.setObject(2,UUID.randomUUID().toString());
                st2.setString(3,"Laiba");
                st2.setString(4,"Aftab");
                i2 = st2.executeUpdate();


                st2.setObject(2,UUID.randomUUID().toString());
                st2.setString(3,"Fatima");
                st2.setString(4,"Shirazi");
                i2 = st2.executeUpdate();

                if( i2 ==1)
                {
                    System.out.println("successfully added in quest");
                }
                else
                {
                    throw new Exception();
                }


                con.commit();
            }
            catch(Exception e)
            {
                con.rollback();
                System.out.println("error occurred so transaction rollback");
                e.printStackTrace();
            }

        }
        catch(Exception e)
        {
            System.out.println("couldn't make connection");
            e.printStackTrace();
        }
        con.close();
    }
}



















//String query = "INSERT INTO reservations(id,check_in_date,check_out_date,status)VALUES('"+UUID.randomUUID()+"','2022-07-07'"+",'2022-07-10'"+",'checkedIn')";
//            String query = "INSERT INTO reservations(id,check_in_date,check_out_date,status)VALUES(?,?,?,?)";
//            PreparedStatement st=con.prepareStatement(query);
//
//            st.setObject(1,UUID.randomUUID().toString());
//            st.setDate(2,date.valueOf("2022-12-17"));
//            st.setDate(3,date.valueOf("2022-12-20"));
//            st.setString(4,"hold");
//            int rs = st.executeUpdate();

//String query2 = "INSERT INTO guests(id_fk,reservation_id,first_name,last_name)VALUES('00b5ca29-608b-4eee-bb53-0a6a8f0afd3b','2','batool','fatima')";
//
//            Statement st=con.createStatement();
//            int rs = st.executeUpdate(query2);
//
//            if(rs==1 ) //|| rs2 ==1
//            {
//                System.out.println("successfully entered");
//            }
//
//            else
//            {
//                System.out.println("couldn't entered data");
//            }

//String query4 = "UPDATE reservations \n SET status = ? \n where id = ?";
//            String query4 = "DELETE FROM reservations WHERE reservations.id = ?";
//            PreparedStatement st4 = con.prepareStatement(query4);
//            //st4.setString(1,"reservations");
//            st4.setString(1,"d636e950-027e-11ed-9d17-fce99c51c0ce");
//            int i = st4.executeUpdate();
//
//            if(i==1)
//            {
//                System.out.println("updated successfully");
//            }
//            else
//            {
//                System.out.println("couldn't update database");
//            }
//
//
//            String query3 = "Select * from reservations;";
//            PreparedStatement st2 = con.prepareStatement(query3);
//
//           ResultSet rs2 =  st2.executeQuery();
//
//            while(rs2.next())
//            {
//                System.out.println(rs2.getObject(1) + " " + rs2.getDate(2) + " "+ rs2.getDate(3)+ " "+ rs2.getString(4));
//            }