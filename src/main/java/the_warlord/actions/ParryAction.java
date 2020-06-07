package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.parry_deck.ParryDeck;

import java.util.ArrayList;

public class ParryAction extends AbstractGameAction {

    public ParryAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> parryOptions = ParryDeck.getParryOptions();
            WarlordMod.logger.info("parryOptions: " + parryOptions);
            if (parryOptions.size() > 0) {
                AbstractDungeon.cardRewardScreen.chooseOneOpen(parryOptions);
            }
            this.tickDuration();
        } else {
            this.tickDuration();
        }
    }
}
