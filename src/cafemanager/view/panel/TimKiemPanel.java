package cafemanager.view.panel;

import javax.swing.*;
import java.awt.*;

public class TimKiemPanel extends JPanel {
    public TimKiemPanel() {
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel label = new JLabel("Tìm Kiếm", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm Kiếm");

        searchPanel.add(txtSearch);
        searchPanel.add(Box.createHorizontalStrut(10)); // Khoảng cách
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.CENTER);
    }
}
