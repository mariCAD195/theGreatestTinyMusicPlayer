import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class Queue extends JPanel {
    public Queue(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(239, 27, 197));

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
