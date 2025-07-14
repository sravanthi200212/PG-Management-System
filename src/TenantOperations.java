
import java.sql.*;

public class TenantOperations {

    // Display all tenants
    public static void displayTenants() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM tenants";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Tenant Details:");
            System.out.println("ID | Name | Phone | Gender | Room No | Rent | Joining Date | Leaving Date | aadhaar_number | Occupation | Payment | Status");

            while (rs.next()) {
                int id = rs.getInt("tenant_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                int roomNo = rs.getInt("room_no");
                double rent = rs.getDouble("rent");
                Date joiningDate = rs.getDate("joining_date");
                Date leavingDate = rs.getDate("leaving_date");
                String  aadhaarnumber = rs.getString("aadhaar_number");
                String occupation = rs.getString("occupation");
                String paymentStatus = rs.getString("payment_status");
                String status = rs.getString("status");

                System.out.println(id + " | " + name + " | " + phone + " | " + gender + " | " + roomNo + " | " + rent + " | " +
                        joiningDate + " | " + leavingDate + " | " + aadhaarnumber + " | " + occupation + " | " + paymentStatus + " | " + status);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while displaying tenants:");
            e.printStackTrace();
        }
    }
    //insert
    
    public static void insertTenant(String name, String phone, String gender, int roomNo, double rent, Date joiningDate,
            Date leavingDate, String aadhaarNumber, String occupation, String paymentStatus, String status) {
try {
Connection conn = DBConnection.getConnection();

String query = "INSERT INTO tenants (name, phone, gender, room_no, rent, joining_date, leaving_date, aadhaar_number, occupation, payment_status, status) " +
   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
PreparedStatement pstmt = conn.prepareStatement(query);

pstmt.setString(1, name);
pstmt.setString(2, phone);
pstmt.setString(3, gender);
pstmt.setInt(4, roomNo);
pstmt.setDouble(5, rent);
pstmt.setDate(6, joiningDate);
pstmt.setDate(7, leavingDate);
pstmt.setString(8, aadhaarNumber);  
pstmt.setString(9, occupation);
pstmt.setString(10, paymentStatus);
pstmt.setString(11, status);

int rows = pstmt.executeUpdate();
if (rows > 0) {
System.out.println("✅ Tenant inserted successfully.");
} else {
System.out.println("❌ Tenant insertion failed.");
}

conn.close();

} catch (Exception e) {
System.out.println("❌ Error while inserting tenant:");
e.printStackTrace();
}
}
    
    
    public static boolean aadhaarExists(String aadhaarNumber) {
        boolean exists = false;

        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT tenant_id FROM tenants WHERE aadhaar_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, aadhaarNumber);
            ResultSet rs = pstmt.executeQuery();

            exists = rs.next(); // if any record found, it exists

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error checking Aadhaar number:");
            e.printStackTrace();
        }

        return exists;
    }
    

//update method
    public static void updateTenant(
    	    String aadhaarNumber, String newName, String newPhone, String newGender,
    	    int newRoomNo, double newRent, Date newJoiningDate, Date newLeavingDate,
    	    String newOccupation, String newPaymentStatus, String newStatus) {

    	    try {
    	        Connection conn = DBConnection.getConnection();

    	        String query = "UPDATE tenants SET name = ?, phone = ?, gender = ?, room_no = ?, rent = ?, " +
    	                       "joining_date = ?, leaving_date = ?, occupation = ?, payment_status = ?, status = ? " +
    	                       "WHERE aadhaar_number = ?";

    	        PreparedStatement pstmt = conn.prepareStatement(query);

    	        pstmt.setString(1, newName);
    	        pstmt.setString(2, newPhone);
    	        pstmt.setString(3, newGender);
    	        pstmt.setInt(4, newRoomNo);
    	        pstmt.setDouble(5, newRent);
    	        pstmt.setDate(6, newJoiningDate);
    	        pstmt.setDate(7, newLeavingDate);
    	        pstmt.setString(8, newOccupation);
    	        pstmt.setString(9, newPaymentStatus);
    	        pstmt.setString(10, newStatus);
    	        pstmt.setString(11, aadhaarNumber);  // WHERE clause

    	        int rows = pstmt.executeUpdate();

    	        if (rows > 0) {
    	            System.out.println("✅ Tenant details updated successfully.");
    	        } else {
    	            System.out.println("⚠️ No tenant found with Aadhaar: " + aadhaarNumber);
    	        }

    	        conn.close();

    	    } catch (Exception e) {
    	        System.out.println("❌ Error while updating tenant:");
    	        e.printStackTrace();
    	    }
    	}


