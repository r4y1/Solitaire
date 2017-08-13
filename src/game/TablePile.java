package game;

import java.awt.*;

class TablePile extends IsCanTakeCardPile {

    static final int CARD_RANGE = 35;

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
        int margin = CARD_RANGE * (getCountOfCards() - 1);
        margin = margin >= 0 ? margin : 0;
        return x <= tx && tx <= x + Card.width
            && y + margin <= ty
            && ty <= y + margin + Card.height;
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
        super.select(tx, ty);
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
