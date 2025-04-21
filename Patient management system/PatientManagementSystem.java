import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class User {
    private String id;
    private String password;
    private String role;

    public User(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role.toLowerCase();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

class UserA {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<Integer, ArrayList<String>> patientRecordsMap = new HashMap<>();
    private HashMap<Integer, String> appointmentRecordsMap = new HashMap<>();
    private String makeAppointment;
    private String status = "Pending";
    private int appointmentID;
    private int appointmentIDGiven;

    public void makeAppointment() {
        System.out.println("Making an appointment...");

        try {
            System.out.print("Enter ID: ");
            appointmentIDGiven = Integer.parseInt(scanner.nextLine());

            System.out.print("Do you want to make an appointment? (yes/no): ");
            makeAppointment = scanner.nextLine().toLowerCase();

            if (makeAppointment.equals("yes")) {
                appointmentRecordsMap.put(appointmentIDGiven, status);
                System.out.println("Appointment request submitted. Status: " + status.toUpperCase());
            } else {
                System.out.println("Appointment not requested.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }


    public void takePrescription() {
        System.out.println("Loading Prescription...");
        System.out.print("Enter your ID: ");
        try {
            appointmentID = Integer.parseInt(scanner.nextLine());
            System.out.println("The Prescription:");
            if (patientRecordsMap.containsKey(appointmentID)) {
                ArrayList<String> records = patientRecordsMap.get(appointmentID);
                if (records.isEmpty()) {
                    System.out.println("System has not updated yet.");
                } else {
                    for (String record : records) {
                        System.out.println(record);
                    }
                }
            } else {
                System.out.println("No records found for this ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    public void viewPatientRecords() {
        System.out.println("Loading Patient Records...");
        System.out.print("Enter your ID: ");
        try {
            appointmentID = Integer.parseInt(scanner.nextLine());
            if (patientRecordsMap.containsKey(appointmentID)) {
                ArrayList<String> records = patientRecordsMap.get(appointmentID);
                if (records.isEmpty()) {
                    System.out.println("System has not updated yet.");
                } else {
                    for (String record : records) {
                        System.out.println(record);
                    }
                }
            } else {
                System.out.println("No records found for this ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    public void updatePatientRecords() {
        System.out.println("Updating Patient Records...");
        System.out.print("Enter patient ID: ");
        try {
            appointmentIDGiven = Integer.parseInt(scanner.nextLine());
            patientRecordsMap.putIfAbsent(appointmentIDGiven, new ArrayList<>());

            System.out.println("Enter Info (Symptoms - Medicine), type 'exit' to stop:");
            while (true) {
                System.out.print("Enter Info: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                patientRecordsMap.get(appointmentIDGiven).add(input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
    public void ManageAppointmentsScheduling() {
        System.out.println("Managing Appointment Scheduling");
        System.out.println("Available Appointment IDs:");
        for (Integer id : appointmentRecordsMap.keySet()) {
            System.out.println("- ID: " + id);
        }
        System.out.print("Enter Patients ID: ");

        try {
            appointmentID = Integer.parseInt(scanner.nextLine());

            if (appointmentRecordsMap.containsKey(appointmentID)) {
                String currentStatus = appointmentRecordsMap.get(appointmentID);

                if (currentStatus.isEmpty()) {
                    System.out.println("System has not updated yet.");
                } else {
                    System.out.println("Current Status: " + currentStatus.toUpperCase());
                    System.out.print("Enter new status (approved/denied): ");
                    String newStatus = scanner.nextLine().toLowerCase();
                    appointmentRecordsMap.put(appointmentID, newStatus);
                    System.out.println("Status updated to: " + newStatus.toUpperCase());
                }

            } else {
                System.out.println("No records found for this ID.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    public void ViewAppointmentsScheduling() {
        System.out.println("Showing Appointment Scheduling");
        System.out.print("Enter your ID: ");

        try {
            appointmentID = Integer.parseInt(scanner.nextLine());

            if (appointmentRecordsMap.containsKey(appointmentID)) {
                String currentStatus = appointmentRecordsMap.get(appointmentID);

                if (currentStatus.isEmpty()) {
                    System.out.println("System has not updated yet.");
                } else {
                    System.out.println("Current Status: " + currentStatus.toUpperCase());

                }

            } else {
                System.out.println("No records found for this ID.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }



}

class UserManager {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<String> doctorInfo = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public void registerUser() {
        System.out.println("\nRegister a new user:");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Role (Patient, Clinician,  Admin): ");
        String role = scanner.nextLine();

        users.add(new User(id, password, role));
        System.out.println("User registered successfully!");
    }

    public User authenticateUser() {
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                System.out.println("\nLogin successful. Your role is: " + user.getRole());
                return user;
            }
        }
        System.out.println("Invalid credentials! Please try again.");
        return null;
    }

    public void changePassword(User user) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password updated successfully!");
    }

    public void viewDoctorsInfo() {
        System.out.println("Doctor Information:");
        if (doctorInfo.isEmpty()) {
            System.out.println("The Doctor are:\n1. Doctor A-1778-heart\n2.Doctor B-1999-Neuro");
        } else {
            int a=3;
            System.out.println("The Doctor are:\n1. Doctor A-1778-heart\n2.Doctor B-1999-Neuro");
            for (String info : doctorInfo) {

                System.out.println( a+"."+info);
                a=a+1;
            }
        }
    }

    public void manageDoctors() {
        System.out.println("Managing Doctors:");
        System.out.println("Enter doctor info (Name - ID - Speciality), type 'exit' to stop:");
        while (true) {
            System.out.print("Enter Info: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            doctorInfo.add(input);
        }
    }
}

public class PatientManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();
    private static UserA userA = new UserA();

    public static void main(String[] args) {
        System.out.println("Welcome to the Patient Management System");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("- Register\n- Login\n- View Doctors Info\n- Exit");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "register":
                    userManager.registerUser();
                    break;
                case "login":
                    User user = userManager.authenticateUser();
                    if (user != null) handleUserRole(user);
                    break;
                case "view doctors info":
                    userManager.viewDoctorsInfo();
                    break;
                case "exit":
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserRole(User user) {
        while (true) {
            System.out.println("\nSelect an option based on your role:");
            switch (user.getRole()) {
                case "patient":
                    System.out.println("- View Doctors Info\n- Make Appointment\n-View Appointments Scheduling\n- Take Prescription\n- Change Password\n- Logout");
                    break;
                case "clinician":
                    System.out.println("- View Patient Records\n- Update Patient Records\n- Change Password\n- Logout");
                    break;
                case "admin":
                    System.out.println("- Manage Doctors\n-Manage Appointments Scheduling\n- Change Password\n- Logout");
                    break;
                default:
                    System.out.println("Invalid role. Please restart.");
                    return;
            }

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "change password":
                    userManager.changePassword(user);
                    break;
                case "logout":
                    System.out.println("Logging out... Goodbye!");
                    return;
                case "view doctors info":
                    userManager.viewDoctorsInfo();
                    break;
                case "make appointment":
                    userA.makeAppointment();
                    break;
                case "take prescription":
                    userA.takePrescription();
                    break;
                case "update patient records":
                    userA.updatePatientRecords();
                    break;
                case "manage doctors":
                    userManager.manageDoctors();
                    break;
                case "view patient records":
                    userA.viewPatientRecords();
                    break;
                case "manage appointments scheduling":
                    userA.ManageAppointmentsScheduling();
                    break;
                case "view appointments scheduling":
                    userA.ViewAppointmentsScheduling();
                    break;


                default:

                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}