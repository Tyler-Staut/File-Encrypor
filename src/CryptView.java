import java.awt.Component;
import java.awt.GridLayout;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/28/17
 * @Model_Used: Model-View-Controller
 *
 * The view manages the display of information.
 */
public class CryptView
{
    //----------Initial Variables----------//
    private CryptModel model; //Javax Api variables
    //Buttons for the gui (Symmetric and Asymmetric)
    JRadioButton symmetric;
    JRadioButton asymmetric;
    //Button group
    ButtonGroup encryptionType;
    //Panel sector for encryption
    JPanel encryptionSelection;

    //Input file lable
    JLabel inputFileLabel;
    JLabel inputFileLabel2;
    //Input File chooser for the gui
    JFileChooser inputFileChooser;
    //File chooser buttion
    JButton inputChooserButton;
    //Input File name
    JLabel inputFileName;
    JPanel fileIO;
    //Gui top of the panel
    JPanel top;

    //Encrypt Button
    JButton encrypt;
    //Decrypt Button
    JButton decrypt;
    //Gui botton of the panel
    JPanel bottom;

    JTextArea messages;

    //Gui Lable symmetric
    JLabel symmetricLabel;
    //Type field for Symmetric key
    JPasswordField symmetricKey;
    //SymmetricPannel
    JPanel symmetricPanel;
    //Symetric Button
    JButton symmetricButton;

    //Generate key button
    JButton generateKeys;

    //Public key label and private key lable
    JLabel publicKeyLabel;
    JLabel privateKeyLabel;
    //File chooser for public key and private key
    JFileChooser publicKeyChooser;
    JButton publicKeyButton;
    JFileChooser privateKeyChooser;
    JButton privateKeyButton;
    //keys pannel
    JPanel keys;

    //Asymetric Panel
    JPanel asymmetricPanel;

    //Asymetric container/frame/amd frame chooser
    JPanel container;
    JFrame frame;
    JFrame chooserFrame;
    //----------Initial Variables----------//

    /**
     * This method initializes the view
     */
    public void init() //creates buttons and windows
    {
        //This should make things look good for now on all Operating Systems
        //Later we will update the look and feel
        //Warning: If GodMode is enabled on your Windows 10 device this will cause problems.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (UnsupportedLookAndFeelException e2) {
            e2.printStackTrace();
        }

        //Symmetric and Asymetric J Radio Button
        symmetric = new JRadioButton("Symmetric Encryption");
        asymmetric = new JRadioButton("Asymmetric Encryption");
        //Encryption type Button section
        encryptionType = new ButtonGroup();
        //Encryption symmetric and asymmetric types
        encryptionType.add(symmetric);
        encryptionType.add(asymmetric);
        //Encryption type button slector
        encryptionSelection = new JPanel();
        encryptionSelection.add(symmetric);
        encryptionSelection.add(asymmetric);

        //File in and out Label
        inputFileLabel = new JLabel("In File:", SwingConstants.CENTER);
        inputFileLabel2 = new JLabel("Selected File:", SwingConstants.CENTER);
        //input file chooser
        inputFileChooser = new JFileChooser();
        inputChooserButton = new JButton("Find");
        inputFileName = new JLabel("");

        //Jpannel for file input and file chooser
        fileIO = new JPanel();
        fileIO.setLayout(new GridLayout(2, 2));
        fileIO.add(inputFileLabel);
        fileIO.add(inputChooserButton);
        fileIO.add(inputFileLabel2);
        fileIO.add(inputFileName);

        //Pannel top and layout
        top = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        top.add(encryptionSelection);
        top.add(fileIO);

        //enctypt and dectypt button
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        bottom.add(encrypt);
        bottom.add(decrypt);

        //Start up message
        messages = new JTextArea("Go to our GitHub project page to learn more on how to use this program.");
        messages.setEditable(false);

        //Symmetric label and symetric key
        symmetricLabel = new JLabel("Symmetric Key:");
        symmetricKey = new JPasswordField("", 30);
        symmetricPanel = new JPanel();
        symmetricButton = new JButton("Set Symmetric Key");
        symmetricPanel.add(symmetricLabel);
        symmetricPanel.add(symmetricKey);
        symmetricPanel.add(symmetricButton);

        //generate keys lable
        generateKeys = new JButton("Generate Keys:");

