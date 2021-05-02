package sample;

import java.util.*;
import java.text.*;
import java.io.*;
import javafx.event.*;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.sql.*;

public class Main extends Application {
    // Labels for aboutMenu
    private static Label aboutMenuItems1;
    private static Label aboutMenuItems2;
    private static Hyperlink aboutMenuItems3;
    // checkout apt num
    private static TextField aptNumFloor; // can be null
    // BorderPane
    private static BorderPane bPane;
    // Blank formatting label
    private static final Label blank = new Label("");
    // Store author data from database
    private static ArrayList<String> bookAuthor;
    // Observable object list for tableview
    private static ObservableList<BookCart> bookCart;
    // Store book names from database
    private static ArrayList<String> bookNames;
    // Observable list for ListView
    private static ObservableList<String> bookNamesList;
    // Store prices from database
    private static ArrayList<Double> bookPrices;
    // Cart window gridpane
    private static GridPane bottom;
    // Add to cart button
    private static Button cartButton;
    // Cart window borderpane
    private static BorderPane cartPane;
    // Cart window scene
    private static Scene cartScene;
    // Cart window stage
    private static Stage cartWindow;
    // Check out button
    private static Button checkOut;
    // Gridpane for checkout
    private static GridPane checkOutPane;
    // Scene for checkout
    private static Scene checkOutScene;
    // Stage for checkout
    private static Stage checkOutStage;
    // Get user city
    private static TextField city;
    // HBox for checkout
    private static HBox cityStateZip;
    // Clear cart
    private static Button clearAll;
    // Hbox for checkout
    private static HBox completeCheckOut;
    // database connection
    private static Connection connection;
    // Exit cart
    private static Button continueShopping;
    // currency format
    private static final NumberFormat curForm = NumberFormat
            .getCurrencyInstance();
    // Delete from cart button
    private static Button deleteButton;
    // Displays retreieved nam
    private static Label dispName;
    // Displays retrieved order number
    private static Label dispOrderNum;
    // Displays shipping status
    private static Label dispShipStat;
    // Get user fname
    private static TextField fName;
    // Final cost
    private static double finalCost;
    // Finish checkout
    private static Button finishCheckOut;
    // prints the selected title and price
    private static Label getTitlePrice;
    // GridPane
    private static GridPane grid;
    // HBox
    private static HBox hbox;
    // View images
    private static ImageView image;
    // Array to store Images
    private static Image[] imageArray;
    // More info button
    private static Button infoButton;
    // Get user last name
    private static TextField lName;
    // Store image links from database
    private static ArrayList<String> links;
    // Store blob images from database
    private static ArrayList<InputStream> imag;
    // ListView variable
    private static ListView<String> listView;
    // Name HBox for CheckOutFunction
    private static HBox nameBox;
    // Order number
    private static int newOrderNum;
    // Search order number
    private static int orderNumSearch;
    // Get order number for searching
    private static TextField orderSearch;
    private static TextField deleteField;
    private static TextField url;
    private static TextField title;
    private static TextField price;
    private static TextField sku;
    private static TextField manufacturer_no;
    private static TextField isbn;
    private static TextField format;
    private static TextField author;
    private static TextField year;
    private static TextField pages;
    private static TextField weight;
    private static TextField series_name;
    private static TextField other_cont;
    private static TextField title_series;
    private static TextField sequence;

    // Get Admin login for login
    private static TextField adminlogin;
    // Get Admin password for login
    private static PasswordField adminpassword;

    // Order status VBox
    private static VBox orderStatBox;
    // Order status borderpane
    private static BorderPane orderStatPane;
    // Order status scene
    private static Scene orderStatScene;
    // Orders status stage
    private static Stage orderStatStage;
    // Orders status apt num
    private static String orderAptNumFloor;
    // Orders city
    private static String orderCity;
    // Orders FName
    private static String orderFName;
    // Order gridpane
    private static GridPane orderGrid;
    // Orders LName
    private static String orderLName;
    // Get order numbers
    private static ArrayList<Integer> orderNum;
    // Orders state
    private static String orderState;
    // Orders address
    private static String orderStrAddr;
    // Orders zip code
    private static int orderZip;
    // Retrieve order VBox
    private static VBox retrieveBox;
    // Retrieve order GridPane
    private static GridPane retrieveGrid;
    // Retrieve borderpane
    private static BorderPane retrievePane;
    // Retrieve scene
    private static Scene retrieveScene;
    // Retrieve stage
    private static Stage retrieveStage;
    // Result sets for queries
    private static ResultSet rst;
    private static ResultSet rst2;
    private static ResultSet rst3;
    // Login button
    private static Button log;
    // Search button
    private static Button search;
    private static Button delete;
    private static Button addbook;
    // Name searched for
    private static String searchName;
    // Order number searched for
    private static int searchOrderNum;
    // Shipping status searched for
    private static String searchShipStat;
    // Tell user to select a book
    private static Label selectLabel;
    // Store selected book names for shopping cart
    private static ArrayList<String> selectedBookNames;
    // Store selected book prices for shopping cart
    private static ArrayList<Double> selectedBookPrices;
    // Store ISBN
    private static ArrayList<String> selectedISBN;
    // Users selection number
    private static int selectionNum;
    // Users shipped status
    private static String shipped;
    // Shipping cost
    private static final double shippingCost = 15.00;
    // Store ISBN
    private static ArrayList<String> sqlISBN;
    // List of states
    private static ComboBox stateBox;
    // Statements
    private static Statement stmt;
    private static Statement stmt2;
    // Get user str addr
    private static TextField strAddr;
    // TableView references BookCart object
    private static TableView<BookCart> table;
    // Tax 9%
    private static final double tax = 0.09;
    // Prints title: price:
    private static Label titlePrice;
    // Total cost of order
    private static double totalCost;
    // Totals
    private static Label totals;
    private static Label totals2;
    private static ArrayList<String> years;
    private static ArrayList<Integer> books;

