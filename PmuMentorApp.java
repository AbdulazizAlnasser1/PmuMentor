import java.util.ArrayList;
import java.util.Scanner;

public class PmuMentorApp {
    private Scanner sc = new Scanner(System.in);

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();

    private User currentUser = null;
    private int nextEventId = 1;

    public void run() {
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                running = welcomeMenu();
            } else {
                userMenu();
            }
        }

        System.out.println("Goodbye.");
    }

    private boolean welcomeMenu() {
        System.out.println("\n=== PMU Mentor (Prototype) ===");
        System.out.println("1) Create Account");
        System.out.println("2) Login");
        System.out.println("0) Exit");
        System.out.print("Choose: ");

        String choice = sc.nextLine();

        if (choice.equals("1")) {
            createAccount();
        } else if (choice.equals("2")) {
            login();
        } else if (choice.equals("0")) {
            return false;
        } else {
            System.out.println("Invalid choice.");
        }

        return true;
    }

    private void createAccount() {
        System.out.println("\n--- Create Account ---");

        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists. Try another.");
            return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Full Name: ");
        String fullName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Major: ");
        String major = sc.nextLine();

        System.out.print("Level (Freshman/Sophomore/Junior/Senior): ");
        String level = sc.nextLine();

        User u = new User(username, password, fullName, email, major, level);
        users.add(u);

        System.out.println("Account created successfully.");
    }

    private void login() {
        System.out.println("\n--- Login ---");

        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User u = findUserByUsername(username);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }

        if (!u.checkPassword(password)) {
            System.out.println("Incorrect password.");
            return;
        }

        currentUser = u;
        System.out.println("Login successful. Welcome, " + currentUser.getFullName() + "!");
    }

    private void userMenu() {
        System.out.println("\n=== User Menu (" + currentUser.getUsername() + ") ===");
        System.out.println("1) Update Account Information");
        System.out.println("2) Create Event (Virtual / In-person)");
        System.out.println("3) View All Events");
        System.out.println("4) View My Events");
        System.out.println("5) Logout");
        System.out.print("Choose: ");

        String choice = sc.nextLine();

        if (choice.equals("1")) {
            updateAccount();
        } else if (choice.equals("2")) {
            createEvent();
        } else if (choice.equals("3")) {
            viewAllEvents();
        } else if (choice.equals("4")) {
            viewMyEvents();
        } else if (choice.equals("5")) {
            currentUser = null;
            System.out.println("Logged out.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void updateAccount() {
        System.out.println("\n--- Update Account Information ---");
        System.out.println("Leave blank to keep current value.");

        System.out.print("Full Name (" + currentUser.getFullName() + "): ");
        String fullName = sc.nextLine();
        if (!fullName.trim().isEmpty()) currentUser.setFullName(fullName);

        System.out.print("Email (" + currentUser.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.trim().isEmpty()) currentUser.setEmail(email);

        System.out.print("Major (" + currentUser.getMajor() + "): ");
        String major = sc.nextLine();
        if (!major.trim().isEmpty()) currentUser.setMajor(major);

        System.out.print("Level (" + currentUser.getLevel() + "): ");
        String level = sc.nextLine();
        if (!level.trim().isEmpty()) currentUser.setLevel(level);

        System.out.print("New Password (hidden): ");
        String newPass = sc.nextLine();
        if (!newPass.trim().isEmpty()) currentUser.setPassword(newPass);

        System.out.println("Account updated successfully.");
    }

    private void createEvent() {
        System.out.println("\n--- Create Event ---");

        System.out.print("Event Title: ");
        String title = sc.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Type (Virtual/In-person): ");
        String type = sc.nextLine();

        System.out.print("Date/Time (e.g., 2025-12-14 09:00): ");
        String dateTime = sc.nextLine();

        System.out.print("Location or Link: ");
        String locationOrLink = sc.nextLine();

        Event e = new Event(nextEventId, title, type, dateTime, locationOrLink, currentUser.getUsername());
        nextEventId++;
        events.add(e);

        System.out.println("Event created successfully.");
    }

    private void viewAllEvents() {
        System.out.println("\n--- All Events ---");
        if (events.size() == 0) {
            System.out.println("No events available.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).toString());
        }
    }

    private void viewMyEvents() {
        System.out.println("\n--- My Events ---");
        boolean found = false;

        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.getCreatedByUsername().equals(currentUser.getUsername())) {
                System.out.println(e.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have not created any events.");
        }
    }

    private User findUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }
}
