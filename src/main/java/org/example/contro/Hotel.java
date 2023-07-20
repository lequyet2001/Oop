package org.example.contro;


import org.example.entity.PremiumRoom;
import org.example.entity.Room;
import org.example.entity.StandardRoom;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel extends abstractHotel {
    private  List<Room>  rooms;
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
    public Hotel(){
        rooms= getRoomsFromDatabase();
    }
    public List<Room> getRoomsFromDatabase() {
        List<Room> rooms = new ArrayList<>();

        Connection conn = null ;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Bước 1: Kết nối với cơ sở dữ liệu

            conn=getConnection();

            // Bước 2: Tạo câu truy vấn SQL
            String query = "SELECT * FROM room;";

            // Bước 3: Tạo một đối tượng Statement để thực thi câu truy vấn
            stmt = conn.createStatement();

            // Bước 4: Thực thi câu truy vấn và nhận kết quả
            rs = stmt.executeQuery(query);

            // Bước 5: Xử lý kết quả và tạo các đối tượng Room từ kết quả
            while (rs.next()) {
                int roomNumber = rs.getInt("Number");
                double area = rs.getDouble("Area");
                int floor = rs.getInt("Floor");
                String status = rs.getString("Status");
                String roomType=rs.getString("roomType");
                Room room = new Room(roomNumber, area, floor, status,roomType);
                if(Objects.equals(roomType, "Standard")){
                    Room  x = new StandardRoom(roomNumber,area,floor,status,roomType);
                    System.out.println(room.getRoomType());
                    rooms.add(x);
                }
                else if (Objects.equals(roomType, "Premium")) {
                    Room  x=new PremiumRoom(roomNumber,area,floor,status,roomType);
                    System.out.println(room.getRoomType());
                    rooms.add(x);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Bước 6: Đóng kết nối, các tài nguyên và giải phóng bộ nhớ
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return rooms;
    }


    // Thêm sửa xóa
    public boolean addRoom(Room room) {
        // Kiểm tra nếu phòng đã tồn tại trong danh sách rooms
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                return false;
            }
        }

        // Thực hiện thêm phòng vào cơ sở dữ liệu
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Bước 1: Kết nối với cơ sở dữ liệu
            conn = getConnection();

            // Bước 2: Tạo câu truy vấn SQL để chèn dữ liệu phòng mới
            String query = "INSERT INTO room (Number, Area, Floor, Status, roomType) VALUES (?, ?, ?, ?, ?)";

            // Bước 3: Tạo đối tượng PreparedStatement để thực thi câu truy vấn
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, room.getNumber());
            pstmt.setDouble(2, room.getArea());
            pstmt.setInt(3, room.getFloor());
            pstmt.setString(4, room.getStatus());
            pstmt.setString(5, room.getRoomType());

            // Bước 4: Thực thi câu truy vấn để chèn dữ liệu vào cơ sở dữ liệu
            int rowsAffected = pstmt.executeUpdate();

            // Bước 5: Kiểm tra kết quả để xác định xem phòng đã được thêm thành công hay không
            if (rowsAffected > 0) {
                // Nếu thêm thành công, thêm phòng vào danh sách rooms

                    rooms.add(room);


                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Bước 6: Đóng kết nối và giải phóng tài nguyên
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false; // Trả về false nếu có lỗi xảy ra hoặc không thêm được phòng
    }

    public String removeRoom(int roomNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();

            // Bước 1: Tạo câu truy vấn SQL để xóa phòng dựa trên số phòng
            String query = "DELETE FROM room WHERE Number = ?";

            // Bước 2: Tạo đối tượng PreparedStatement để thực thi câu truy vấn
            stmt = conn.prepareStatement(query);

            // Bước 3: Đặt giá trị tham số cho câu truy vấn
            stmt.setInt(1, roomNumber);

            // Bước 4: Thực thi câu truy vấn để xóa dữ liệu trong cơ sở dữ liệu
            int rowsAffected = stmt.executeUpdate();

            // Bước 5: Kiểm tra xem có phòng nào được xóa hay không
            if (rowsAffected > 0) {
                // Nếu có ít nhất một dòng bị ảnh hưởng (phòng đã được xóa)
                // Cập nhật lại danh sách phòng trong bộ nhớ và trả về thông báo
                rooms.removeIf(room -> room.getNumber() == roomNumber);
                return "Room " + roomNumber + " removed";
            } else {
                // Nếu không có dòng nào bị ảnh hưởng (không tìm thấy phòng)
                return "Room " + roomNumber + " not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error occurred while removing room " + roomNumber;
        } finally {
            // Bước 6: Đóng kết nối và giải phóng tài nguyên
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String updateRoom(int roomNumber, Double area, Integer floor, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();

            // Bước 1: Tạo câu truy vấn SQL để cập nhật thông tin phòng dựa trên số phòng
            String query = "UPDATE room SET Area = ?, Floor = ?, Status = ? WHERE Number = ?";

            // Bước 2: Tạo đối tượng PreparedStatement để thực thi câu truy vấn
            stmt = conn.prepareStatement(query);

            // Bước 3: Đặt giá trị tham số cho câu truy vấn
            stmt.setDouble(1, area);
            stmt.setInt(2, floor);
            stmt.setString(3, status);
            stmt.setInt(4, roomNumber);

            // Bước 4: Thực thi câu truy vấn để cập nhật thông tin phòng trong cơ sở dữ liệu
            int rowsAffected = stmt.executeUpdate();

            // Bước 5: Kiểm tra xem có phòng nào được cập nhật hay không
            if (rowsAffected > 0) {
                // Nếu có ít nhất một dòng bị ảnh hưởng (phòng đã được cập nhật)
                // Cập nhật lại thông tin phòng trong bộ nhớ và trả về thông báo
                for (Room room : rooms) {
                    if (room.getNumber() == roomNumber) {
                        if (area != null) {
                            room.setArea(area);
                        }
                        if (floor != null) {
                            room.setFloor(floor);
                        }
                        if (status != null) {
                            room.setStatus(status);
                        }
                        break;
                    }
                }
                return "Room " + roomNumber + " updated";
            } else {
                // Nếu không có dòng nào bị ảnh hưởng (không tìm thấy phòng)
                return "Room " + roomNumber + " not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error occurred while updating room " + roomNumber;
        } finally {
            // Bước 6: Đóng kết nối và giải phóng tài nguyên
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Room> showAllRooms(){
        return rooms;
    }
    //Tìm phòng
    public List<Room> searchRoom(Double area, Integer floor, String roomType, String status) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : rooms) {

            //check roomtype
            String currRoomType;
            if (room instanceof StandardRoom){
                currRoomType = "Standard";
            } else {
                currRoomType = "Premium";
            }

            if (area != null && room.getArea() != area) {
                continue;
            }
            if (floor != null && room.getFloor() != floor) {
                continue;
            }
            if (roomType != null && !currRoomType.equals(roomType)) {
                continue;
            }

            if (status != null && !room.getStatus().equals(status)) {
                continue;
            }
            result.add(room);
        }
        return result;
    }


    public List<Room> searchRoom(double maxPrice) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : rooms) {
            if (room.rentalPrice() <= maxPrice) {
                result.add(room);
            }
        }
        return result;
    }

    //Doanh thu hotel
    public double currentRevenue() {
        double revenue = 0;
        for (Room room : rooms) {
            if (room.getStatus().equals("Rented")) {
                revenue += room.rentalPrice();
            }
        }
        return revenue;
    }
}