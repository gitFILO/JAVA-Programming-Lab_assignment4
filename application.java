import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class application extends JFrame {

    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JPanel panel;
    private JTextField nameTextField;
    private JButton submitButton;
    private JTextField birthDateTextField;
    private JTextField emailTextField;
    private JTextField degreeTextField;
    private JTextField attendedUniversityTextField;
    private JTextField gpaTextField;
    private JTextField homeAddressTextField;
    private JTextField phoneNumberTextField;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel consentNewLabel2;
    private JLabel consentNewLabel1;
    private JCheckBox consentCheckBox;
    private JTextArea personalStatementTextArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    application frame = new application();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // try,catch로 구현할 때
    // JOptionPane.showMessageDialog(null,"Error message","하고싶은말",);
    // 내가 원하지 않는 숫자의 범위 등 일떄(커스터마이징)
    // class ㅅ만들어서 extends exception 하고 -> throw new 만든 클래스 로 exception으로 던지기 + catch(내가만든클래스이름 + 객체이름)	 만들기

    /**
     * Create the frame.
     */
    public application() {
        setTitle("SKKU New Students Enrollment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 849, 618);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane);
        desktopPane.setLayout(null);

        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(12, 10, 268, 550);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\x1xgu\\Downloads\\images\\skku_wallpaper.png"));
        desktopPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(344, 10, 420, 67);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\x1xgu\\Downloads\\images\\title_label.png"));
        desktopPane.add(lblNewLabel_1);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(281, 74, 519, 485);
        desktopPane.add(panel);
        panel.setLayout(null);

        nameTextField = new JTextField();
        nameTextField.setBounds(216, 10, 291, 21);
        panel.add(nameTextField);
        nameTextField.setColumns(10);

        submitButton = new JButton("Submit Application");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int wrongFormatNum=0;
                boolean emptyName = false;
                boolean noNameOrSurname = false;
                boolean wrongBirthDate = false;
                boolean wrongEmail = false;
                boolean wrongPhoneNumber = false;
                boolean shortPersonalStatement = false;
                boolean emptyAttendedUniv = false;
                boolean emptyGPA = false;
                boolean wrongGPA = false;
                String errorMessage = "";
                String successMessage = "Successfully Submitted";
                boolean successFlag = true;
                boolean wrongHomeAddress = false;
                boolean notConsented = false;
                boolean emptyDegree = false;

                try{
                    // name check
                    String name = nameTextField.getText();
                    String checkName = name.replaceAll(" ", "");

                    if(name.isEmpty()) {
                        wrongFormatNum++;
                        emptyName = true;
                        successFlag = false;
                    }
                    else if(name.equals(checkName)) {
                        wrongFormatNum++;
                        noNameOrSurname = true;
                        successFlag = false;
                    }
                    // birth date check
                    String birthDate = birthDateTextField.getText();
                    int birthDateSlashFlag = 0;

                    for(int i=0;i<birthDate.length();i++) if(birthDate.charAt(i) =='/') birthDateSlashFlag++;
                    if(birthDateSlashFlag != 2) {
                        wrongFormatNum++;
                        wrongBirthDate = true;
                        successFlag = false;
                    }

                    // email check
                    String email = emailTextField.getText();
                    String checkEmail2 = email.replaceAll("[.]", "");
                    String checkEmail = email.replaceAll("[@]","");       // email = @  , ch1 = " ", ch2 = @
                    if(checkEmail.equals(email) || checkEmail2.equals(email) ) {
                        wrongFormatNum++;
                        wrongEmail = true;
                        successFlag = false;
                    }

                    // phone number check
                    String phoneNumber = phoneNumberTextField.getText();
                    String checkPhoneNumber = phoneNumber.replaceAll("[-]", "");
                    String checkPhoneNumber2 = phoneNumber.replaceAll(" ", "");
                    if(checkPhoneNumber.equals(phoneNumber) || checkPhoneNumber2.equals(phoneNumber)) {
                        wrongFormatNum++;
                        wrongPhoneNumber = true;
                        successFlag = false;
                    }

                    // degree check
                    String degree = degreeTextField.getText();
                    if(degree.isEmpty()) {
                        wrongFormatNum++;
                        emptyDegree = true;
                        successFlag = false;
                    }
                    else if(!("undergraduate".equals(degree)) && !("Undergraduate".equals(degree))) { // apply undergraduate -> no need to GPA or attended university
                        String attendedUniv = attendedUniversityTextField.getText();
                        String gpa = gpaTextField.getText();

                        if(attendedUniv.isEmpty() || gpa.isEmpty()) {
                            wrongFormatNum++;
                            emptyAttendedUniv = true;
                            successFlag = false;

                        }
                        if(!gpa.isEmpty()) { // gpa check : 0 <= gpa <=4.5
                            double gpaNum = Double.parseDouble(gpa);
                            if(gpaNum > 4.5 || gpaNum < 0) {
                                wrongFormatNum++;
                                wrongGPA = true;
                                successFlag = false;
                            }
                        }

                    }
                    // check personalStatement
                    String personalStatement = personalStatementTextArea.getText();
                    if(personalStatement.length() <100) {
                        wrongFormatNum++;
                        shortPersonalStatement = true;
                        successFlag = false;
                    }
                    // check homeaddress
                    String homeAddress = homeAddressTextField.getText();
                    int commmaFlag =0;
                    for(int i=0;i<homeAddress.length();i++) if(homeAddress.charAt(i) ==',') commmaFlag++;
                    if(commmaFlag < 3) {
                        wrongFormatNum++;
                        wrongHomeAddress = true;
                        successFlag = false;
                    }
                    // check consent
                    if(consentCheckBox.isSelected() == false) {
                        wrongFormatNum++;
                        notConsented = true;
                        successFlag = false;
                    }

                    //make errorMessage by booleans and wrongFormatNum..wrongFormatNum is the number of error
                    for(int i=1;i<=wrongFormatNum;i++) {
                        String errorContents = i+". You forget to write name or surname\n";
                        if(emptyName) {
                            errorMessage = errorMessage + errorContents;
                            emptyName = false;
                            continue;
                        }
                        if(noNameOrSurname) {
                            errorMessage = errorMessage + (i+". You forget to write name or surname\n");
                            noNameOrSurname = false;
                            continue;
                        }
                        if(wrongBirthDate) {
                            errorMessage = errorMessage + (i+". Birth date must be '06/06/1995' format\n");
                            wrongBirthDate = false;
                            continue;
                        }
                        if(wrongEmail) {
                            errorMessage = errorMessage + (i+". Email must be in example@some.some\n");
                            wrongEmail = false;
                            continue;
                        }
                        if(wrongPhoneNumber) {
                            errorMessage = errorMessage + (i+". Proper format for a phone number is '10 2158-0222'\n");
                            wrongPhoneNumber = false;
                            continue;
                        }
                        if(emptyDegree) {
                            errorMessage = errorMessage + (i+". You forget to write degree\n");
                            emptyDegree = false;
                            continue;
                        }
                        if(emptyAttendedUniv || emptyGPA) {
                            errorMessage = errorMessage + (i+". For graduate, you have to enter previous university and GPA\n");
                            emptyAttendedUniv = false;
                            continue;
                        }

                        if(wrongGPA) {
                            errorMessage = errorMessage + (i+". GPA must be between 0 and 4.5\n");
                            wrongGPA = false;
                            continue;
                        }
                        if(shortPersonalStatement) {
                            errorMessage = errorMessage + (i+". Your personal Statement must be at least 100 words\n");
                            shortPersonalStatement = false;
                            continue;
                        }
                        if(wrongHomeAddress) {
                            errorMessage = errorMessage + (i+". Your address must be in 'number, street, district, city' format\n");
                            wrongHomeAddress = false;
                            continue;
                        }
                        if(notConsented) {
                            errorMessage = errorMessage + (i+". You have to consent to collection and use of personal information \n");
                            notConsented = false;
                            continue;
                        }

                    }

                    if(successFlag == false) throw new errorMessage();
                    if(successFlag == true) throw new successMessage();

                } catch(errorMessage ex) {
                    JOptionPane.showMessageDialog(null,errorMessage,"You have following problems",
                            JOptionPane.ERROR_MESSAGE);

                } catch(successMessage ex) { // if you successfully apply, clear textfield
                    nameTextField.setText("");
                    birthDateTextField.setText("");
                    emailTextField.setText("");
                    phoneNumberTextField.setText("");
                    degreeTextField.setText("");
                    attendedUniversityTextField.setText("");
                    gpaTextField.setText("");
                    personalStatementTextArea.setText("");
                    homeAddressTextField.setText("");
                    consentCheckBox.setSelected(false);

                    JOptionPane.showMessageDialog(null,successMessage,"Success Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        submitButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
        submitButton.setForeground(new Color(255, 255, 255));
        submitButton.setBackground(new Color(0, 128, 64));

        submitButton.setBounds(154, 444, 207, 31);
        panel.add(submitButton);

        birthDateTextField = new JTextField();
        birthDateTextField.setColumns(10);
        birthDateTextField.setBounds(216, 41, 291, 21);
        panel.add(birthDateTextField);

        emailTextField = new JTextField();
        emailTextField.setColumns(10);
        emailTextField.setBounds(216, 72, 291, 21);
        panel.add(emailTextField);

        degreeTextField = new JTextField();
        degreeTextField.setColumns(10);
        degreeTextField.setBounds(216, 134, 291, 21);
        panel.add(degreeTextField);

        attendedUniversityTextField = new JTextField();
        attendedUniversityTextField.setColumns(10);
        attendedUniversityTextField.setBounds(216, 165, 291, 21);
        panel.add(attendedUniversityTextField);

        gpaTextField = new JTextField();
        gpaTextField.setColumns(10);
        gpaTextField.setBounds(216, 196, 291, 21);
        panel.add(gpaTextField);

        homeAddressTextField = new JTextField();
        homeAddressTextField.setColumns(10);
        homeAddressTextField.setBounds(216, 373, 291, 21);
        panel.add(homeAddressTextField);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setColumns(10);
        phoneNumberTextField.setBounds(216, 103, 291, 21);
        panel.add(phoneNumberTextField);

        lblNewLabel_2 = new JLabel("Applicant Name");
        lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_2.setBounds(120, 15, 91, 15);
        panel.add(lblNewLabel_2);

        lblNewLabel_9 = new JLabel("Personal Statement");
        lblNewLabel_9.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_9.setBounds(154, 233, 194, 21);
        panel.add(lblNewLabel_9);

        lblNewLabel_10 = new JLabel("Birth Date");
        lblNewLabel_10.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_10.setBounds(154, 43, 57, 15);
        panel.add(lblNewLabel_10);

        lblNewLabel_3 = new JLabel("Email");
        lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_3.setBounds(177, 74, 34, 15);
        panel.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("Phone Number");
        lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_4.setBounds(126, 106, 87, 15);
        panel.add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("Degree");
        lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_5.setBounds(169, 137, 42, 15);
        panel.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("Attended University(for Graduates)");
        lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 11));
        lblNewLabel_6.setBounds(22, 167, 191, 15);
        panel.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("GPA(for Graduates)");
        lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_7.setBounds(100, 198, 113, 15);
        panel.add(lblNewLabel_7);

        lblNewLabel_8 = new JLabel("Home Adress");
        lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_8.setBounds(134, 375, 79, 15);
        panel.add(lblNewLabel_8);

        consentNewLabel2 = new JLabel("INFORMATION FOR THE WORK RELATE TO APPLICATION");
        consentNewLabel2.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 14));
        consentNewLabel2.setBounds(42, 423, 448, 21);
        panel.add(consentNewLabel2);

        consentNewLabel1 = new JLabel("I give my consent to collect and use my personal");
        consentNewLabel1.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 14));
        consentNewLabel1.setBounds(42, 408, 462, 19);
        panel.add(consentNewLabel1);

        consentCheckBox = new JCheckBox("");
        consentCheckBox.setBounds(13, 410, 21, 17);
        panel.add(consentCheckBox);

        personalStatementTextArea = new JTextArea();
        personalStatementTextArea.setBackground(new Color(240, 240, 240));
        personalStatementTextArea.setLineWrap(true);
        personalStatementTextArea.setBounds(12, 252, 495, 107);
        panel.add(personalStatementTextArea);
    }
}

class errorMessage extends Exception {
    public errorMessage(){

    }
}

class successMessage extends Exception{
    public successMessage(){

    }
}