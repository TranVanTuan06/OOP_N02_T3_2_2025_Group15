package cafemanager.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private JTextField txtUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    public LoginUI() {
        setTitle("Đăng nhập - Coffee Shop Ba Anh Em");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        // Panel chính với ảnh nền
        BackgroundPanel mainPanel = new BackgroundPanel(new ImageIcon(getClass().getResource("/img/Imbue.png")).getImage());
        mainPanel.setLayout(null);

        // Vị trí căn giữa thủ công
        int formWidth = 300;
        int formX = (getWidth() - formWidth) / 2 - 20;

        // Nhãn và ô nhập tên đăng nhập
        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 16));
        lblUser.setForeground(Color.BLACK);
        lblUser.setBounds(formX, 130, formWidth, 25);
        mainPanel.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(formX, 160, formWidth, 35);
        mainPanel.add(txtUsername);

        // Nhãn và ô nhập mật khẩu
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 16));
        lblPass.setForeground(Color.BLACK);
        lblPass.setBounds(formX, 210, formWidth, 25);
        mainPanel.add(lblPass);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(formX, 240, formWidth, 35);
        mainPanel.add(pfPassword);

        // Nút đăng nhập
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(formX, 300, formWidth, 40);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(pfPassword.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginUI.this,
                            "Vui lòng nhập đầy đủ thông tin!",
                            "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (authenticate(username, password)) {
                    dispose();

//                    JOptionPane.showMessageDialog(LoginUI.this,
//                            "Đăng nhập thành công!",
//                            "Thông báo",
//                            JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        new MainUI().setVisible(true);
                    });

                } else {
                    JOptionPane.showMessageDialog(LoginUI.this,
                            "Sai tên đăng nhập hoặc mật khẩu!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    pfPassword.setText("");
                }
            }
        });

        mainPanel.add(btnLogin);
        pfPassword.addActionListener(e -> btnLogin.doClick());

        add(mainPanel);
    }

    private boolean authenticate(String username, String password) {
        return username.equals("1") && password.equals("1");
    }

    class BackgroundPanel extends JPanel {

        private Image background;

        public BackgroundPanel(Image background) {
            this.background = background;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginUI().setVisible(true);
        });
    }
}

