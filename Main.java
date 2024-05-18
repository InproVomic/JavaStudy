//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//
//public class Main {
//    public static void main(String[] args) throws SQLException {
//        Scanner scan  = new Scanner(System.in);
//        int id = scan.nextInt();
//        String name = scan.next();
//        //1. 先创建DataSource
//        DataSource dataSource = new MysqlDataSource();
//        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
//        ((MysqlDataSource)dataSource).setUser("root");
//        ((MysqlDataSource)dataSource).setPassword("15016246620");
//
//        //2. 建立与数据库之间的连接
//        Connection connection = dataSource.getConnection();
//
//        //3. 编写sql语句
//        String sql = "insert into student values (?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setInt(1,id);
//        statement.setString(2,name);
//
//
//        //4. 接收返回值（表示影响的行数）
//        int n = statement.executeUpdate();
//        System.out.println(n);
//
//        //5. 释放资源，关闭连接。
//        statement.close();
//        connection.close();
//    }
//}
