package com.swed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class AndmedDAO {
	
	public String getAndmed(){
		
		String andmed = "tere fail";
		Connection conn = null;
        
        try {
        	conn = DBConnection.getConnection();          
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from mesa.pl_measure_fact_prt where object_name = 'OBJECT_22065' and measure_fact_date = '2017-02-22' and measure_group_id = '49808'");
            
            rs.next();
            andmed = rs.getString(2);
      
//            while (rs.next())
//            {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(1));
//                
//            }
            rs.close();
            st.close();
        	
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
        return andmed;
    }
	

}

