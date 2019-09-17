package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScollableTest {
	private static final String SENSITIVE_STUDENT="select sno,sname,sadd from Student";
	
	

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//create PreparedStatement object with tpe,mode values
			if(con!=null)
			
				ps=con.prepareStatement(SENSITIVE_STUDENT,ResultSet.TYPE_SCROLL_SENSITIVE
						                                                                   , ResultSet.CONCUR_READ_ONLY);
				//create ResultSet object
				if(ps!=null)
				{
					rs=ps.executeQuery(SENSITIVE_STUDENT);
					//display records(top-bottom)
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
							
						}//while
						System.out.println("Bottom - Top");
						while(rs.previous()) {
							System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
							
						}//while
						System.out.println("-----------------------");
						//first record
						rs.first();
						System.out.println(rs.getRow()+"--------->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						//last record
						rs.last();
						System.out.println(rs.getRow()+"--------->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						//3rd record from top
						rs.absolute(3);
						System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						rs.absolute(-3);
						System.out.println(rs.getRow()+"--------->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						//relative
						rs.relative(2);
						System.out.println(rs.getRow()+"----->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						//relative
						rs.relative(-4);
						System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
						
						
					}//if
				}
					
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
				catch(ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				finally {
					//close JDBC object
					
					try {
						if(rs!=null)
							rs.close();
					}
					catch(SQLException se) {
						se.printStackTrace();
					}
					try {
						if(ps!=null)
							ps.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
					try {
						if(con!=null) 
							con.close();
						}
						catch(SQLException se) {
							se.printStackTrace();
						}
					}//finally
					
				
				}//main

			}//class
