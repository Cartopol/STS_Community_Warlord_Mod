package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.parry_deck.ParryDeck;

public class AddToParryDeckAction extends AbstractGameAction {
    private final AbstractCard card;
    private boolean removeFromDrawPile;

    public AddToParryDeckAction(AbstractCard card, boolean fromDrawPile) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.removeFromDrawPile = fromDrawPile;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (!removeFromDrawPile) {
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, true, false));
        }
        ParryDeck.masterParryDeck.addToRandomSpot(card.makeStatEquivalentCopy());
            AbstractDungeon.player.drawPile.removeCard(this.card);
        WarlordMod.logger.info("Added " + card + " to Parry Deck from drawPile: " + removeFromDrawPile);

        WarlordMod.logger.info("ParryDeck contains" + ParryDeck.masterParryDeck);

        this.isDone = true;
    }
}
