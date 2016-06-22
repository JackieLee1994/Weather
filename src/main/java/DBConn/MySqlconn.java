package DBConn;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.singleRec;

public class MySqlconn {

	public List<singleRec> select() throws Exception {
		List<singleRec> result = new ArrayList<singleRec>();
		Connection conn = null;

		String sql;

		String url = "jdbc:mysql://localhost:3306/highmath?"

		+ "user=root&password=1188&useUnicode=true&characterEncoding=UTF8";

		try {
			Class.forName("com.mysql.jdbc.Driver");// 閿熸枻鎷锋�侀敓鏂ゆ嫹閿熸枻鎷穖ysql閿熸枻鎷烽敓鏂ゆ嫹


			conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			
			Date dt=new Date();
		    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		    String time=matter1.format(dt);
		     if(time.substring(5, 6).contains("0")){
		    	 time=time.substring(0, 5)+time.substring(6);
		     }
			sql = "select * from weather where sj LIKE '"+time+"%'";
			ResultSet rs = stmt.executeQuery(sql);// executeQuery閿熺粨杩旈敓鎴枻鎷烽敓鏂ゆ嫹鍕熼敓鏂ゆ嫹甯岄敓鏂ゆ嫹閿熸枻鎷疯殟绁蜂箿閿熻锟�
			while (rs.next()) {
				singleRec sr =null;
				sr = new singleRec();
				sr.setId(rs.getInt(1));
				sr.setWd(rs.getDouble(2));
				sr.setShidu(rs.getString(3));
				sr.setY1(rs.getDouble(4));
				sr.setFx(rs.getDouble(5));
				sr.setFxname(rs.getString(6));
				sr.setFxcode(rs.getString(7));
				sr.setFsname(rs.getString(8));
				sr.setFs(rs.getInt(9));
				sr.setSj(rs.getString(10));
				result.add(sr);
			}

		} catch (SQLException e) {

			System.out.println("数据库连接失败");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return result;
	}

	public void insert(singleRec data) throws Exception {
		String param = data.getWd() + "," + data.getShidu() + "," + data.getY1() + "," + data.getFx() + ","
				+ data.getFxname() + "," + data.getFxcode() + "," + data.getFsname() + "," + data.getFs() + ","
				+ data.getSj();
		System.out.println(param);
		Connection conn = null;

		String sql;
		String url = "jdbc:mysql://localhost:3306/highmath?"

		+ "user=root&password=1188&useUnicode=true&characterEncoding=UTF8&useSSL=true";
		try {
			Class.forName("com.mysql.jdbc.Driver");// 閿熸枻鎷锋�侀敓鏂ゆ嫹閿熸枻鎷穖ysql閿熸枻鎷烽敓鏂ゆ嫹
			System.out.println("閿熺即鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹MySQL閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			sql = "insert into weather(wd,shidu,y1,fx,fxname,fxcode,fsname,fs,sj,cur_time) " + "values(" + data.getWd()
					+ ",'" + data.getShidu() + "'," + data.getY1() + "," + data.getFx() + ",'" + data.getFxname()
					+ "','" + data.getFxcode() + "','" + data.getFsname() + "'," + data.getFs() + ",'" + data.getSj()
					+ "',now())";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("MySQL閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

}
