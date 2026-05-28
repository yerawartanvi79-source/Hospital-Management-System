import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;

public class HospitalGUI extends Application {

    TableView<PatientData> table =
            new TableView<>();

    ObservableList<PatientData> patientList =
            FXCollections.observableArrayList();

    Hospital hospital =
            new Hospital(5);

    Connection conn;

    TextArea output =
            new TextArea();

    // ================= DATABASE =================

    public void connectDatabase() {

        try {

            conn =
                    DBConnection.connect();

            Statement stmt =
                    conn.createStatement();

            String sql =
                    "CREATE TABLE IF NOT EXISTS patients(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "age INTEGER," +
                    "blood TEXT," +
                    "type TEXT," +
                    "room INTEGER," +
                    "status TEXT," +
                    "bill REAL" +
                    ")";

            stmt.execute(sql);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= LOAD TABLE =================

    public void loadTable() {

        patientList.clear();

        try {

            Statement stmt =
                    conn.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM patients"
                    );

            while (rs.next()) {

                patientList.add(

                        new PatientData(

                                rs.getString("name"),

                                rs.getInt("age"),

                                rs.getString("blood"),

                                rs.getString("type"),

                                rs.getInt("room"),

                                rs.getString("status")
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= UPDATE STATUS =================

    public void updatePatientStatus(String name) {

        try {

            String sql =
                    "UPDATE patients SET status='Discharged' WHERE name=?";

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setString(1, name);

            pstmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= UPDATE BILL =================

    public void updateBill(
            String name,
            double bill
    ) {

        try {

            String sql =
                    "UPDATE patients SET bill=? WHERE name=?";

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setDouble(1, bill);
            pstmt.setString(2, name);

            pstmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= MAIN =================

    @Override
    public void start(Stage stage) {

        connectDatabase();

        loadTable();

        // ================= TABLE =================

        TableColumn<PatientData, String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<PatientData, Integer> ageCol =
                new TableColumn<>("Age");

        ageCol.setCellValueFactory(
                new PropertyValueFactory<>("age")
        );

        TableColumn<PatientData, String> bloodCol =
                new TableColumn<>("Blood");

        bloodCol.setCellValueFactory(
                new PropertyValueFactory<>("blood")
        );

        TableColumn<PatientData, String> typeCol =
                new TableColumn<>("Type");

        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        TableColumn<PatientData, Integer> roomCol =
                new TableColumn<>("Room");

        roomCol.setCellValueFactory(
                new PropertyValueFactory<>("room")
        );

        TableColumn<PatientData, String> statusCol =
                new TableColumn<>("Status");

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        table.getColumns().addAll(
                nameCol,
                ageCol,
                bloodCol,
                typeCol,
                roomCol,
                statusCol
        );

        table.setItems(patientList);

        table.setPrefHeight(300);

        // ================= LOGIN =================

        Label loginTitle =
                new Label("Hospital Login");

        loginTitle.setFont(
                new Font("Arial", 28)
        );

        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Username"
        );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Password"
        );

        Label loginMessage =
                new Label();

        Button loginBtn =
                new Button("Login");

        VBox loginRoot =
                new VBox(15);

        loginRoot.setPadding(
                new Insets(30)
        );

        loginRoot.setAlignment(
                Pos.CENTER
        );

        loginRoot.getChildren().addAll(
                loginTitle,
                usernameField,
                passwordField,
                loginBtn,
                loginMessage
        );

        Scene loginScene =
                new Scene(loginRoot, 400, 400);

        // ================= DASHBOARD =================

        Label title =
                new Label("🏥 Hospital Management System");

        title.setFont(
                new Font("Arial", 28)
        );

        title.setTextFill(
                Color.WHITE
        );

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter Name"
        );

        TextField ageField =
                new TextField();

        ageField.setPromptText(
                "Enter Age"
        );

        TextField bloodField =
                new TextField();

        bloodField.setPromptText(
                "Enter Blood Group"
        );

        TextField daysField =
                new TextField();

        daysField.setPromptText(
                "Enter Days"
        );

        ComboBox<String> typeBox =
                new ComboBox<>();

        typeBox.getItems().addAll(
                "Regular",
                "ICU"
        );

        typeBox.setValue(
                "Regular"
        );

        Button admitBtn =
                new Button("Admit");

        Button roomBtn =
                new Button("Rooms");

        Button dbBtn =
                new Button("Database");

        Button dischargeBtn =
                new Button("Discharge");

        Button revenueBtn =
                new Button("Revenue");

        Button logoutBtn =
                new Button("Logout");

        output.setEditable(false);

        // ================= ADMIT =================

        admitBtn.setOnAction(e -> {

            try {

                String name =
                        nameField.getText();

                String blood =
                        bloodField.getText();

                int age =
                        Integer.parseInt(
                                ageField.getText()
                        );

                String type =
                        typeBox.getValue();

                Patient patient;

                if(type.equals("ICU")) {

                    patient =
                            new IcuPatient(
                                    name,
                                    age,
                                    blood
                            );

                } else {

                    patient =
                            new RegularPatient(
                                    name,
                                    age,
                                    blood
                            );
                }

                String roomMessage =
                        hospital.admitPatient(patient);

                if(roomMessage.equals("No Rooms Available!")) {

                    output.setText(roomMessage);

                    return;
                }

                int roomNo =
                        Integer.parseInt(
                                roomMessage.replaceAll("\\D+","")
                        );

                // ================= SPRING BOOT API =================

                URL url =
        new URL("https://hospital-management-system-wtdw.onrender.com/add");

                HttpURLConnection apiConn =
                        (HttpURLConnection) url.openConnection();

                apiConn.setRequestMethod("POST");

                apiConn.setRequestProperty(
                        "Content-Type",
                        "application/json"
                );

                apiConn.setDoOutput(true);

                String jsonInput =
                        "{"
                        + "\"name\":\"" + name + "\","
                        + "\"age\":" + age + ","
                        + "\"blood\":\"" + blood + "\","
                        + "\"type\":\"" + type + "\","
                        + "\"room\":" + roomNo + ","
                        + "\"status\":\"Admitted\","
                        + "\"bill\":0"
                        + "}";

                OutputStream os =
                        apiConn.getOutputStream();

                os.write(
                        jsonInput.getBytes()
                );

                os.flush();
                os.close();

                apiConn.getResponseCode();

                apiConn.disconnect();

                // ================= LOCAL SQLITE SAVE =================

                String sql =
                        "INSERT INTO patients(name,age,blood,type,room,status,bill) VALUES(?,?,?,?,?,?,?)";

                PreparedStatement pstmt =
                        conn.prepareStatement(sql);

                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, blood);
                pstmt.setString(4, type);
                pstmt.setInt(5, roomNo);
                pstmt.setString(6, "Admitted");
                pstmt.setDouble(7, 0);

                pstmt.executeUpdate();

                loadTable();

                output.setText(
                        "Patient Added Successfully\n\n"
                                + roomMessage
                );

                nameField.clear();
                ageField.clear();
                bloodField.clear();

            } catch (Exception ex) {

                ex.printStackTrace();

                output.setText(
                        "Error While Adding Patient"
                );
            }
        });

        // ================= ROOMS =================

        roomBtn.setOnAction(e -> {

            output.clear();

            for(RoomSlot room : hospital.rooms) {

                if(room.isEmpty()) {

                    output.appendText(
                            "Room "
                                    + room.roomNumber
                                    + " : Empty\n"
                    );

                } else {

                    output.appendText(
                            "Room "
                                    + room.roomNumber
                                    + " : "
                                    + room.patient.name
                                    + "\n"
                    );
                }
            }
        });

        // ================= DATABASE =================

        dbBtn.setOnAction(e -> {

            output.clear();

            try {

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(
                                "SELECT * FROM patients"
                        );

                while (rs.next()) {

                    output.appendText(
                            "Name : "
                                    + rs.getString("name")

                                    + "\nRoom : "
                                    + rs.getInt("room")

                                    + "\nStatus : "
                                    + rs.getString("status")

                                    + "\nBill : ₹"
                                    + rs.getDouble("bill")

                                    + "\n------------------\n"
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= DISCHARGE =================

        dischargeBtn.setOnAction(e -> {

            try {

                String patientName =
                        nameField.getText();

                int days =
                        Integer.parseInt(
                                daysField.getText()
                        );

                double bill = 0;

                String type = "";

                String sql =
                        "SELECT * FROM patients WHERE name=?";

                PreparedStatement pstmt =
                        conn.prepareStatement(sql);

                pstmt.setString(1, patientName);

                ResultSet rs =
                        pstmt.executeQuery();

                if(rs.next()) {

                    type =
                            rs.getString("type");
                }

                if(type.equals("ICU")) {

                    bill = days * 2000;

                } else {

                    bill = days * 500;
                }

                String msg =
                        hospital.dischargePatient(
                                patientName,
                                days
                        );

                updatePatientStatus(patientName);

                updateBill(
                        patientName,
                        bill
                );

                loadTable();

                output.setText(
                        msg +
                        "\n\nBill Saved"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                output.setText(
                        "Invalid Input"
                );
            }
        });

        // ================= REVENUE =================

        revenueBtn.setOnAction(e -> {

            try {

                String sql =
                        "SELECT SUM(bill) AS total FROM patients";

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql);

                double revenue = 0;

                if(rs.next()) {

                    revenue =
                            rs.getDouble("total");
                }

                output.setText(
                        "Total Revenue : ₹"
                                + revenue
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= LOGOUT =================

        logoutBtn.setOnAction(e -> {

            stage.setScene(loginScene);
        });

        HBox buttonBox =
                new HBox(10);

        buttonBox.getChildren().addAll(
                admitBtn,
                roomBtn,
                dbBtn,
                dischargeBtn,
                revenueBtn,
                logoutBtn
        );

        buttonBox.setAlignment(
                Pos.CENTER
        );

        VBox dashboardRoot =
                new VBox(15);

        dashboardRoot.setPadding(
                new Insets(25)
        );

        dashboardRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom,#74b9ff,#dfe6e9);"
        );

        dashboardRoot.getChildren().addAll(
                title,
                nameField,
                ageField,
                bloodField,
                daysField,
                typeBox,
                buttonBox,
                table,
                output
        );

        Scene dashboardScene =
                new Scene(
                        dashboardRoot,
                        900,
                        700
                );

        // ================= LOGIN BUTTON =================

        loginBtn.setOnAction(e -> {

            String username =
                    usernameField.getText();

            String password =
                    passwordField.getText();

            if(username.equals("admin")
                    &&
                    password.equals("admin123")) {

                stage.setScene(
                        dashboardScene
                );

            } else {

                loginMessage.setText(
                        "Invalid Credentials"
                );

                loginMessage.setTextFill(
                        Color.RED
                );
            }
        });

        stage.setScene(loginScene);

        stage.setTitle(
                "Hospital Management System"
        );

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}