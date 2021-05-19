package com.revature.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class AdminStore implements AdminRepo {
	int checkAdminExist;
	int flag5;

	@Override
	public int checkAdmin(String userName) {
		// TODO Auto-generated method stub
		 checkAdminExist= adminMatchMethod(userName);
		return checkAdminExist;
	}
	private int adminMatchMethod(String userName) {
		// TODO Auto-generated method stub
		//System.out.println("Kisu print hois");
		 String myContainer=null;
			
			String sql= "SELECT * FROM admin_table WHERE admin_username= (?)";
			try(Connection conn= ConnectionFactory.getConnection()) {
				PreparedStatement ps=conn.prepareStatement(sql);
				//System.out.println("Vai asos ?");
				ps.setString(1, userName);
				ResultSet rs= ps.executeQuery();
				
				while(rs.next())
				{
					//System.out.println("Tui asos eikhane ?");
					myContainer= rs.getString("admin_username");
					flag5=1;
				}
				
			}catch(NullPointerException e)
			{
				//System.out.println("Ami catch block e ");
				flag5=0;
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return flag5;
	}
	

}
