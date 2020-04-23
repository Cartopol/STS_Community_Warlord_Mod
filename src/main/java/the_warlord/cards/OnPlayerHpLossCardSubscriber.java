package the_warlord.cards;

public interface OnPlayerHpLossCardSubscriber {
    int onPlayerHpLossWhileInHand(int originalHpLoss);
}
