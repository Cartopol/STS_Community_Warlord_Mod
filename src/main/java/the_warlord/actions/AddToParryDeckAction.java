package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.parry_deck.ParryDeck;

public class AddToParryDeckAction extends AbstractGameAction {
    private final AbstractCard card;

    public AddToParryDeckAction(AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        ParryDeck.masterParryDeck.addToRandomSpot(card.makeStatEquivalentCopy());
        AbstractDungeon.player.drawPile.removeCard(this.card);
        WarlordMod.logger.info("ParryDeck contains" + ParryDeck.masterParryDeck);

        this.isDone = true;
    }
}
