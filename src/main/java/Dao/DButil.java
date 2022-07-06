package Dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author 712f
 */
public class DButil {
    static final String URL = "jdbc:mysql://localhost:3306/dizhu?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    static final String USER = "root";
    static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int update(String sql, Object...params) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            System.out.println(params.length);
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pst, null);
        }
        return 0;
    }


    /**
     * 查询数据库
     * @param sql
     * @return 返回一个Vector<Object>，每个Object是一个Object[]，Object[]的长度是查询的字段数
     */
    public static Vector<Object> query(String sql) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector<Object> v = new Vector<>();

            while (rs.next()) {
                Vector<Object> v1 = new Vector<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    v1.add(rs.getObject(i));
                }
                v.add(v1);
            }
            return v;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pst, rs);
        }

        return null;
    }

    /**
     * 不会用 qwq~
     * @author teacher
     * @param sql
     * @param clazz
     * @param args
     * @return
     * @param <T>
     */
    public static <T> List<T> query(String sql, Class<T> clazz, Object ...args){

        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet resultSet=null;
        List<T> rList = new ArrayList<>();
        try {
            conn=getConnection();
            pst = conn.prepareStatement(sql);

            if (args!=null){
                for (int i = 0; i < args.length; i++) {
                    pst.setObject(i+1,args[i]);
                }
            }
            // 4。执行sql,得到结果集
            resultSet = pst.executeQuery();
            //从结果集中得到元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
//            从结果集中得到有多少列
            int count = metaData.getColumnCount();
            while (resultSet.next()){
                //从类对象中实例一个对象
                T t = clazz.newInstance();
                for (int i = 0; i < count; i++) {
//                    获取列的值
                    Object object = resultSet.getObject(i+1);
//                    获取列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);

//                    根据别名获取类的成员属性（要求别名和属性名要一致）
                    Field field = clazz.getDeclaredField(columnLabel);
//                    设置该列能被设置（不然私有不能设值）
                    field.setAccessible(true);
                    field.set(t,object);
                }
                rList.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn,pst,resultSet);
        }
        return rList;
    }

    public static void main(String[] args) {
    }

}
