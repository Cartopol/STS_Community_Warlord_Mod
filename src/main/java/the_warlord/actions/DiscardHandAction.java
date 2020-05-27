package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardHandAction extends AbstractGameAction {

    public DiscardHandAction() {}

    public void update() {
        int count = AbstractDungeon.player.hand.size();
        this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, count, true));

        isDone = true;
    }
}
