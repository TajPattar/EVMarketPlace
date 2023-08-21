package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


// The following code was taken / made with guidance from
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java

// JPanel that will be used in GUI
public class EVGui extends JPanel implements ListSelectionListener {
    private JList evList;
    private DefaultListModel listmodel;
    private JButton removeEVButton;
    private JTextField evmodel;
    private JLabel amountlabel;
    int count = 1;
    private JButton saveButton;
    private JButton loadButton;



    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})


    //EFFECTS: Constructs the JComponent that will be used in GUI
    // while also setting up the JButtons, JLists, JLbael and JScrollPlane
    // and adding them to JComponent
    public EVGui() {
        super(new BorderLayout());
        listmodel = new DefaultListModel();
        listmodel.addElement("Tesla Model Y (SAMPLE LISTING)");


        evList = new JList(listmodel); // Display object/select them
        evList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        evList.setSelectedIndex(0);
        evList.addListSelectionListener(this); // will tell when list change
        evList.setVisibleRowCount(1);
        evList.setBackground(Color.ORANGE);

        JScrollPane evListScrollPane = new JScrollPane(evList); //allows scroll

        JButton listEvButton = new JButton("List EV");
        ListEVListner listevlistner = new ListEVListner(listEvButton);
        listEvButton.setActionCommand("List EV");
        listEvButton.addActionListener(listevlistner);
        listEvButton.setEnabled(false);

        removeEVButton = new JButton("Purchase EV");
        removeEVButton.setActionCommand("Purchase EV");
        removeEVButton.addActionListener(new RemoveEVListner());

        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new SavingGui());

        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new LoadingGui());


        amountlabel = new JLabel("Number of listings: 1");


        evmodel = new JTextField(12);
        evmodel.addActionListener(listevlistner);
        evmodel.getDocument().addDocumentListener(listevlistner);

        // Make Panel
        JPanel jpanel = new JPanel();
        jpanel.setBackground(Color.CYAN);
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.LINE_AXIS));
        jpanel.add(removeEVButton);
        jpanel.add(Box.createHorizontalStrut(20));
        jpanel.add(new JSeparator(SwingConstants.VERTICAL)); // Dividng Line
        jpanel.add(Box.createHorizontalStrut(20));
        jpanel.add(evmodel);
        jpanel.add(listEvButton);
        jpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel jlabel = new JLabel();
        ImageIcon evimage = new ImageIcon("./data/EVIMAGE.png");
        evimage.setImage(evimage.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT));
        jlabel.setIcon(evimage);
        //jpanel.add(jlabel);
        jpanel.add(saveButton);
        jpanel.add(loadButton);
        jpanel.add(amountlabel);


        add(evListScrollPane, BorderLayout.CENTER);
        add(jpanel, BorderLayout.PAGE_START);
        add(jlabel, BorderLayout.EAST);



    }



    // The following code was taken / made with guidance from
// https://youtu.be/ScUJx4aWRi0

    // ActionListner for Save Button
    public class  SavingGui implements ActionListener {

        //EFFECTS: Sets up saving mechanism for GUI
        public void actionPerformed(ActionEvent actionevent) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("./data/MarketPlaceListing.txt"));
                for (Object o: listmodel.toArray()) {
                    writer.write("\n" + o);
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // The following code was taken / made with guidance from
// https://youtu.be/ScUJx4aWRi0

    // ActionListner for Load Button
    public class LoadingGui implements ActionListener {

        //EFFECTS: Sets up loading mechanism for GUI
        public void actionPerformed(ActionEvent actionevent) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("./data/MarketPlaceListing.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    listmodel.addElement(line);
                }


                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


   // ActionListner for remove button
    class RemoveEVListner implements ActionListener {

        // MODIFIES: this, count
        // EFFECTS: If there is a listing, removes it and updates
       // the listing count, otherwise keeps remove button as off
        public void actionPerformed(ActionEvent actionevent) {
            int index = evList.getSelectedIndex();
            listmodel.remove(index);

            int amountlisted = listmodel.size();

            if (amountlisted == 0) {
                removeEVButton.setEnabled(false);
            } else {
                if (index == listmodel.size()) { // Removes Item in last pos
                    index--;


                }
                count--;
                amountlabel.setText("Number of listings: " + count);

                evList.setSelectedIndex(index);
                evList.ensureIndexIsVisible(index);
            }
        }
    }


    // ActionListner for List button
    class ListEVListner implements ActionListener, DocumentListener {
        private Boolean alreadyon = false;
        private JButton jbutton;

        // EFFECTS: Initalizes button/ Assigns Jbutton
        public ListEVListner(JButton jbutton) {
            this.jbutton = jbutton;
        }

        // MODIFIES: this, count
        // EFFECTS: gets inputted EV model and adds it
        // while also increasing amount of EV in list counter
        public void actionPerformed(ActionEvent actionevent) {
            String evname = evmodel.getText();

            if (evname.equals("")) {   // If JTEXTField is empty
                evmodel.requestFocusInWindow();
                evmodel.selectAll();
                return;
            }

            int index = evList.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;

            }

            listmodel.addElement(evmodel.getText());

            evmodel.requestFocusInWindow();
            evmodel.setText("");

            evList.setSelectedIndex(index);
            evList.ensureIndexIsVisible(index);

            count++;
            amountlabel.setText("Number of listings: " + count);
        }

        // EFFECTS: Notifies there was an insert/ new EV listing into the MarketPlace
        public void insertUpdate(DocumentEvent documentevent) {
            enableButton();
        }

        // EFFECTS: Notifies when EV listing has been removed
        public void removeUpdate(DocumentEvent documentevent) {
            handleEmptyTextField(documentevent);
        }

        // EFFECTS: Notifies when attribute is changed
        public void changedUpdate(DocumentEvent documentevent) {
            if (!handleEmptyTextField(documentevent)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: Turns Jbutton on if it is not on
        private void enableButton() {
            if (!alreadyon) {
                jbutton.setEnabled(true);
            }
        }

        // EFFECTS: Returns true if textfield is empty and thus disables list button
        // returns false if textfield has something typed
        private boolean handleEmptyTextField(DocumentEvent documentevent) {
            if (documentevent.getDocument().getLength() <= 0) {
                jbutton.setEnabled(false);
                alreadyon = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: Keeps remove listing button on if scrolling through
    // listing that is in marketplace, otherwise turns remove listing button
    // off
    public void valueChanged(ListSelectionEvent lightselectionevent) {
        if (lightselectionevent.getValueIsAdjusting() == false) {
            if (evList.getSelectedIndex() == -1) { // no selection
                removeEVButton.setEnabled(false);
            } else {
                removeEVButton.setEnabled(true);
            }
        }
    }



    // EFFECTS: Constructs the JFrame and adds components
    private static void guiRunner() {
        JFrame jframe = new JFrame("EV MarketPlace");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JComponent jcomponent = new EVGui();
        jcomponent.setOpaque(true);
        jframe.setContentPane(jcomponent); // replace content panel of frame

        jframe.pack(); // establishes frame
        jframe.setVisible(true);




    }

    // EFFECTS: Runs GUI/ Displays the window
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                guiRunner();
            }
        });

    }







}










