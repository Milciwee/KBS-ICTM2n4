package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

// Dialoog voor de monitoringtab om een nieuwe server toe te voegen voor monitoring.
public class MonitoringDialog extends JDialog implements ActionListener {
    JLabel jlName = new JLabel("Name");
    JLabel jlIP = new JLabel("Ip Address");
    JLabel jlHostname = new JLabel("Hostname");
    JLabel jlPassword = new JLabel("Password");
    private static JTextField jtfName = new JTextField();
    private static JTextField jtfIP = new JTextField();
    private static JTextField jtfHostname = new JTextField();
    private static JPasswordField jpfPassword = new JPasswordField();
    private static Serverconnection[] serverConnections = new Serverconnection[Screen.maxServersMonitoring];

    public static String getServerName() {
        return jtfName.getText();
    }

    public static String getServerIP() {
        return jtfIP.getText();
    }

    public static String getServerHostname() {
        return jtfHostname.getText();
    }

    public static String getServerPassword() {
        return String.valueOf(jpfPassword.getPassword());
    }

    public static Serverconnection[] getServerConnections() {
        return serverConnections;
    }

    public static void setJtfName(String jtfName) {
        MonitoringDialog.jtfName.setText(jtfName);
    }

    public static void setJtfIP(String jtfIP) {
        MonitoringDialog.jtfIP.setText(jtfIP);
    }

    public static void setJtfHostname(String jtfHostname) {
        MonitoringDialog.jtfHostname.setText(jtfHostname);
    }

    public static void setJpfPassword(String jpfPassword) {
        MonitoringDialog.jpfPassword.setText(jpfPassword);
    }

    static int serverCount = 0;
    JButton jbCancel = new JButton("Cancel");
    JButton jbSubmit = new JButton("Submit");

