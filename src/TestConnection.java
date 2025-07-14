import java.sql.Date;
import java.util.Scanner;

//import com.mysql.cj.protocol.SocksProxySocketFactory;

public class TestConnection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n📋 PG Management Menu:");
            System.out.println("1. Add Tenant");
            System.out.println("2. Display Tenants");
            System.out.println("3. Update Tenant");
            System.out.println("4. Delete Tenant");
            System.out.println("5. Search Tenant by Aadhaar");
            System.out.println("6. Search Tenant by Name");
            System.out.println("7. Show Rent Due Alerts");
            System.out.println("8. mark payment done");
            System.out.println("9. vacate Tenant");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Room No: ");
                    int roomNo = sc.nextInt();
                 // ✅ Check if room is available
                    
                    if (!RoomOperations.isRoomAvailable(roomNo)) {
                        System.out.println("❌ Room is already full. Cannot insert tenant.");
                        break;
                    }
                    System.out.print("Rent: ");
                    double rent = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    System.out.print("Joining Date (yyyy-mm-dd): ");
                    Date joiningDate = Date.valueOf(sc.nextLine());
                    System.out.print("Leaving Date (yyyy-mm-dd): ");
                    Date leavingDate = Date.valueOf(sc.nextLine());
                    System.out.print("Aadhaar Number: ");
                    String aadhaar = sc.nextLine();
                    System.out.print("Occupation: ");
                    String occupation = sc.nextLine();
                    System.out.print("Payment Status: ");
                    String payment = sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();

                    TenantOperations.insertTenant(name, phone, gender, roomNo, rent,
                            joiningDate, leavingDate, aadhaar, occupation, payment, status);
                    break;

                case 2:
                    TenantOperations.displayTenants();
                    break;

                case 3:
                    System.out.print("Enter Aadhaar to update: ");
                    String updateAadhaar = sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Phone: ");
                    String newPhone = sc.nextLine();
                    System.out.print("New Gender: ");
                    String newGender = sc.nextLine();
                    System.out.print("New Room No: ");
                    int newRoomNo = sc.nextInt();
                    System.out.print("New Rent: ");
                    double newRent = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("New Joining Date (yyyy-mm-dd): ");
                    Date newJoin = Date.valueOf(sc.nextLine());
                    System.out.print("New Leaving Date (yyyy-mm-dd): ");
                    Date newLeave = Date.valueOf(sc.nextLine());
                    System.out.print("New Occupation: ");
                    String newOcc = sc.nextLine();
                    System.out.print("New Payment Status: ");
                    String newPay = sc.nextLine();
                    System.out.print("New Status: ");
                    String newStat = sc.nextLine();

                    TenantOperations.updateTenant(updateAadhaar, newName, newPhone, newGender,
                            newRoomNo, newRent, newJoin, newLeave, newOcc, newPay, newStat);
                    break;

                case 4:
                    System.out.print("Enter Aadhaar to delete: ");
                    String deleteAadhaar = sc.nextLine();
                    TenantOperations.deleteTenant(deleteAadhaar);
                    break;

                case 5:
                    System.out.print("Enter Aadhaar to search: ");
                    String searchAadhaar = sc.nextLine();
                    TenantOperations.searchTenantByAadhaar(searchAadhaar);
                    break;

                case 6:
                    System.out.print("Enter Name to search: ");
                    String searchName = sc.nextLine();
                    TenantOperations.searchTenantByName(searchName);
                    break;
                case 7:
                    TenantOperations.showPendingPayments();
                    break;
                case 8:
                    System.out.print("Enter Phone Number to mark payment as done: ");
                    String Phone = sc.nextLine();
                    TenantOperations.markPaymentDone(Phone);
                    break;
                case 9:
                    System.out.print("Enter Phone Number to vacate: ");
                    String PhoneNumber = sc.nextLine();
                    TenantOperations.vacateTenant(PhoneNumber);
                    break;

                case 0:
                    System.out.println("👋 Exiting. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
