import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SQLC {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/JavaEx?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "";
	
	public static void DoOrder(ArrayList Orders,int tableNo) {
		String sql = "Insert into orders (o_table, o_name, o_count,o_price, o_date) values ";
		for(int i = 0; i<Orders.size(); i++) {
			sql += "('"+tableNo+"','" + Orders.get(i).toString().split(":")[0] +"','"+Orders.get(i).toString().split(":")[1]+"','"+Orders.get(i).toString().split(":")[2]+"',now())";
			if(i < Orders.size() - 1) {
				sql+= ",";
			}
		}
			try {
			    Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, user, password);
			    Statement stmt = conn.createStatement();
			    System.out.println(sql);
			    stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();			  
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
			}		
	}
	public static void DelMenu(String m_name) {
		String sql =  "delete from menus where m_name='"+m_name+"'";
		System.out.println(sql);
			try {
			    Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, user, password);
			    Statement  stmt = conn.createStatement();
			    stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();			  
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		}
	}
	public static void DoPayment(String tableNo) {
		String sql =  "Update orders set o_state = 'Y' where o_table = '"+tableNo+"'";
		System.out.println(sql);
			try {
			    Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, user, password);
			    Statement  stmt = conn.createStatement();
			    stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
				JOptionPane.showMessageDialog(null,"성공적으로 결제 완료했습니다.","결제",JOptionPane.PLAIN_MESSAGE);
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		}
	}
	public static void ModiMenu(String m_category, String m_name,String m_price) {
		String sql =  "Update menus set m_category = '"+m_category+"',m_price = '"+m_price+"' where m_name = '"+m_name+"'";
		System.out.println(sql);
			try {
			    Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, user, password);
			    Statement  stmt = conn.createStatement();
			    stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();			  
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		}
	}
	public static void AddMenu(String m_category, String m_name,String m_price) {
		String sql =  "insert into menus (m_category, m_name, m_price) values ('"+m_category+"','"+m_name+"','"+m_price+"')";
		System.out.println(sql);
			try {
			    Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, user, password);
			    Statement  stmt = conn.createStatement();
			    stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();			  
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		}
	}
	public static ArrayList GetOrders(int tableNo) throws Exception{
		ArrayList Menus = new ArrayList();
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, user, password);
		    Statement stmt = conn.createStatement();

		    String sql = "select * from orders where o_table='"+tableNo+"' and o_state ='N' order by o_no";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	Menus.add(rs.getString("o_name")+":"+rs.getString("o_price")+":"+rs.getString("o_count"));
		    	System.out.println();
		    }
		    
			rs.close();
			conn.close();
			stmt.close();
			
		    return Menus;

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
		}
	}
	public static ArrayList GetMenusAll() throws Exception{
		ArrayList Menus = new ArrayList();
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, user, password);
		    Statement stmt = conn.createStatement();

		    String sql = "select * from menus order by m_category";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	Menus.add(rs.getString("m_name")+":"+rs.getString("m_price")+":"+rs.getString("m_category"));
		    	System.out.println();
		    }
		    
			rs.close();
			conn.close();
			stmt.close();
			
		    return Menus;

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
		}
	}
	public static ArrayList GetMenus(String Category) throws Exception{
		ArrayList Menus = new ArrayList();
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, user, password);
		    Statement stmt = conn.createStatement();

		    String sql = "select * from menus where m_category = '"+Category+"'";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	Menus.add(rs.getString("m_name")+":"+rs.getString("m_price"));
		    	System.out.println();
		    }
		    
			rs.close();
			conn.close();
			stmt.close();
			
		    return Menus;

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
		}
	}
	
	public static ArrayList GetCategories() throws Exception{
		ArrayList Categories = new ArrayList();
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, user, password);
		    Statement stmt = conn.createStatement();

		    String sql = "select DISTINCT m_category from menus";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	Categories.add(rs.getString("m_category"));
		    }
		    
			rs.close();
			conn.close();
			stmt.close();
			
		    return Categories;

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
		}
	}
}
