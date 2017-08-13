package game;

class DiscardPile extends IsCanTakeCardPile {

    DiscardPile (final int x, final int y) {
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
        super.select(tx, ty);
    }
}
