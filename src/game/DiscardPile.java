package game;

class DiscardPile extends CardPile {

    DiscardPile (int x, int y) {
        super (x, y);
    }

    public void addCard (Card aCard) {
        if (! aCard.isFaceUp()) {
            aCard.flip();
        }
        super.addCard(aCard);
    }

    public void select (int tx, int ty) {
        if (empty())
            return;
        Card topCard = pop();
        for (int i = 0; i < 4; i++)
            if (Solitaire.suitPile[i].canTake(topCard)) {
                Solitaire.suitPile[i].addCard(topCard);
                return;
            }
        for (int i = 0; i < 7; i++)
            if (Solitaire.tableau[i].canTake(topCard)) {
                Solitaire.tableau[i].addCard(topCard);
                return;
            }
        // nobody can use it, put it back on our list
        addCard(topCard);
    }
}
