import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {

    // Todo: Add The Following Fields : username, password,role
    private String uName;
    private String uPass;
    private String uRole;

    User(String uName, String uPass, String uRole) {
        this.uName = uName;
        this.uPass = uPass;
        this.uRole = uRole;
    }

    // TODO: add getters
    public String getUsername() {
        return uName;
    }

    public String getPassword() {
        return uPass;
    }

    public String getRole() {
        return uRole;
    }
}

class WelcomePage extends Frame {

    WelcomePage(User usr) {
        super("Welcome");
        // TODO: build the page that says "Hello, username" and the role of the user
        setLayout(new BorderLayout(10, 20));

        Label welcomeLbl = new Label("Hello, " + usr.getUsername() + " (" + usr.getRole() + ")", Label.CENTER);
        welcomeLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(welcomeLbl, BorderLayout.CENTER);

        Button logoutBtn = new Button("Close");
        logoutBtn.addActionListener(e -> dispose());
        Panel p = new Panel();
        p.add(logoutBtn);
        add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setSize(380, 200);
        setLocationRelativeTo(null);
    }
}

public class LoginFormTask extends Frame {

    // TODO: add list of users to seed
    List<User> userList = new ArrayList<>();
    Label msgLabel;
    TextField txtUser;
    TextField txtPass;
    Choice roleChoice;
    Button btnLogin;
    Button btnCancel;

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        setBackground(new Color(242, 244, 247));

        Panel card = new Panel(new BorderLayout(10, 15));

        Label header = new Label("Welcome back", Label.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        card.add(header, BorderLayout.NORTH);

        Panel formFields = new Panel(new GridLayout(4, 2, 8, 8));
        formFields.add(new Label("Username:", Label.RIGHT));
        txtUser = new TextField(18);
        formFields.add(txtUser);

        formFields.add(new Label("Password:", Label.RIGHT));
        txtPass = new TextField(18);
        txtPass.setEchoChar('*');
        formFields.add(txtPass);

        formFields.add(new Label("Role:", Label.RIGHT));
        roleChoice = new Choice();
        roleChoice.add("Student");
        roleChoice.add("TA");
        roleChoice.add("Professor");
        formFields.add(roleChoice);

        formFields.add(new Label("")); 
        msgLabel = new Label("");
        msgLabel.setForeground(Color.RED);
        formFields.add(msgLabel);

        card.add(formFields, BorderLayout.CENTER);

        Panel actions = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnCancel = new Button("Cancel");
        btnLogin = new Button("Login");
        actions.add(btnCancel);
        actions.add(btnLogin);
        card.add(actions, BorderLayout.SOUTH);

        add(card);

        btnLogin.addActionListener(e -> {
            // TODO: the login logic
            String u = txtUser.getText().trim();
            String p = txtPass.getText().trim();
            String r = roleChoice.getSelectedItem();

            if (u.isEmpty() || p.isEmpty()) {
                msgLabel.setText("Fill all fields!");
                return;
            }

            User user = findUser(u);
            if (user == null) {
                msgLabel.setText("User not found!");
                return;
            }

            if (!user.getPassword().equals(p)) {
                msgLabel.setText("Wrong password!");
                return;
            }

            if (!user.getRole().equalsIgnoreCase(r)) {
                msgLabel.setText("Role mismatch!");
                return;
            }

            msgLabel.setText("");
            new WelcomePage(user).setVisible(true);
            dispose();
        });

        btnCancel.addActionListener(e -> {
            // TODO: close the window
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setSize(420, 340);
        setLocationRelativeTo(null);
    }

    private void loadUsers() {
        // TODO: add at least 3 users to the list one for each role
        userList.add(new User("ahmed", "1234", "Student"));
        userList.add(new User("mona", "ta123", "TA"));
        userList.add(new User("dr_ali", "prof123", "Professor"));
    }

    private User findUser(String name) {
        // TODO: search the list for the user
        for (User u : userList) {
            if (u.getUsername().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);
    }
}