    // List of states
    private static final ObservableList<String> kzStates =
            FXCollections.observableArrayList(
                    "ALA",//1
                    "AST",//2
                    "KZO",//3
                    "SHY",//4
                    "KOK",//5
                    "TAR",//6
                    "OSK",//7
                    "SEM",//8
                    "PET",//9
                    "ORA",//10
                    "ATY",//11
                    "AKT",//12
                    "ZHA",//13
                    "PAV",//14
                    "MAN",//15
                    "AKO"//16

            );
    // Get zip
    private static TextField zip;


    private void PieChart(MenuItem pie){
        pie.setOnAction((ActionEvent event) -> {
            try {
                HashMap<String,Integer> map = new HashMap<>();
                for(int i=0; i<years.size();i++){
                    if(map.containsKey(years.get(i))){
                        map.replace(years.get(i), map.get(years.get(i))+1);
                    }else{
                        map.put(years.get(i),1);
                    }
                }
                ArrayList<String> sorted = new ArrayList<>(map.keySet());
                Collections.sort(sorted, Collections.reverseOrder());
                PieChart pieChart = new PieChart();
                for(int i = 0;i<5;i++){
                    PieChart.Data slice = new PieChart.Data(sorted.get(i), map.get(sorted.get(i)));
                    pieChart.getData().add(slice);
                }
                final Label caption = new Label("");
                caption.setTextFill(Color.WHITE);
                caption.setStyle("-fx-font: 12 arial;");

                for (final PieChart.Data data : pieChart.getData()) {
                    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());

                            caption.setText(String.valueOf(data.getPieValue()));
                        }
                    });
                }

                pieChart.setLegendSide(Side.LEFT);

                AnchorPane root = new AnchorPane();
                root.getChildren().addAll(pieChart, caption);


