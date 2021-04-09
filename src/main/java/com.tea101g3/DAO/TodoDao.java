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

    private static final String INSERT_STMT = "INSERT INTO todolist (id, priority,orderby,title) VALUES (NEXTVAL('todo_id_seq'), ?, ?,?)";
    private static final String GET_ALL_STMT = "SELECT * FROM todolist";

    private static final String DELETE_STMT = "DELETE FROM todolist where orderby = ?";

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
        ResultSet rs = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setInt(1, newBean.getPriority());
            pstmt.setInt(2, newBean.getOrderBy());
            pstmt.setString(3, newBean.getTitle());

            pstmt.executeQuery();


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
