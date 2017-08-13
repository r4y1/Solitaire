package game;

import java.awt.*;

class TablePile extends CardPile {

    TablePile (final int x, final int y, final int c) {
        // initialize the parent class
        super(x, y);
        // then initialize our pile of cards
        for (int i = 0; i < c; i++) {
            addCard(Solitaire.deckPile.pop());
        }
        // flip topmost card face up
        top().flip();
    }

    public boolean canTake (Card aCard) {
        if (empty())
            return aCard.getRank() == 12;
        Card topCard = top();
        return (aCard.color() != topCard.color()) &&
                (aCard.getRank() == topCard.getRank() - 1);
    }

    public boolean includes (final int tx, final int ty) {
        // don't test bottom of card
        return x <= tx && tx <= x + Card.width &&
                y <= ty;
    }

    public void select (int tx, int ty) {
        if (empty())
            return;

        // if face down, then flip
        Card topCard = top();
        if (! topCard.isFaceUp()) {
            topCard.flip();
            return;
        }

        // else see if any suit pile can take card
        topCard = pop();
        for (int i = 0; i < 4; i++)
            if (Solitaire.suitPile[i].canTake(topCard)) {
                Solitaire.suitPile[i].addCard(topCard);
                return;
            }
        // else see if any other table pile can take card
        for (int i = 0; i < 7; i++)
            if (Solitaire.tableau[i].canTake(topCard)) {
                Solitaire.tableau[i].addCard(topCard);
                return;
            }
        // else put it back on our pile
        addCard(topCard);
    }

    private int stackDisplay(final Graphics g, final Card aCard) {
        int localy;
        if (aCard == null) {
            return y;
        }
        localy = stackDisplay(g, aCard.link);
        aCard.draw(g, x, localy);
        return localy + 35;
    }

    public void display (Graphics g) {
        stackDisplay(g, top());
    }

}
