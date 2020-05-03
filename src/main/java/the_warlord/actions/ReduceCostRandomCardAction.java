package the_warlord.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;

public class ReduceCostRandomCardAction extends AbstractGameAction {

    private boolean untilEndOfCombat;
    private AbstractPlayer p = AbstractDungeon.player;

    public ReduceCostRandomCardAction(boolean untilEndOfCombat) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.untilEndOfCombat = untilEndOfCombat; }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            WarlordMod.logger.info("ReduceCostAction update triggered");

            boolean betterPossible = false;
            boolean possible = false;
            for (AbstractCard c : this.p.hand.group) {
                if (c.costForTurn > 0) {
                    betterPossible = true;
                    continue;
                }
                if (c.cost > 0)
                    possible = true;
            }
            if (betterPossible || possible)
                findAndModifyCard(betterPossible);
        }
        tickDuration();
    }

    private void findAndModifyCard(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        WarlordMod.logger.info("card selected: " + c);
        if (better) {
            if (c.costForTurn > 0) {
                if (untilEndOfCombat) {
                    c.cost = 0;
                }
                c.costForTurn = 0;
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            } else {
                findAndModifyCard(better);
            }
        } else if (c.cost > 0) {
            if (untilEndOfCombat) {
                c.cost = 0;
            }
            c.costForTurn = 0;
            c.isCostModified = true;
            c.superFlash(Color.GOLD.cpy());
        } else {
            findAndModifyCard(better);
        }
    }
}
