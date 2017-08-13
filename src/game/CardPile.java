package game;

import java.awt.*;

class CardPile {

    // coordinates of the card pile
    private Card firstCard;

    int x;
    int y;


    CardPile (int xl, int yl) {
        x = xl;
        y = yl;
        firstCard = null;
    }

    // access to cards are not overridden

    Card top() {
        return firstCard;
    }

    boolean empty() {
        return firstCard == null;
    }

    Card pop() {
        Card result = null;
        if (firstCard != null) {
            result = firstCard;
            firstCard = firstCard.link;
        }
        return result;
    }

    // the following are sometimes overridden

    public boolean includes (int tx, int ty) {
        return x <= tx && tx <= x + Card.width &&
                y <= ty && ty <= y + Card.height;
    }

    public void select (int tx, int ty) {
        // do nothing
    }

    public void addCard (Card aCard) {
        aCard.link = firstCard;
        firstCard = aCard;
    }

    public void display (Graphics g) {
        g.setColor(Color.black);
        if (firstCard == null)
            g.drawRect(x, y, Card.width, Card.height);
        else
            firstCard.draw(g, x, y);
    }

    public boolean canTake (Card aCard) {
        return false;
    }
}