// delete method
    public static void deleteTenant(String aadhaarNumber) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "DELETE FROM tenants WHERE aadhaar_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, aadhaarNumber);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Tenant deleted successfully.");
            } else {
                System.out.println("⚠️ No tenant found with Aadhaar: " + aadhaarNumber);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while deleting tenant:");
            e.printStackTrace();
        }
    }

    // search method
    
    public static void searchTenantByAadhaar(String aadhaarNumber) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM tenants WHERE aadhaar_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, aadhaarNumber);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Tenant Found:");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Room No: " + rs.getInt("room_no"));
                System.out.println("Rent: " + rs.getDouble("rent"));
                System.out.println("Joining Date: " + rs.getDate("joining_date"));
                System.out.println("Leaving Date: " + rs.getDate("leaving_date"));
                System.out.println("Occupation: " + rs.getString("occupation"));
                System.out.println("Payment: " + rs.getString("payment_status"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("⚠️ No tenant found with Aadhaar: " + aadhaarNumber);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while searching tenant:");
            e.printStackTrace();
        }
    }

    // search by name method
    public static void searchTenantByName(String name) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM tenants WHERE name LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + name + "%");

            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("---- Tenant Found ----");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Room No: " + rs.getInt("room_no"));
                System.out.println("Aadhaar: " + rs.getString("aadhaar_number"));
                System.out.println("Status: " + rs.getString("status"));
            }

            if (!found) {
                System.out.println("⚠️ No tenant found with name: " + name);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while searching by name:");
            e.printStackTrace();
        }
    }

    // pending payments
    public static void showPendingPayments() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM tenants WHERE payment_status = 'pending'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("💸 Tenants with Pending Rent:");
            System.out.println("ID | Name | Phone | Room No | Rent | Aadhaar | Payment Status");

            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("tenant_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int roomNo = rs.getInt("room_no");
                double rent = rs.getDouble("rent");
                String aadhaar = rs.getString("aadhaar_number");
                String payment = rs.getString("payment_status");

                System.out.println(id + " | " + name + " | " + phone + " | " + roomNo + " | " + rent + " | " + aadhaar + " | " + payment);
                found = true;
            }

            if (!found) {
                System.out.println("✅ All tenants have paid rent.");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error fetching pending payments:");
            e.printStackTrace();
        }
    }

    //payment details
    public static void markPaymentDone(String phoneNumber) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE tenants SET payment_status = 'paid' WHERE phone = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phoneNumber);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Payment status updated to 'paid' successfully.");
            } else {
                System.out.println("❌ Tenant not found or already paid.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error while updating payment status:");
            e.printStackTrace();
        }
    }
//vacate room
    public static void vacateTenant(String phoneNumber) {
        try {
            Connection conn = DBConnection.getConnection();

            // First, check if tenant exists
            String checkQuery = "SELECT status FROM tenants WHERE phone = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, phoneNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Tenant not found.");
                conn.close();
                return;
            }

            String status = rs.getString("status");
            if (status.equalsIgnoreCase("inactive")) {
                System.out.println("⚠️ Tenant is already marked as inactive.");
                conn.close();
                return;
            }

            // Now vacate
            String updateQuery = "UPDATE tenants SET status = 'inactive', payment_status = 'paid' WHERE phone = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, phoneNumber);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Tenant vacated successfully.");
            } else {
                System.out.println("❌ Unexpected error while vacating.");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error while vacating tenant:");
            e.printStackTrace();
        }
    }

    




    }
