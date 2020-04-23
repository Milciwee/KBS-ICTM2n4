package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

    // static String[] dropdownitemsdesign = new String[50];
    // //beschikbare ontwerpen
    // static String[] dropdownitemsedit = new String[50];
    static ArrayList<String> dropdownitemsedit = new ArrayList<>();
    static ArrayList<String> dropdownitemsdesign = new ArrayList<>();
    static JTextField jtfDesnameEdit = new JTextField(); // designnaam
    static JTextField jtfWs1 = new JTextField();
    static JTextField jtfWs2 = new JTextField();
    static JTextField jtfWs3 = new JTextField();
    static JTextField jtfDb1 = new JTextField();
    static JTextField jtfDb2 = new JTextField();
    static JTextField jtfDb3 = new JTextField();
    static JTextField jtfCalculateAnswer = new JTextField();
    static JTextField jtfavailability = new JTextField();
    static JTextField jtfOptimizeAnswer = new JTextField();
    static JLabel jlDesignName = new JLabel("");
    static JComboBox dropdowndesign;
    static JComboBox dropdownedit;
    JButton jbCalculate = new JButton("Calculate");
    JButton jbOptimize = new JButton("Optimize");
    JButton jbDelete = new JButton("Delete");
    JButton jbSave = new JButton("Save");
    JButton jbSaveAs = new JButton("Save as new Design");
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel monitorPanel = new JPanel();
    static JPanel designPanel = new JPanel();
    JPanel editPanel = new JPanel();
    // Labels voor de configuration op de designtab
    static JLabel jlWb1 = new JLabel();
    static JLabel jlWb2 = new JLabel();
    static JLabel jlWb3 = new JLabel();
    static JLabel jlDb1 = new JLabel();
    static JLabel jlDb2 = new JLabel();
    static JLabel jlDb3 = new JLabel();

    public Screen() {

        // titel van de window
        setTitle("Facility Monitoring Application");
        // grootte van de window
        setSize(700, 600);
        // gebruiker kan groote van window niet aanpassen
        setResizable(false);
        // bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // componenten initialiseren

        monitorPanel.setLayout(null);
        editPanel.setLayout(null);
        designPanel.setLayout(null);

        // hieronder kunnen onderdelen per panel met behulp van coordinaten wordn
        // geplaatste
        // Monitorpanel

        // editpanel
        dropdownedit = new JComboBox();
        dropdownedit.setBounds(525, 0, 150, 25);
        JLabel jlDesnameEdit = new JLabel("Design name:");
        jlDesnameEdit.setBounds(10, 20, 100, 25);
        // labels
        JLabel jlDbs1 = new JLabel("Database server 1");
        JLabel jlDbs2 = new JLabel("Database server 2");
        JLabel jlDbs3 = new JLabel("Database server 3");
        JLabel jlWs1 = new JLabel("Webserver 1");
        JLabel jlWs2 = new JLabel("Webserver 2");
        JLabel jlWs3 = new JLabel("Webserver 3");
        JLabel jlFw = new JLabel("Pfsense");
        JLabel jlAvailability = new JLabel("Availibility");
        // label bounds
        jlDbs1.setBounds(10, 60, 120, 25);
        jlDbs2.setBounds(10, 90, 120, 25);
        jlDbs3.setBounds(10, 120, 120, 25);
        jlWs1.setBounds(10, 150, 120, 25);
        jlWs2.setBounds(10, 180, 120, 25);
        jlWs3.setBounds(10, 210, 120, 25);
        jlFw.setBounds(10, 240, 120, 25);
        jlAvailability.setBounds(10, 330, 100, 25);
        // button bounds
        jbCalculate.setBounds(10, 280, 100, 25);
        jbOptimize.setBounds(140, 330, 100, 25);
        jbDelete.setBounds(70, 500, 100, 25);
        jbSave.setBounds(270, 500, 100, 25);
        jbSaveAs.setBounds(470, 500, 150, 25);
        // textfield bounds
        jtfDesnameEdit.setBounds(110, 20, 100, 25);
        jtfDb1.setBounds(140, 60, 25, 25);
        jtfDb2.setBounds(140, 90, 25, 25);
        jtfDb3.setBounds(140, 120, 25, 25);
        jtfWs1.setBounds(140, 150, 25, 25);
        jtfWs2.setBounds(140, 180, 25, 25);
        jtfWs3.setBounds(140, 210, 25, 25);
        jtfCalculateAnswer.setBounds(120, 280, 300, 25);
        jtfavailability.setBounds(80, 330, 40, 25);
        jtfOptimizeAnswer.setBounds(10, 370, 300, 25);
        // actionlisteneners
        jbOptimize.addActionListener(this);
        jbCalculate.addActionListener(this);
        jbDelete.addActionListener(this);
        // toevoegen aan panel
        jbSaveAs.addActionListener(this);
        // toevoegen aan panel
        editPanel.add(dropdownedit);
        editPanel.add(jlDesnameEdit);
        editPanel.add(jtfDesnameEdit);
        editPanel.add(jlWs1);
        editPanel.add(jlWs2);
        editPanel.add(jlWs3);
        editPanel.add(jlDbs1);
        editPanel.add(jlDbs2);
        editPanel.add(jlDbs3);
        editPanel.add(jlFw);
        editPanel.add(jlAvailability);
        editPanel.add(jbCalculate);
        editPanel.add(jbOptimize);
        editPanel.add(jbDelete);
        editPanel.add(jbSave);
        editPanel.add(jbSaveAs);
        editPanel.add(jtfWs1);
        editPanel.add(jtfWs2);
        editPanel.add(jtfWs3);
        editPanel.add(jtfDb1);
        editPanel.add(jtfDb2);
        editPanel.add(jtfDb3);
        editPanel.add(jtfCalculateAnswer);
        editPanel.add(jtfavailability);
        editPanel.add(jtfOptimizeAnswer);

        // designpanel
        // dropdown
        dropdowndesign = new JComboBox();
        dropdowndesign.setBounds(525, 0, 150, 25);
        dropdowndesign.addActionListener(this);
        // graphics
        DisplayGraphics graphicsPanel = new DisplayGraphics();
        graphicsPanel.setBounds(10, 250, 660, 270);
        // variabele JLabels
        jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        // statische JLabels
        JLabel jlConfiguration = new JLabel("Configuration:");
        JLabel jlFirewall = new JLabel("PFsense");
        jlDesignName.setBounds(10, 20, 250, 25);
        jlConfiguration.setBounds(10, 50, 100, 25);

        // for loop waarin door de lijst met opgeslagen servers wordt gegaan om deze
        // onder elkaar te krijgen.

        designPanel.add(graphicsPanel);
        designPanel.add(jlDesignName);
        designPanel.add(jlConfiguration);
        designPanel.add(dropdowndesign);

        // voegt de panes toe aan tabbedpane met een name
        tabbedPane.addTab("Monitor", monitorPanel);
        tabbedPane.addTab("Edit", editPanel);
        tabbedPane.addTab("Design", designPanel);
        add(tabbedPane);
        readDesignsList(this);
        showConfig();
        // zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dropdowndesign) {
            try {
                if (dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    tabbedPane.setSelectedComponent(editPanel);
                }
                if (!dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
                    showConfig();

                }
            } catch (NullPointerException ex) {
                // TODO
            }

        }
        if (e.getSource() == jbOptimize) {
            int[] arrayServers = new int[6];
            if (isNumeric(jtfDb1.getText()) && Integer.parseInt(jtfDb1.getText()) >= 0) {
                arrayServers[0] = Integer.parseInt(jtfDb1.getText());
            }
            if (isNumeric(jtfDb2.getText()) && Integer.parseInt(jtfDb2.getText()) >= 0) {
                arrayServers[1] = Integer.parseInt(jtfDb2.getText());
            }
            if (isNumeric(jtfDb3.getText()) && Integer.parseInt(jtfDb3.getText()) >= 0) {
                arrayServers[2] = Integer.parseInt(jtfDb3.getText());
            }
            if (isNumeric(jtfWs1.getText()) && Integer.parseInt(jtfWs1.getText()) >= 0) {
                arrayServers[3] = Integer.parseInt(jtfWs1.getText());
            }
            if (isNumeric(jtfWs2.getText()) && Integer.parseInt(jtfWs2.getText()) >= 0) {
                arrayServers[4] = Integer.parseInt(jtfWs2.getText());
            }
            if (isNumeric(jtfWs3.getText()) && Integer.parseInt(jtfWs3.getText()) >= 0) {
                arrayServers[5] = Integer.parseInt(jtfWs3.getText());
            }
            try {
                double availabilityDouble = Double.parseDouble(jtfavailability.getText());
                Backtracking backtracking = new Backtracking();
                backtracking.optimisation(arrayServers, availabilityDouble);

            } catch (Exception ex2) {
                jtfOptimizeAnswer.setText("unknown value");
            }
        }
        if (e.getSource() == jbCalculate) {
            try {
                jtfCalculateAnswer
                        .setText(prijsbeschikbaarheidberekenen(jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3));
            } catch (Exception ex) {
                jtfCalculateAnswer.setText("Choose at least 1 webserver and 1 databaseserver");
            }
        }
        if (e.getSource() == jbSaveAs) {
            ArrayList<Server> servers = Server.getServerList();
            // haal ingevulde naam op voor design
            String name = jtfDesnameEdit.getText();
            // haal alle aantalen op van ingevulde waardes
            String db1 = jtfDb1.getText();
            String db2 = jtfDb2.getText();
            String db3 = jtfDb3.getText();
            String wb1 = jtfWs1.getText();
            String wb2 = jtfWs2.getText();
            String wb3 = jtfWs3.getText();
            // doe deze waardes in een array en daarna is een arraylist
            ArrayList<Integer> serverAmount = new ArrayList<>();
            String[] listString = new String[] { db1, db2, db3, wb1, wb2, wb3 };
            // Checken of er niks is ingevuld, als dit is verander dan het naar 0
            for (String string : listString) {
                if (string.equals("")) {
                    string = "0";
                }
                serverAmount.add(Integer.parseInt(string));

            }
            // roep de write functie aan
            WriteJson.saveDesign(servers, name, serverAmount);
            readDesignsList(this);
        }
        if (e.getSource() == jbDelete) {
            File temp = new File("src/savedDesigns/" + dropdownedit.getSelectedItem() + ".json");
            if (temp.delete()) {
                System.out.println(dropdownedit.getSelectedItem() + " deleted");
            }
            readDesignsList(this);
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String prijsbeschikbaarheidberekenen(JTextField Db1, JTextField Db2, JTextField Db3, JTextField Ws1,
            JTextField Ws2, JTextField Ws3) throws IndexOutOfBoundsException {
        ArrayList<Server> serverList = new ArrayList<>();
        if (isNumeric(Db1.getText()) && Integer.parseInt(Db1.getText()) >= 0) {
            int count = Integer.parseInt(Db1.getText());
            for (int i = 0; i < count; i++) {
                Server db1 = new Server(0, "database", "Database server 1", 0.90, 5100);
                serverList.add(db1);
            }
        }
        if (isNumeric(Db2.getText()) && Integer.parseInt(Db2.getText()) >= 0) {
            int count2 = Integer.parseInt(Db2.getText());
            for (int i = 0; i < count2; i++) {
                Server db2 = new Server(1, "database", "Database server 2", 0.95, 7700);
                serverList.add(db2);
            }
        }
        if (isNumeric(Db3.getText()) && Integer.parseInt(Db3.getText()) >= 0) {
            int count3 = Integer.parseInt(Db3.getText());
            for (int i = 0; i < count3; i++) {
                Server db3 = new Server(2, "database", "Database server 3", 0.98, 12200);
                serverList.add(db3);
            }
        }
        if (isNumeric(Ws1.getText()) && Integer.parseInt(Ws1.getText()) >= 0) {
            int count4 = Integer.parseInt(Ws1.getText());
            for (int i = 0; i < count4; i++) {
                Server ws1 = new Server(3, "webserver", "Webserver 1", 0.80, 2200);
                serverList.add(ws1);
            }
        }
        if (isNumeric(Ws2.getText()) && Integer.parseInt(Ws2.getText()) >= 0) {
            int count5 = Integer.parseInt(Ws2.getText());
            for (int i = 0; i < count5; i++) {
                Server ws2 = new Server(4, "webserver", "Webserver 2", 0.90, 3200);
                serverList.add(ws2);
            }
        }
        if (isNumeric(Ws3.getText()) && Integer.parseInt(Ws3.getText()) >= 0) {
            int count6 = Integer.parseInt(Ws3.getText());
            for (int i = 0; i < count6; i++) {
                Server ws3 = new Server(5, "webserver", "Webserver 3", 0.95, 5100);
                serverList.add(ws3);
            }
        }
        if (!serverList.isEmpty()) {
            double a = Calculatepriceavailability.calculateavailability(serverList);
            double b = Calculatepriceavailability.calculateTotalPrice(serverList);
            a = a * 100;
            a = round(a, 2);
            return "Availibility: " + a + "%, Price: €" + b;
        }
        return "Serverlist is empty";
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void showConfig() {

        try {
            JLabel[] labels = new JLabel[] { jlDb1, jlDb2, jlDb3, jlWb1, jlWb2, jlWb3 };
            int[] serverAmount = ReadJson.readDesign((String) dropdowndesign.getSelectedItem());
            String[] serverAmount2 = ReadJson.readDesignNames((String) dropdowndesign.getSelectedItem());
            int counter = 0;
            int y = 30;
            for (int i : serverAmount) {
                if (i != 0) {
                    String temp = String.valueOf(i);
                    JLabel jlTemp = labels[counter];
                    jlTemp.setText(serverAmount2[counter] + ":     " + temp);
                    jlTemp.setBounds(10, 50 + y, 200, 25);
                    designPanel.add(jlTemp);
                    y += 30;

                } else {
                    JLabel jlTemp = labels[counter];
                    jlTemp.setText("");
                }

                counter++;

            }
        } catch (NullPointerException e) {
            System.out.println("No designs found");
        }

    }

    private static void readDesignsList(Screen screen) {
        File[] files = new File("src/savedDesigns").listFiles();
        dropdownedit.removeAllItems();
        dropdowndesign.removeAllItems();
        for (File file : files) {
            String name = file.getName();
            dropdownitemsedit.add(name.replace(".json", ""));
            dropdownedit.addItem(name.replace(".json", ""));
            dropdowndesign.addItem(name.replace(".json", ""));
            System.out.println(name);
        }
        dropdowndesign.addItem("Add new Design");

    }

}
