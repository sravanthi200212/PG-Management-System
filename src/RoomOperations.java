
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomOperations {
     // TO DISPLAY ROOMS
    public static void displayRooms() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM rooms";

            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Room Details:");
            System.out.println("Room No | Floor | Capacity | Occupied");

            while (rs.next()) {
                int roomNo = rs.getInt("room_no");
                int floor = rs.getInt("floor");
                int capacity = rs.getInt("capacity");
                boolean occupied = rs.getBoolean("occupied");

                System.out.println(roomNo + " | " + floor + " | " + capacity + " | " + occupied);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while displaying rooms:");
            e.printStackTrace();
        }
        
    }
    //TO INSERT ROOM DATA
    public static void insertRoom(int roomNo, int floor, int capacity, boolean occupied) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO rooms (room_no, floor, capacity, occupied) VALUES ("
                    + roomNo + ", " + floor + ", " + capacity + ", " + occupied + ")";

            int rows = stmt.executeUpdate(query);
            if (rows > 0) {
                System.out.println("✅ Room inserted successfully.");
            } else {
                System.out.println("❌ Insertion failed.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error while inserting room:");
            e.printStackTrace();
        }
    }
    // To UPDATE ROOM HISTORY
    public static void updateRoom(int roomNo, int newFloor, int newCapacity, boolean newOccupied) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            String query = "UPDATE rooms SET floor = " + newFloor +
                           ", capacity = " + newCapacity +
                           ", occupied = " + newOccupied +
                           " WHERE room_no = " + roomNo;

            int rows = stmt.executeUpdate(query);

            if (rows > 0) {
                System.out.println("✅ Room updated successfully.");
            } else {
                System.out.println("⚠️ Room not found.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error while updating room:");
            e.printStackTrace();
        }
    }
 // Method to delete a room using its room number
    public static void deleteRoom(int roomNo) {
        try {
            // Step 1: Connect to database
            Connection conn = DBConnection.getConnection();

            // Step 2: Create a SQL statement
            Statement stmt = conn.createStatement();

            // Step 3: Write delete query for the given room number
            String query = "DELETE FROM rooms WHERE room_no = " + roomNo;

            // Step 4: Execute the query and get affected rows
            int rows = stmt.executeUpdate(query);

            // Step 5: Check if any row was deleted
            if (rows > 0) {
                System.out.println("✅ Room deleted successfully.");
            } else {
                System.out.println("⚠️ Room not found.");
            }

            // Step 6: Close the connection
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while deleting room:");
            e.printStackTrace();
        }
    }
    
    public static boolean isRoomAvailable(int roomNo) {
        try {
            Connection conn = DBConnection.getConnection();

            // 1. Get room capacity
            String capQuery = "SELECT capacity FROM rooms WHERE room_no = ?";
            PreparedStatement capStmt = conn.prepareStatement(capQuery);
            capStmt.setInt(1, roomNo);
            ResultSet capRs = capStmt.executeQuery();

            if (!capRs.next()) {
                System.out.println("❌ Room not found.");
                conn.close();
                return false;
            }

            int capacity = capRs.getInt("capacity");

            // 2. Get active tenant count
            String countQuery = "SELECT COUNT(*) FROM tenants WHERE room_no = ? AND status = 'active'";
            PreparedStatement countStmt = conn.prepareStatement(countQuery);
            countStmt.setInt(1, roomNo);
            ResultSet countRs = countStmt.executeQuery();

            int count = 0;
            if (countRs.next()) {
                count = countRs.getInt(1);
            }

            // 🧠 Add Debugging Print
            System.out.println("🔎 Room Check → Room No: " + roomNo);
            System.out.println("🔢 Capacity: " + capacity + ", Active Tenants: " + count);

            conn.close();

            // ✅ Only return true if current tenants < capacity
            return count < capacity;

        } catch (Exception e) {
            System.out.println("❌ Error checking room availability:");
            e.printStackTrace();
            return false;
        }
    }




}