    public MonitoringDialog(JFrame frame) {
        super(frame, true);
        setTitle("Add New Server");
        setSize(250, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // button bounds
        jbCancel.setBounds(25, 280, 80, 25);
        jbSubmit.setBounds(135, 280, 80, 25);
        // label bounds
        jlName.setBounds(25, 5, 100, 30);
        jlIP.setBounds(25, 70, 100, 30);
        jlHostname.setBounds(25, 135, 100, 30);
        jlPassword.setBounds(25, 200, 100, 30);
        // textfield bounds
        jtfName.setBounds(25, 35, 191, 25);
        jtfIP.setBounds(25, 100, 191, 25);
        jtfHostname.setBounds(25, 165, 191, 25);
        jpfPassword.setBounds(25, 230, 191, 25);

        // toevoegen aan panel
        add(mainPanel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbSubmit);
        mainPanel.add(jlName);
        mainPanel.add(jtfName);
        mainPanel.add(jlHostname);
        mainPanel.add(jtfHostname);
        mainPanel.add(jlPassword);
        mainPanel.add(jpfPassword);
        mainPanel.add(jlIP);
        mainPanel.add(jtfIP);

        // ActionListener
        jbCancel.addActionListener(this);
        jbSubmit.addActionListener(this);

        setVisible(true);
    }

    // Method voor het toevoegen van een server aan de monitoring tab
    public void addServer() {
        // Alle fileNamess met JPanel componenten ophalen uit Screen class
        JPanel jpServer = Screen.jpServers[serverCount];
        JLabel jlServernaam = Screen.jlServernamen[serverCount];
        JPanel jpStatuspanel = Screen.jpStatuspanel[serverCount];
        JLabel jlStatus = Screen.jlStatussen[serverCount];
        JTextArea jtaInfo = Screen.jtaInfos[serverCount];
        JButton jbKruis = Screen.jbKruisen[serverCount];

        jpServer.setVisible(true);
        jpServer.setName(jtfName.getText());
        jlServernaam.setText(jtfName.getText() + "  -  " + jtfIP.getText());

        // een verbinding maken met de server doormiddel van SSH
        // Deze verbinding word opgeslagen in een fileNames, zodat deze later weer gebruikt kan worden
        // en het verkomen van steeds weer nieuwe hoeven connections maken
        Serverconnection serverConnectionTemp = new Serverconnection();
        serverConnections[serverCount] = serverConnectionTemp;
        Serverconnection serverConnection = serverConnections[serverCount];
        if (serverConnection.makeConnectionWithServer(getServerIP(), getServerHostname(), getServerPassword())) {
            jlStatus.setText("Online");
            jpStatuspanel.setBackground(Color.green);
            jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                    + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                    + serverConnection.serverDiskSpaceAvailable() + "\n");
        } else {
            jlStatus.setText("Offline");
            jpStatuspanel.setBackground(Color.red);
            jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                    + "Available disk space:\n" + "- unavailable\n");

        }
        serverCount++;
        // De JBkruis component krijgt bij het aanmaken een Name mee, deze bevat een uniek ID (0-3 in onze POC)
        // Dit ID kan gebruikt worden om elke server te kunnen identificeren
        int i = Integer.parseInt(jbKruis.getName());
        // De server word opgeslagen in een JSON file, de name van de file gebruikt het ID van JBKruis
        WriteJson.saveServer(getServerName(), getServerIP(), getServerHostname(), getServerPassword(), i);
        jtfName.setText("");
        jtfIP.setText("");
        jtfHostname.setText("");
        jpfPassword.setText("");
    }

    // Method voor het ophalen van opgeslagen servers
    public static void addServerFromJson() {
        // Alle files worden opgehaald en de name ervan word gepakt
        File[] files = new File("src/savedServers").listFiles();
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            fileNames[i] = (name.replace(".json", ""));
        }
        serverCount = files.length;
        for (int i = 0; i < fileNames.length; i++) {
            // Hetzelfde als de addServer() method alleen wordt hier de data vanuit de JSON file gehaald.
            String name = ReadJson.readServer(fileNames[i], "name");
            String ip = ReadJson.readServer(fileNames[i], "ip");
            String hostname = ReadJson.readServer(fileNames[i], "hostname");
            String password = ReadJson.readServer(fileNames[i], "password");
            JPanel jpServer = Screen.jpServers[i];
            JLabel jlServernaam = Screen.jlServernamen[i];
            JPanel jpStatuspanel = Screen.jpStatuspanel[i];
            JLabel jlStatus = Screen.jlStatussen[i];
            JTextArea jtaInfo = Screen.jtaInfos[i];
            jpServer.setVisible(true);
            jpServer.setName(name);
            jlServernaam.setText(name + "  -  " + ip);
            Serverconnection serverConnectionTemp = new Serverconnection();
            serverConnections[i] = serverConnectionTemp;
            Serverconnection serverConnection = serverConnections[i];
            serverConnection.makeConnectionWithServer(ip, hostname, password);
            try {
                //als de applicatie een verbinding tot stand kan brengen, dan vraagt hij eenmalig gegevens op
                if (serverConnection.serverConnected(i)) {
                    jlStatus.setText("Online");
                    jpStatuspanel.setBackground(Color.green);
                    jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                        + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                        + serverConnection.serverDiskSpaceAvailable() + "\n");
                } else {
                    //als de verbinding mislukt, dan zal de applicatie laten zien dat de server offline is en dat de data "unavailable" is.
                    jlStatus.setText("Offline");
                    jpStatuspanel.setBackground(Color.red);
                    jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                        + "Available disk space:\n" + "- unavailable\n");
                }
            }catch (Exception exServer){
                //als er iets misgaat bij het lezen van bestanden, dan gaat het progamma ervan uit dat het json bestand corrupt is, en verwijdert dit. daana sluit de applicatie af
                String fileName = fileNames[i].substring(1);
                JOptionPane.showMessageDialog(null, "Server configuration file \"" + fileName + "\" is corrupt.\n This file will now be deleted, please restart the application");
                String name2 = fileNames[i];
                File[] files2 = new File("src/savedServers").listFiles();
                for (File file : files2) {
                    String nameFile = file.getName();
                    nameFile = nameFile.replace(".json", "");
                    if(nameFile.indexOf(name2) != -1){
                        if(file.delete()){
                            System.out.println("Server " + nameFile + " deleted");
                        } else {
                            System.out.println("Something went wrong");
                        }
                    }
                }
            System.exit(0);
            }
        }
    }

    // Method voor het refreshen van de servers
    public static void refreshServers() {
        try {
            // Deze werkt hetzelfde als
            File[] files = new File("src/savedServers").listFiles();
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();
                fileNames[i] = (name.replace(".json", ""));
            }
            for (int i = 0; i < fileNames.length; i++) {

                String ip = ReadJson.readServer(fileNames[i], "ip");
                String hostname = ReadJson.readServer(fileNames[i], "hostname");
                String password = ReadJson.readServer(fileNames[i], "password");
                JPanel jpStatuspanel = Screen.jpStatuspanel[i];
                JLabel jlStatus = Screen.jlStatussen[i];
                JTextArea jtaInfo = Screen.jtaInfos[i];
                Serverconnection serverConnection = serverConnections[i];
                // Checked of er al een bestaande connection aanwezig is met de server
                // is dit zo dan word het geupdate, is er geen bestaande dan word er geprobeerd
                // om een verbinding te maken, lukt dit dan word het geupdate.
                // Lukt ook dit niet dan wor de server op Offline gezit in het monitoring tab
                if (serverConnection.serverConnected(i)) {
                    jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                            + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                            + serverConnection.serverDiskSpaceAvailable() + "\n");
                } else if (serverConnection.makeConnectionWithServer(ip, hostname, password)) {
                    jlStatus.setText("Online");
                    jpStatuspanel.setBackground(Color.green);
                    jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                            + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                            + serverConnection.serverDiskSpaceAvailable() + "\n");

                } else {
                    jlStatus.setText("Offline");
                    jpStatuspanel.setBackground(Color.red);
                    jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                            + "Available disk space:\n" + "- unavailable\n");
                }
            }
        } catch (Exception e) {
            //zorgt ervoor dat de applicatie door kan gaan als het refreshen mislukt
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
            jtfName.setText("");
            jtfIP.setText("");
            jtfHostname.setText("");
            jpfPassword.setText("");
        }
        // Checks of er een name, en geldig ip is ingevuld.
        // Is dit niet gedaan dan worden er messages getoond.
        // Ook word er gechecked of de name al bestaat
        if (e.getSource() == jbSubmit) {
            try {
                String userInputNameJson = getServerName();
                if (userInputNameJson.equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter a server name");
                    int throwsExeption = 0 / 0;
                }
                File[] files = new File("src/savedServers").listFiles();
                for (int i = 0; i < files.length; i++) {
                    String name = files[i].getName();
                    name = name.replace(".json", "");
                    String serverName = ReadJson.readServer(name, "name");
                    if (serverName.equals(userInputNameJson)) {
                        JOptionPane.showMessageDialog(this, "This server name already exists");
                        int throwsExeption = 0 / 0;
                    }
                }
                if (!validate(getServerIP())) {
                    JOptionPane.showMessageDialog(this, "Please use a valid ip address");
                    int throwsExeption = 0 / 0;
                }
                addServer();
                dispose();
            } catch (Exception ex4) {
                System.out.println("faulty user input, server was not added");
            }
        }
    }

    // Een method om te valideren dat er een geldig ip address is ingevuld door middel van regex
    public static boolean validate(final String ip) {
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(PATTERN);
    }
}
