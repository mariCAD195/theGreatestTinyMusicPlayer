import javax.swing.*;
import java.awt.*;

public class Customization extends JPanel {
    public Customization(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(154, 239, 27));

        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
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