        //public keylabel
        publicKeyLabel = new JLabel("Public Key:", SwingConstants.CENTER);
        //private keylabel
        privateKeyLabel = new JLabel("Private Key:", SwingConstants.CENTER);
        //private key chooser chooser button
        publicKeyChooser = new JFileChooser();
        //public key chooser
        privateKeyChooser = new JFileChooser();
        //find button for public key
        publicKeyButton = new JButton("Find");
        //find button for private key
        privateKeyButton = new JButton("Find");
        //Keys button for private key and public key
        keys = new JPanel();
        keys.setLayout(new GridLayout(2, 2));
        keys.add(publicKeyLabel);
        keys.add(publicKeyButton);
        keys.add(privateKeyLabel);
        keys.add(privateKeyButton);

        //Asymmetric Pannel and its button
        asymmetricPanel = new JPanel();
        asymmetricPanel.setLayout(new GridLayout(1, 2));
        asymmetricPanel.add(generateKeys);
        asymmetricPanel.add(keys);

        //Containers design and layout
        container = new JPanel();
        container.setLayout(new GridLayout(4, 1));
        container.add(top);
        container.add(bottom);
        container.add(messages);

        //File Encrytion and Decryption frame and container
        frame = new JFrame("File Encryption/Decryption");
        frame.add(container);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //chooser frame
        chooserFrame = new JFrame();
    }

    /**
     * Constructor that gets the model from the Controller
     * @param model
     */
    public CryptView(CryptModel model)
    {
        this.model = model;
        init();
    }

    /**
     * Method used to update the view
     */
    public void update() //updated view for the model
    {
        if (model.getWindowToUse().equals("Symmetric")) //For symmetric
        {
            Component[] components = container.getComponents();
            for (int i = 0; i < components.length; i++)
            {
                container.remove(components[i]);
            }
            container.add(top);
            container.add(symmetricPanel);
            container.add(bottom);
            container.add(messages);
            container.revalidate();
            container.repaint();
            model.setSymmetric();
        }
        else if (model.getWindowToUse().equals("Asymmetric")) //For asymmetric
        {
            Component[] components = container.getComponents();
            for (int i = 0; i < components.length; i++)
            {
                container.remove(components[i]);
            }
            container.add(top);
            container.add(asymmetricPanel);
            container.add(bottom);
            container.add(messages);
            container.revalidate();
            container.repaint();
            try
            {
                model.setAsymmetric();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchPaddingException e)
            {
                e.printStackTrace();
            }
        }
        else if (model.getWindowToUse().equals("In File")) //For infile
        {
            inputFileChooser.showOpenDialog(chooserFrame);
        }
        else if (model.getWindowToUse().equals("In File Chooser")) //For infile chooser
        {
            model.setInFile(inputFileChooser.getSelectedFile());

            //Gets the name of the input file so the user knows what file they are using.
            inputFileName.setText(model.getInFile().getName().toString());
        }
        else if (model.getWindowToUse().equals("Generate Keys:")) //For generate keys
        {
            try
            {
                model.sk.generatePublicPrivateKeys();
            } catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }
        else if (model.getWindowToUse().equals("Symmetric Button")) //Symmetric button
        {
            model.setSymmetricKey(symmetricKey.getText());
        }
        else if (model.getWindowToUse().equals("Public Key:")) //Public key button
        {
            publicKeyChooser.showOpenDialog(chooserFrame);
        }
        else if (model.getWindowToUse().equals("Private Key:")) //For private key
        {
            privateKeyChooser.showOpenDialog(chooserFrame);
        }
        else if (model.getWindowToUse().equals("Public Key Chooser")) //For public key chooser
        {
            try
            {
                model.setPublicKey(publicKeyChooser.getSelectedFile());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (model.getWindowToUse().equals("Private Key Chooser")) //Private key chooser
        {
            try
            {
                System.out.println("Private Key Chooser");
                model.setPrivateKey(privateKeyChooser.getSelectedFile());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (model.getWindowToUse().equals("Encrypt Button")) //Encrypt button
        {
            System.out.println("Encrypt Button");
            model.encryptFile("Not Currently Implemented");
            messages.setText("Encrypted '" + model.getInFile().getName().toString() + "' with " + model.encMethod + " algorithm.");
        }
        else if (model.getWindowToUse().equals("Decrypt Button")) //For decrypt button
        {
            model.decryptFile("Not Currently Implemented");
            messages.setText("Decrypted '" + model.getInFile().getName().toString() + "' with " + model.encMethod + " algorithm.");
        }
    }
}
