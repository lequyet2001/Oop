package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionn {
    // Thay đổi các thông tin sau phù hợp với cấu hình MySQL của bạn
    private static final String DB_URL = "jdbc:mysql://localhost:3306/qlks";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Hàm để thiết lập kết nối
    public static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;

        try {
            // Bước 1: Đăng ký driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Bước 2: Kết nối với cơ sở dữ liệu
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    // Hàm main để kiểm tra kết nối
    public static void main(String[] args) {
        java.sql.Connection conn = getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công đến MySQL trên XAMPP!");
            // Thực hiện các tác vụ truy vấn hoặc thao tác với cơ sở dữ liệu ở đây
            try {
                conn.close(); // Đừng quên đóng kết nối sau khi hoàn tất công việc
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
