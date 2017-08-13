package game;

class SuitPile extends CardPile {

    SuitPile (int x, int y) { super(x, y); }

    public boolean canTake (Card aCard) {
        if (empty())
            return aCard.getRank() == 0;
        Card topCard = top();
        return (aCard.suit() == topCard.suit()) &&
                (aCard.getRank() == 1 + topCard.getRank());
    }
}
