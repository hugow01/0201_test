package com.tea101g3.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tea101g3.entity.ListBean;

public class TodoDao {

    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost:5432/0201";
    String userid = "hr";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO todolist (id, priority,orderby,title) VALUES (?, ?, ?,?)";
    private static final String GET_ALL_STMT = "SELECT * FROM todolist";

    private static final String DELETE_STMT = "DELETE FROM todolist where id = ?";

    private static final String UPDATE = "UPDATE todolist set priority=?, orderby=?, title=? where orderby = ?";

    public List<ListBean> getAll() {
        List<ListBean> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while(rs.next()){
                ListBean bean = new ListBean();
                bean.setId(rs.getString("id"));
                bean.setPriority(rs.getInt("priority"));
                bean.setOrderBy(rs.getInt("orderby"));
                bean.setTitle(rs.getString("title"));
                list.add(bean);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        return list;
    }

    public void createOne(ListBean newBean) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, newBean.getId());
            pstmt.setInt(2, newBean.getPriority());
            pstmt.setInt(3, newBean.getOrderBy());
            pstmt.setString(4, newBean.getTitle());

            Boolean success = pstmt.execute();
            System.out.println(!success?"Create success":"something Wrong");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

    public void deleteOne(String srcId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE_STMT);
            pstmt.setString(1, srcId);

            int deleteCount = pstmt.executeUpdate();
            System.out.println("Deleted " + deleteCount + " rows");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }


}
