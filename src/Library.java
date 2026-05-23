import javax.swing.*;
import java.awt.*;

public class Library extends JPanel {
    public Library(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(27, 94, 239));

        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        add(back);
    }
}
