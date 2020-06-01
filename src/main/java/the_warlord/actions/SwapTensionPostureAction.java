package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.powers.PosturePower;
import the_warlord.powers.TensionPower;

public class SwapTensionPostureAction extends AbstractGameAction {

    public SwapTensionPostureAction() {
        this.duration = this.startDuration;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int currentPosture = 0;
        int currentTension = 0;
        if (p.hasPower(PosturePower.POWER_ID)) {
            currentPosture += p.getPower(PosturePower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, PosturePower.POWER_ID));
        }
        if (p.hasPower(TensionPower.POWER_ID)) {
            currentTension += p.getPower(TensionPower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, TensionPower.POWER_ID));
        }
        if (currentPosture > 0) {
            addToBot(new ApplyPowerAction(p, p, new TensionPower(p, currentPosture)));
        }
        if (currentTension > 0) {
            addToBot(new ApplyPowerAction(p, p, new PosturePower(p, currentTension)));
        }
        this.isDone = true;
    }
}