                Scene secondScene = new Scene(root,500,400);
                Stage newWindow = new Stage();
                newWindow.setTitle("Pie Chart");
                newWindow.setScene(secondScene);
                // Display
                newWindow.show();
            } catch (Exception e) {

            }
        });
    }
    private void BarChart(MenuItem bar){
        bar.setOnAction((ActionEvent event) -> {
            try {

                HashMap<String,Integer> map = new HashMap<>();

                CategoryAxis xAxis    = new CategoryAxis();
                xAxis.setLabel("years");

                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Books");

                BarChart barChart = new BarChart(xAxis, yAxis);
                XYChart.Series dataSeries1 = new XYChart.Series();
//                dataSeries1.setName("2014");
                for(int i=0; i<years.size();i++){
                    if(map.containsKey(years.get(i))){
                        map.replace(years.get(i), map.get(years.get(i))+1);
                    }else{
                        map.put(years.get(i),1);
                    }
                }
                ArrayList<String> sorted = new ArrayList<>(map.keySet());
                Collections.sort(sorted);
                for(String e: sorted){
                    dataSeries1.getData().add(new XYChart.Data(e, map.get(e)));
                }


                barChart.getData().add(dataSeries1);
                VBox vbox = new VBox(barChart);

                Scene secondScene = new Scene(vbox,500,400);
                Stage newWindow = new Stage();
                newWindow.setTitle("Bar Chart");
                newWindow.setScene(secondScene);
                // Display
                newWindow.show();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });

    }
    // Adds items to cart button
    private void CartButtonFuction() {
        // Set array lists
        selectedBookNames = new ArrayList<>();
        selectedBookPrices = new ArrayList<>();
        selectedISBN = new ArrayList<>();
        // Start listening for mouse click
        cartButton.setOnMouseClicked((MouseEvent arg0) -> {
            if (selectionNum == 0) {
            }
            // Adds items to cart
            else {
                selectedBookNames.add(bookNames.get(listView
                        .getSelectionModel().getSelectedIndex()));
                selectedBookPrices.add(bookPrices.get(listView
                        .getSelectionModel().getSelectedIndex()));
                selectedISBN.add(sqlISBN.get(listView
                        .getSelectionModel().getSelectedIndex()));
            }
        });
        System.out.println("All with cart button func ok");
    }
    // Cart menu
    private void CartMenuFunction(MenuItem cartItem) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/buy.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        FileInputStream input2 = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/delete.png");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);

        FileInputStream input3 = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/back.png");
        Image image3 = new Image(input3);
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitHeight(30);
        imageView3.setFitWidth(30);

        FileInputStream input4 = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/clear.jpeg");
        Image image4 = new Image(input4);
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitHeight(30);
        imageView4.setFitWidth(30);
        // Enter cart
        cartItem.setOnAction((ActionEvent event) -> {
            // Check out button
            checkOut = new Button("", imageView);
            // Delete 1 item button
            deleteButton = new Button("", imageView2);
            // open cart button
            continueShopping = new Button("", imageView3);
            // Clear all button
            clearAll = new Button("", imageView4);
            // Set cart array list
            bookCart = FXCollections.observableArrayList();
            // Add selected items to cart
            for (int i = 0; i < selectedBookNames.size(); i++) {
                bookCart.add(new BookCart(selectedBookNames.get(i),
                        selectedBookPrices.get(i)));
            }
            // Get total cost
            GetTotal();
            // Print totals
            totals = new Label("\nTotal: " + curForm.format(totalCost)
                    + "\n" + "Shipping: " + curForm.format(shippingCost) + "\n" +
                    "Tax: " + curForm.format(totalCost * tax) + "\n" +
                    "\nGrand Total: " + curForm.format(finalCost));
            // Set new tableview item
            table = new TableView();
            // Book name column
            TableColumn bookNameCol = new TableColumn("Book Title        " +
                    "(Select row and press delete to remove item)");
            bookNameCol.setMinWidth(545);
            bookNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("bookTitle"));
            // Price column
            TableColumn lastNameCol = new TableColumn("Price");
            lastNameCol.setMinWidth(110);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("bookPrice"));
            // Set items into table
            table.setItems(bookCart);
            // Add columns to tableview
            table.getColumns().addAll(bookNameCol,lastNameCol);
            // Borderpane for cart
            cartPane = new BorderPane();
            // VBox for cart
            VBox cartBox = new VBox();
            // GridPane for cart
            bottom = new GridPane();
            // Rows and columns
            // 0
            bottom.getColumnConstraints().add(new ColumnConstraints(175));
            // 0
            bottom.getRowConstraints().add(new RowConstraints(100));
            // 1
            bottom.getColumnConstraints().add(new ColumnConstraints(150));
            // 1
            bottom.getRowConstraints().add(new RowConstraints(150));
            // 2
            bottom.getColumnConstraints().add(new ColumnConstraints(120));
            // 2
            bottom.getRowConstraints().add(new RowConstraints(30));
            // 3
            bottom.getColumnConstraints().add(new ColumnConstraints(120));
            // 3
            bottom.getRowConstraints().add(new RowConstraints(20));
            // Spacing
            bottom.setHgap(10);
            bottom.setVgap(5);
            bottom.setPadding(new Insets(0,10,0,10));
            // Set totals in gridpane
            bottom.add(totals, 0,0);
            // VBox
            VBox deleteClearBox = new VBox();
            // Spacing
            deleteClearBox.setSpacing(10);
            // Add items to VBox
            deleteClearBox.getChildren().addAll(blank,deleteButton,clearAll);
            // Delete single item function
            DeleteFunction();
            // Clear all items function
            ClearAll();
            // Add to gridpane
            bottom.add(deleteClearBox,1,0);
            bottom.add(continueShopping,2,0);
            // Continue shopping button function
            KeepShoppingFunction();
            // Add checkout button to gridpane
            bottom.add(checkOut, 3,0);
            // Checkout button functionality
            CheckOutFunction();
            // Add table and grid to VBox
            cartBox.getChildren().addAll(table,bottom);
            // Set location
            cartPane.setTop(cartBox);
            // Create scene
            cartScene = new Scene(cartPane,650,535,Color.ANTIQUEWHITE);
            // Create stage
            cartWindow = new Stage();
            // Title
            cartWindow.setTitle("Cart");
            cartWindow.setScene(cartScene);
            // Display
            cartWindow.show();
        });
    }
    private void CheckOutFunction() {
        // Set combobox
        stateBox = new ComboBox(kzStates);
        // Action

        checkOut.setOnAction((ActionEvent event) -> {
            if(totalCost>0){
            completeCheckOut = new HBox();
            nameBox = new HBox();
            cityStateZip = new HBox();
            totals2 = new Label();
            // Text fields
            fName = new TextField();
            fName.setPromptText("First Name");
            lName = new TextField();
            lName.setPromptText("Last Name");
            strAddr = new TextField();
            strAddr.setPromptText("Street Address");
            aptNumFloor = new TextField();
            aptNumFloor.setPromptText("Apartment #/Floor (Can leave empty)");
            city = new TextField();
            city.setPromptText("City");
            zip = new TextField();
            zip.setPromptText("Zip");
            finishCheckOut = new Button("Complete Buy");
            // Recalculate totals
            GetTotal();
            // Print totals
            totals2.setText("\nTotal: " + curForm.format(totalCost)
                    + "\n" + "Shipping: " + curForm.format(shippingCost) + "\n" +
                    "Tax: " + curForm.format(totalCost * tax) + "\n" +
                    "\nGrand Total: " + curForm.format(finalCost));
            // Gridpane
            checkOutPane = new GridPane();
            // 0
            checkOutPane.getColumnConstraints().add(new ColumnConstraints(20));
            // 0
            checkOutPane.getRowConstraints().add(new RowConstraints(20));
            // 1
            checkOutPane.getColumnConstraints().add(
                    new ColumnConstraints(450));
            // 1
            checkOutPane.getRowConstraints().add(new RowConstraints(40));
            // 2
            checkOutPane.getRowConstraints().add(new RowConstraints(40));
            // 3
            checkOutPane.getRowConstraints().add(new RowConstraints(40));
            // 4
            checkOutPane.getRowConstraints().add(new RowConstraints(40));
            // 5
            checkOutPane.getRowConstraints().add(new RowConstraints(20));
            // 6
            checkOutPane.getRowConstraints().add(new RowConstraints(300));
            // Format text boxes
            nameBox.getChildren().addAll(fName,lName);
            nameBox.setSpacing(10);
            cityStateZip.getChildren().addAll(city,stateBox,zip);
            stateBox.setValue("ALA"); // Initial statebox value
            completeCheckOut.getChildren().addAll(finishCheckOut, totals2);
            cityStateZip.setPadding(new Insets(15, 0, 0, 0));
            cityStateZip.setSpacing(10);
            completeCheckOut.setPadding(new Insets(15,0,0,0));
            completeCheckOut.setSpacing(30);
            checkOutPane.add(nameBox, 1, 1);
            checkOutPane.add(strAddr, 1, 2);
            checkOutPane.add(aptNumFloor, 1, 3);
            checkOutPane.add(cityStateZip, 1, 4);
            checkOutPane.add(completeCheckOut, 1, 6);
            // Send checkout to database
            try {
                FinishCheckOutFunction();
            } catch (SQLException ex) {
                System.out.println("Error");
            }
            // Display
            checkOutScene = new Scene(checkOutPane,500,350,Color.ANTIQUEWHITE);
            checkOutStage = new Stage();
            checkOutStage.setTitle("Buy");
            totals2.requestFocus();
            checkOutStage.setScene(checkOutScene);
            checkOutStage.show();
            }
        });
    }
    // Clear all function
    private void ClearAll() {
        clearAll.setOnAction((ActionEvent event) -> {
            // removes everything from bookCart, selectedBookNames,
            // selectedBookPrices, selected ISBN
            bookCart.removeAll(bookCart);
            selectedBookNames.removeAll(selectedBookNames);
            selectedBookPrices.removeAll(selectedBookPrices);
            selectedISBN.removeAll(selectedISBN);
            GetTotal();
            totals.setText("\nTotal: " + curForm.format(totalCost) + "\n" +
                    "Shipping: " + curForm.format(shippingCost) + "\n" +
                    "Tax: " + curForm.format(totalCost * tax) + "\n" +
                    "\nGrand Total: " + curForm.format(finalCost));
        });
    }
    private void DBConnect() throws ClassNotFoundException, SQLException,
            IOException {
        // Set lists for saving data
        bookNames = new ArrayList<>();
        bookPrices = new ArrayList<>();
        links = new ArrayList<>();
        imag = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        sqlISBN = new ArrayList<>();
        orderNum = new ArrayList<>();
        books = new ArrayList<>();
        years = new ArrayList<>();
        // Start connection
        try {
            // Register the Oracle JDBC drivers
            DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver()
            );
            System.out.println("Driver loaded");
            // Establish a connection to the remote database
            connection = DriverManager.getConnection(
                    // IP address of the Oracle1 server, port 1521, SID name
                    "jdbc:oracle:thin:@localhost:1521:orclcdb",
                    "c##super",    // A generic Oracle account
                    "super"
            );
            System.out.println("Database connected");

            // Create a statement
            stmt = connection.createStatement();
            // Query
            String orderQry = "{? = call cur_book()}";
            CallableStatement searchStatement
                    = connection.prepareCall(orderQry);
            searchStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            searchStatement.execute();
            rst = (ResultSet) searchStatement.getObject(1);

            while (rst.next()) {
                if(rst.getObject(1)!=null && rst.getObject(2)!=null && rst.getObject(3)!=null && rst.getObject(4)!=null && rst.getObject(5)!=null) {
                    bookNamesList = FXCollections.observableArrayList(bookNames);
                    bookNames.add(rst.getObject(1).toString());
                    bookPrices.add(Double.parseDouble(rst.getObject(2).toString()));
                    links.add(rst.getObject(3).toString());
                    bookAuthor.add(rst.getObject(4).toString());
                    years.add(rst.getObject(7).toString());
                    sqlISBN.add(rst.getObject(5).toString());
                }
                if(rst.getObject(6)!=null){
                    imag.add(((Blob)rst.getObject(6)).getBinaryStream());

                }
            }
            // Another query to generate new order numbers
            bookNamesList = FXCollections.observableArrayList(bookNames);
            String orderNumQuery = "{? = call order_num()}";
            CallableStatement searchNum
                    = connection.prepareCall(orderNumQuery);
            searchNum.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            searchNum.execute();
            rst2 = (ResultSet) searchNum.getObject(1);
            while (rst2.next()) {
                orderNum.add(rst2.getInt(1));
            }
            System.out.println("All with DB ok");
        } catch (SQLException ex) {}
    }
    // Delete individual items
    private void DeleteFunction() {
        totals2 = new Label();
        deleteButton.setOnAction((ActionEvent event) -> {
            // Remove from selected index
            selectedBookNames.remove(table.getSelectionModel()
                    .getSelectedIndex());
            selectedBookPrices.remove(table.getSelectionModel()
                    .getSelectedIndex());
            selectedISBN.remove(table.getSelectionModel()
                    .getSelectedIndex());
            // Reset list
            bookCart.removeAll(bookCart);
            // Refill list with updated cart
            for (int i = 0; i < selectedBookNames.size(); i++) {
                bookCart.add(new BookCart(selectedBookNames.get(i),
                        selectedBookPrices.get(i)));
            }
            // Recalculate totals
            GetTotal();
            totals.setText("\nTotal: " + curForm.format(totalCost) + "\n" +
                    "Shipping: " + curForm.format(shippingCost) + "\n" +
                    "Tax: " + curForm.format(totalCost * tax) + "\n" +
                    "\nGrand Total: " + curForm.format(finalCost));
            totals2.setText("\nTotal: " + curForm.format(totalCost) + "\n" +
                    "Shipping: " + curForm.format(shippingCost) + "\n" +
                    "Tax: " + curForm.format(totalCost * tax) + "\n" +
                    "\nGrand Total: " + curForm.format(finalCost));
        });
    }
    // Exit menu
    private void ExitMenuFunction(MenuItem exitMenuItem) {
        // Exit if clicked
        exitMenuItem.setOnAction(ActionEvent -> Platform.exit());
    }
    // Fills image list
    private void FillImageList() {
        // Make array expandable
        imageArray = new Image[links.size()];
        // Fill
        for(int i = 0; i < 20; i++) {
            imageArray[i] = new Image(imag.get(i));
        }

    }
    // Final checkout
    private void FinishCheckOutFunction() throws SQLException {
        // labels for unnacceptable inputs
        Label error = new Label("Error! 1 or more field(s) are Null!");
        Label error2 = new Label("Error! Zip code entry invalid");
        // BorderPane for errors
        BorderPane errorPane = new BorderPane();
        // Scene/Stage for errors
        Scene errorScene = new Scene(errorPane,300,75);
        Stage errorWindow = new Stage();
        // Listen for complete check out
        finishCheckOut.setOnAction((ActionEvent event) -> {
            // If any fields are empty error
            if (fName.getText().trim().isEmpty() ||
                    lName.getText().trim().isEmpty() ||
                    strAddr.getText().trim().isEmpty() ||
                    city.getText().trim().isEmpty() ||
                    zip.getText().trim().isEmpty()) {
                errorPane.setCenter(error);
                errorWindow.setScene(errorScene);
                errorWindow.show();
            }
            // If zip code is not valid error
            else if (isValidZip(zip.getText()) == false) {
                errorPane.setCenter(error2);
                errorWindow.setScene(errorScene);
                errorWindow.show();
            }
            // Send information to the database
            else {
                // Getting information from the textfields
                // Converts text field to integer
                orderZip = Integer.parseInt(zip.getText());
                orderFName = fName.getText();
                orderLName = lName.getText();
                orderStrAddr = strAddr.getText();
                orderAptNumFloor = aptNumFloor.getText();
                orderCity = city.getText();
                orderState = String.valueOf(stateBox.getValue());
                newOrderNum = orderNum.size() + 1;
                shipped = "NO";
                // Uploads book orders to the database
                for (int x = 0; x < selectedISBN.size(); x++) {
                    try {
                        String stringQry = "{call buy(?,?,?,?,?,?,?,?,?,?,?)}";
                        CallableStatement checkOutState =
                                connection.prepareCall(stringQry);

                        checkOutState.setString(1, orderFName.toUpperCase());
                        checkOutState.setString(2, orderLName.toUpperCase());
                        checkOutState.setString(3, orderStrAddr.toUpperCase());
                        checkOutState.setString(4, orderAptNumFloor
                                .toUpperCase());
                        checkOutState.setString(5, orderCity.toUpperCase());
                        checkOutState.setString(6, orderState.toUpperCase());
                        checkOutState.setInt(7, orderZip);
                        checkOutState.setInt(8, newOrderNum);
                        checkOutState.setString(9, selectedISBN.get(x));
                        checkOutState.setString(10, shipped);
                        checkOutState.setDouble(11, finalCost);
                        checkOutState.execute();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                try {
                    // Prints customers order number
                    GiveOrderNum();
                } catch (SQLException ex) {

                }
                cartWindow.close();
                checkOutStage.close();
            }
        });
    }
    // More info button
    private void FullInfoMenu(Stage stage) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/info.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        // Button to enter more info menu
        infoButton = new Button("", imageView);

        aboutMenuItems2.setWrapText(true);
        infoButton.setOnAction((ActionEvent event) -> {
            GridPane pane = new GridPane();
            pane.getColumnConstraints().add(new ColumnConstraints(25));
            pane.getRowConstraints().add(new RowConstraints(5));
            pane.getColumnConstraints().add(new ColumnConstraints(200));
            pane.getRowConstraints().add(new RowConstraints(70));
            pane.getColumnConstraints().add(new ColumnConstraints(200));
            pane.getRowConstraints().add(new RowConstraints(50));
            pane.setHgap(20);
            pane.setVgap(20);
            pane.add(aboutMenuItems1, 1,1);
            pane.add(aboutMenuItems2, 2,1);
            pane.add(aboutMenuItems3, 1,2);
            Scene thirdScene = new Scene(pane, 500, 200);
            Stage anotherWindow = new Stage();
            anotherWindow.setTitle("More Info");
            anotherWindow.setScene(thirdScene);
            anotherWindow.setX(stage.getX() + 200);
            anotherWindow.setY(stage.getY() + 100);
            anotherWindow.show();
        });
    }
    // Customer get order
    private void GetOrder() {
        retrieveBox = new VBox();
        retrieveGrid = new GridPane();
        retrievePane = new BorderPane();
        // Start search query
        search.setOnAction((ActionEvent event) -> {
            orderNumSearch = Integer.parseInt(orderSearch.getText());
            try {
                // Prepared statement, allows use of variables
                String orderQry = "{? = call cur(?)}";
                CallableStatement searchStatement
                        = connection.prepareCall(orderQry);
                searchStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                searchStatement.setInt(2, orderNumSearch);
                searchStatement.execute();
                rst3 = (ResultSet) searchStatement.getObject(1);

                // Get values for use in program
                while (rst3.next()) {
                    searchOrderNum = Integer.parseInt(rst3.getObject(1).toString());
                    searchName =rst3.getObject(2).toString();
                    searchShipStat = (String)rst3.getObject(4).toString();
                }
            } catch (Exception SQLException) {
                System.out.println(SQLException);
            }
            // Displays order number
            dispOrderNum = new Label("ORDER # " + searchOrderNum);
            dispOrderNum.setFont(new Font("Arial", 13));
            // Displays name on order
            dispName = new Label("Name: " + searchName);
            dispName.setFont(new Font("Arial", 13));
            // Displays shipping status
            dispShipStat = new Label("Shipped Status: " + searchShipStat);
            dispShipStat.setFont(new Font("Arial", 13));
            // Gridpane columns and rows
            // 0
            retrieveGrid.getColumnConstraints().add(new ColumnConstraints(20));
            // 0
            retrieveGrid.getRowConstraints().add(new RowConstraints(20));
            // 1
            retrieveGrid.getColumnConstraints().add(new
                    ColumnConstraints(250));
            // 1
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // 2
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // 3
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // Add items to gridpane
            retrieveGrid.add(dispOrderNum,1,1);
            retrieveGrid.add(dispName,1,2);
            retrieveGrid.add(dispShipStat,1,3);

            retrieveBox.getChildren().addAll(retrieveGrid);
            retrievePane.setTop(retrieveBox);
            retrieveScene = new Scene(retrievePane,300,200,Color.ANTIQUEWHITE);
            retrieveStage = new Stage();
            retrieveStage.setTitle("Order Status");
            retrieveStage.setScene(retrieveScene);
            retrieveStage.show();
        });
    }
    private void Login() {
        log.setOnAction((ActionEvent event) -> {
            if(adminlogin.getText().equals("admin") && adminpassword.getText().equals("admin")) {
                try {
                    updateShipping();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Admin get order (Allows user to update shipping status)
    private void GetOrderAndUpdate() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/update.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        // Button for updating shipping status
        Button update = new Button("", imageView);
        // Update button functionality
        UpdateShippedButton(update);
        retrieveBox = new VBox();
        retrieveGrid = new GridPane();
        retrievePane = new BorderPane();
        search.setOnAction((ActionEvent event) -> {
            // Convert to integer from textbox
            orderNumSearch = Integer.parseInt(orderSearch.getText());
            // Query
            try {
                String orderQ = "{? = call cur(?)}";
                CallableStatement searchS
                        = connection.prepareCall(orderQ);
                searchS.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                searchS.setInt(2, orderNumSearch);
                searchS.execute();
                rst3 = (ResultSet) searchS.getObject(1);
                while (rst3.next()) {
                    searchOrderNum = rst3.getInt(1);
                    searchName = rst3.getString(2)+" "+rst3.getString(3);
                    searchShipStat = rst3.getString(4);
                }
            } catch (SQLException SQLException) {}
            // Display queried info
            dispOrderNum = new Label("ORDER # " + searchOrderNum);
            dispOrderNum.setFont(new Font("Arial", 13));
            dispName = new Label("Name: " + searchName);
            dispName.setFont(new Font("Arial", 13));
            dispShipStat = new Label("Shipped Status: " + searchShipStat);
            dispShipStat.setFont(new Font("Arial", 13));
            // 0
            retrieveGrid.getColumnConstraints().add(new ColumnConstraints(20));
            // 0
            retrieveGrid.getRowConstraints().add(new RowConstraints(20));
            // 1
            retrieveGrid.getColumnConstraints().add(new
                    ColumnConstraints(250));
            // 1
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // 2
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // 3
            retrieveGrid.getRowConstraints().add(new RowConstraints(40));
            // 2
            retrieveGrid.getColumnConstraints().add(new ColumnConstraints(150));
            retrieveGrid.add(dispOrderNum,1,1);
            retrieveGrid.add(dispName,1,2);
            retrieveGrid.add(dispShipStat,1,3);
            retrieveGrid.add(update,2,3);
            retrieveBox.getChildren().addAll(retrieveGrid);
            retrievePane.setTop(retrieveBox);
            retrieveScene = new Scene(retrievePane,425,200,Color.ANTIQUEWHITE);
            retrieveStage = new Stage();
            retrieveStage.setTitle("Order Status");
            retrieveStage.setScene(retrieveScene);
            retrieveStage.show();
        });
        delete.setOnAction((ActionEvent event2) -> {
            orderNumSearch = Integer.parseInt(deleteField.getText());
            // Query
            try {
                String sqlDelete =
                        "{call delete_books(?)}";
                CallableStatement searchStatement =
                        connection.prepareCall(sqlDelete);
                searchStatement.setInt(1, orderNumSearch);


                searchStatement.execute();

            } catch (SQLException SQLException) {
                System.out.println(SQLException);
            }
            deleteField.setText("");


        });

    }
    // Calculate totals
    private void GetTotal() {
        try {
            if(selectedBookPrices.size()!=0) {
                CallableStatement cs = connection.prepareCall("{? = call total_cost(?)}");

                cs.registerOutParameter(1, OracleTypes.NUMBER);
                ArrayDescriptor arrDesc =
                        ArrayDescriptor.createDescriptor("INTS", connection);
                int[] arr = new int[selectedBookPrices.size()];
                for (int i = 0; i < selectedBookPrices.size(); i++) {
                    double d = selectedBookPrices.get(i);
                    arr[i] = (int) d;
                }
                // System.out.println(selectedBookPrices.get(1));
                Array array = new ARRAY(arrDesc, connection, arr);
                cs.setArray(2, array);
                cs.execute();
                totalCost = cs.getInt(1);
                finalCost = totalCost * tax + totalCost + shippingCost;
            }
            else
            {
                totalCost = 0;
                finalCost = 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    // Give user their order number after checkout
    private void GiveOrderNum() throws SQLException {
        Stage orderStage = new Stage();
        orderStage.setTitle("Order #");
        GridPane orderGrid2 = new GridPane();
        Label yourOrderNum = new Label("Your order number is " +
                newOrderNum + "\n\n!!SAVE THIS NUMBER!!");
        yourOrderNum.setFont(new Font("Arial",14));
        BorderPane orderPane = new BorderPane();
        orderGrid2.getColumnConstraints().add(new ColumnConstraints(30));
        orderGrid2.getRowConstraints().add(new RowConstraints(30));
        orderGrid2.add(yourOrderNum, 1, 1);
        orderPane.setCenter(orderGrid2);
        Scene orderScene = new Scene(orderPane,250,150,Color.ANTIQUEWHITE);
        orderStage.setScene(orderScene);
        orderStage.show();
    }
    // Main Menu gridpane
    private void GridPaneAdd() throws FileNotFoundException {
        selectLabel = new Label("Bakdaulet Berdikul\n\nOtep Abylay\n\nAdlet Askarov\n\nAzamat Abishev");
        selectLabel.setWrapText(true);
        selectLabel.setFont(Font.font("Source Code Pro", 12));
        // Set selected picture size
        image.setFitWidth(155);
        image.setFitHeight(240);
        // Cache for performance
        image.setCache(true);
        grid = new GridPane();

        // Column constraints for grid formatting
        grid.getColumnConstraints().add(new ColumnConstraints(120));
        grid.getRowConstraints().add(new RowConstraints(110));
        grid.getColumnConstraints().add(new ColumnConstraints(0));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(0));
        grid.getRowConstraints().add(new RowConstraints(90));
        grid.getColumnConstraints().add(new ColumnConstraints(5));
        grid.setHgap(0);
        grid.setVgap(20);
        grid.setPadding(new Insets(0,30,0,30));
        // Add image and button to grid
        grid.add(image, 2, 1);
        grid.add(titlePrice, 0, 3);
        grid.add(getTitlePrice, 4, 3);
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/cart.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        // Set cart button
        cartButton = new Button("",imageView);
        grid.add(cartButton, 4, 4);
        grid.add(infoButton, 0, 4);
        grid.add(selectLabel, 0, 0);
        System.out.println("All with grid pane are ok");
    }
    // open cart
    private void KeepShoppingFunction() {
        continueShopping.setOnAction((ActionEvent event) -> {
            cartWindow.close();
        });
    }
    // Layout main menu
    private void Layout() {
        bPane = new BorderPane();
        hbox = new HBox();
        hbox.getChildren().addAll(listView, grid);
        bPane.setLeft(hbox);
        System.out.println("All with Layout is ok");
    }
    // Fill listview
    private void ListView() {

        listView = new ListView<>(bookNamesList);
        // Make only one item selectable
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println("All with List View ok");
    }
    // Listview functionality
    private void ListViewFunction() {
        aboutMenuItems1 = new Label();
        aboutMenuItems2 = new Label();
        aboutMenuItems3 = new Hyperlink();
        titlePrice = new Label();
        getTitlePrice = new Label();
        getTitlePrice.setWrapText(true);
        image = new ImageView();
        // Set on mouse click action
        listView.setOnMouseClicked((MouseEvent arg0) -> {
            selectLabel.setText("");
            image.setImage(imageArray[listView.getSelectionModel()
                    .getSelectedIndex()]);
            titlePrice.setText("Title:\n\n" + "Price: ");
            getTitlePrice.setText(bookNames.get(listView.getSelectionModel()
                    .getSelectedIndex()) + "\n\n" + curForm.format(bookPrices.get(
                    listView.getSelectionModel().getSelectedIndex()
            )));
            selectionNum = listView.getSelectionModel().getSelectedIndex() + 1;
            aboutMenuItems1.setText("Title:\n\n" + "Price: ");
            aboutMenuItems2.setText(bookNames.get(listView.getSelectionModel()
                    .getSelectedIndex()) + "\n\n" + curForm.format(bookPrices.get(
                    listView.getSelectionModel().getSelectedIndex()
            )));
            aboutMenuItems3.setText(links.get(
                    listView.getSelectionModel().getSelectedIndex()));
            aboutMenuItems3.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    getHostServices().showDocument(aboutMenuItems3.getText());
                }
            });
        });
        System.out.println("All with List View Function ok");
    }
    private void MenuBar(Stage stage, BorderPane root) throws FileNotFoundException {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        // set menubar on top
        root.setTop(menuBar);
        // File menu item
        Menu fileMenu = new Menu("File");
        // Add exit item
        MenuItem exitMenuItem = new MenuItem("Exit");
        ExitMenuFunction(exitMenuItem);
        fileMenu.getItems().addAll(exitMenuItem);

        // Cart menu
        Menu cartMenu = new Menu("Cart");
        MenuItem cartItem = new MenuItem("Shopping Cart");
        MenuItem orderItem = new MenuItem("Order Status");
        CartMenuFunction(cartItem);
        OrderStatus(orderItem);
        cartMenu.getItems().addAll(cartItem, orderItem);
        // Admin menu
        Menu adminMenu = new Menu("Admin");
        MenuItem login = new MenuItem("Login");
        AdminLogin(login);
        adminMenu.getItems().addAll(login);
        //BarChart
        Menu barchart = new Menu("Statistics");
        MenuItem bar = new MenuItem("Bar Chart");
        MenuItem pie = new MenuItem("Pie Chart");
        PieChart(pie);
        BarChart(bar);
        barchart.getItems().addAll(bar, pie);
        //Filter
        Menu filter = new Menu("Filter");
        MenuItem byName = new MenuItem("By Name");
        MenuItem byYears = new MenuItem("By Year");
        filter.getItems().addAll(byName,byYears);
        FilterByName(byName);
        FilterByYears(byYears);
        // Menu bar
        menuBar.getMenus().addAll(fileMenu, cartMenu, adminMenu, barchart,filter);
        System.out.println("Menu Bar is  ok");
    }
    // Search for order status
    private void OrderStatus(MenuItem orderItem) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/search.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        orderItem.setOnAction((ActionEvent event) -> {
            searchOrderNum = 0;
            searchName = null;
            searchShipStat = null;
            search = new Button("", imageView);
            orderGrid = new GridPane();
            orderStatBox = new VBox();
            orderSearch = new TextField();
            // Ask user to enter their order number
            orderSearch.setPromptText("Enter Order #");
            // 0
            orderGrid.getColumnConstraints().add(new ColumnConstraints(20));
            // 0
            orderGrid.getRowConstraints().add(new RowConstraints(20));
            // 1
            orderGrid.getColumnConstraints().add(new ColumnConstraints(160));
            // 1
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            // 2
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            orderGrid.add(orderSearch,1,1);
            orderGrid.add(search,1,2);
            // Gets order status
            GetOrder();
            orderStatBox.getChildren().addAll(orderGrid);
            orderStatPane = new BorderPane();
            orderStatPane.setTop(orderStatBox);
            orderStatScene = new Scene(orderStatPane,300,150,Color.ANTIQUEWHITE);
            orderStatStage = new Stage();
            search.requestFocus();
            orderStatStage.setTitle("Order Status");
            orderStatStage.setScene(orderStatScene);
            orderStatStage.show();
        });
    }
    // Allows admin to update shipping status
    private void UpdateShippedButton(Button update) {
        update.setOnAction((ActionEvent event) -> {
            // Update query
            try {
                String sqlUpdate =
                        "{call shipped(?,?)}";
                CallableStatement searchStatement =
                        connection.prepareCall(sqlUpdate);
                searchStatement.setInt(2, orderNumSearch);
                searchStatement.registerOutParameter(1, Types.VARCHAR);
                searchStatement.setString(1, "YES");
                searchStatement.execute();
                dispShipStat.setText("Shipped Status: YES");
            } catch (SQLException SQLException) {
                System.out.println(SQLException);
            }
        });
    }
    //Filter By Name
    private void FilterByName(MenuItem byName){
        byName.setOnAction((ActionEvent event) ->{
            bookNamesList.sort(Comparator.comparing(String::toString));
        });
    }
    //Filter By Years
    private void FilterByYears(MenuItem byYears){
        byYears.setOnAction((ActionEvent event) ->{

        });
    }
    //Login Admin
    private void AdminLogin(MenuItem login) {
        login.setOnAction((ActionEvent event) -> {

            log = new Button("Login");
            orderGrid = new GridPane();
            orderStatBox = new VBox();
            adminlogin = new TextField();
            adminpassword = new PasswordField();
            // Ask user to enter their order number
            adminlogin.setPromptText("Login");
            adminpassword.setPromptText("Password");
            // 0
            orderGrid.getColumnConstraints().add(new ColumnConstraints(40));
            // 0
            orderGrid.getRowConstraints().add(new RowConstraints(20));
            // 1
            orderGrid.getColumnConstraints().add(new ColumnConstraints(180));
            // 1
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            // 2
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            orderGrid.add(adminlogin,1,1);
            orderGrid.add(adminpassword,1,2);
            orderGrid.add(log,1,3);
            // Gets order status
            Login();

            orderStatBox.getChildren().addAll(orderGrid);
            orderStatPane = new BorderPane();
            orderStatPane.setTop(orderStatBox);
            orderStatScene = new Scene(orderStatPane,300,150,Color.ANTIQUEWHITE);
            orderStatStage = new Stage();
            log.requestFocus();
            orderStatStage.setTitle("Admin Login");
            orderStatStage.setScene(orderStatScene);
            orderStatStage.show();
        });
    }
    // Check if valid zipcode
    private static boolean isValidZip(String validZip) {
        if (validZip.length() != 5) {
            return false;
        }
        return validZip.chars().allMatch(Character::isDigit);
    }
    // Main
    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {
        launch(args);
    }
    // Main start
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException,
            SQLException {
        // Title main menu
        stage.setTitle("Book Store");
        stage.setWidth(700);
        stage.setHeight(535);
        // Connect to database
        DBConnect();
        // Fill image list
        FillImageList();
        // Generate listview
        ListView();
        // Provide listview function
        ListViewFunction();
        FullInfoMenu(stage);
        // Setup gridpane
        GridPaneAdd();
        // Generate layout
        Layout();
        CartButtonFuction();
        // Generate and add menubar
        MenuBar(stage,bPane);
        // Display

        Scene scene = new Scene(bPane,350,250,Color.ALICEBLUE);
        stage.setScene(scene);
        stage.show();
    }
    // Copy of GetOrder() but for accessing admin menu
    private void updateShipping() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/search.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(30);

        FileInputStream input2 = new FileInputStream("/Users/bakdauletberdikul/IdeaProjects/FinalDBMS2/src/sample/add.jpeg");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);
            searchOrderNum = 0;
            searchName = null;
            searchShipStat = null;
            search = new Button("", imageView);
            delete = new Button("Delete Book");
            addbook = new Button("", imageView2);
            orderGrid = new GridPane();
            orderStatBox = new VBox();
            orderSearch = new TextField();
            deleteField = new TextField();

            url = new TextField();
            title = new TextField();
            price = new TextField();
            sku = new TextField();
            manufacturer_no = new TextField();
            isbn = new TextField();
            format= new TextField();
            author = new TextField();
            year = new TextField();
            pages = new TextField();
            weight = new TextField();
            series_name = new TextField();
            other_cont = new TextField();
            title_series = new TextField();
            sequence = new TextField();

        url.setPromptText("book url");
        title.setPromptText("title");
        price.setPromptText("price");
        sku.setPromptText("sku");
        manufacturer_no.setPromptText("manufacturer number");
        isbn.setPromptText("isbn");
        format.setPromptText("format");
        author.setPromptText("author");
        year.setPromptText("year");
        pages.setPromptText("pages");
        weight.setPromptText("weight kg");
        series_name.setPromptText("series name");
        other_cont.setPromptText("other contributions");
        title_series.setPromptText("title series");
        sequence.setPromptText("sequence");

            deleteField.setPromptText("Enter ID");
            orderSearch.setPromptText("Enter Order #");
            //0
            orderGrid.getColumnConstraints().add(new ColumnConstraints(20));
            //0
            orderGrid.getRowConstraints().add(new RowConstraints(20));
            //1
            orderGrid.getColumnConstraints().add(new ColumnConstraints(160));
            //1
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            //2
            orderGrid.getRowConstraints().add(new RowConstraints(40));
            orderGrid.add(orderSearch,1,1);
            orderGrid.add(search,2,1);
            orderGrid.add(deleteField,1,2);
            orderGrid.add(delete,2,2);
        orderGrid.add(new Label("Add Book"),1,4);

        orderGrid.add(url,1,5);
        orderGrid.add(title,1,6);
        orderGrid.add(price,1,7);
        orderGrid.add(sku,1,8);
        orderGrid.add(manufacturer_no,1,9);
        orderGrid.add(isbn,1,10);
        orderGrid.add(format,1,11);
        orderGrid.add(author,2,4);
        orderGrid.add(year,2,5);
        orderGrid.add(pages,2,6);
        orderGrid.add(weight,2,7);
        orderGrid.add(series_name,2,8);
        orderGrid.add(other_cont,2,9);
        orderGrid.add(title_series,2,10);
        orderGrid.add(sequence,2,11);
        orderGrid.add(addbook, 2, 12);


            GetOrderAndUpdate();
            AddBooks(addbook);
            orderStatBox.getChildren().addAll(orderGrid);
            orderStatPane = new BorderPane();
            orderStatPane.setTop(orderStatBox);
            orderStatScene = new Scene(orderStatPane,500,400,Color.ANTIQUEWHITE);
            orderStatStage = new Stage();

            orderStatStage.setTitle("Control Panel");
            orderStatStage.setScene(orderStatScene);
            orderStatStage.show();

    }
    private void AddBooks(Button addbook){
        addbook.setOnAction((ActionEvent event)->{

                try {
                    String stringQry = "{call add_books(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    CallableStatement checkOutState =
                            connection.prepareCall(stringQry);

                    checkOutState.setString(1, url.getText());
                    checkOutState.setString(2, title.getText());
                    checkOutState.setInt(3, Integer.parseInt(price.getText()));
                    checkOutState.setInt(4, Integer.parseInt(sku.getText()));
                    checkOutState.setString(5, manufacturer_no.getText());
                    checkOutState.setString(6, isbn.getText());
                    checkOutState.setString(7, format.getText());
                    checkOutState.setString(8, author.getText());
                    checkOutState.setInt(9, Integer.parseInt(year.getText()));
                    checkOutState.setString(10, pages.getText());
                    checkOutState.setString(11, weight.getText());
                    checkOutState.setString(12, series_name.getText());
                    checkOutState.setString(13, other_cont.getText());
                    checkOutState.setString(14, title_series.getText());
                    checkOutState.setString(15, sequence.getText());
                    checkOutState.execute();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            url.setText("");
            title.setText("");
            price.setText("");
            sku.setText("");
            manufacturer_no.setText("");
            isbn.setText("");
            format.setText("");
            author.setText("");
            year.setText("");
            pages.setText("");
            weight.setText("");
            series_name.setText("");
            other_cont.setText("");
            title_series.setText("");
            sequence.setText("");

        });
    }
    // Tableview object class
    public static class BookCart {
        public final SimpleStringProperty bookTitle;
        public final SimpleDoubleProperty bookPrice;
        public BookCart(String bookTitle, Double bookPrice) {
            this.bookTitle = new SimpleStringProperty(bookTitle);
            this.bookPrice = new SimpleDoubleProperty(bookPrice);
        }
        public String getBookTitle() {
            return bookTitle.get();
        }
        public Double getBookPrice() {
            return bookPrice.get();
        }
        public void setBookTitle(String bookTitle) {
            this.bookTitle.set(bookTitle);
        }
        public void setBookPrice(Double bookPrice) {
            this.bookPrice.set(bookPrice);
        }
    }
}
