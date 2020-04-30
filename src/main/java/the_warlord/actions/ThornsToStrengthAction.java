package the_warlord.actions;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import the_warlord.powers.TempThornsPower;

public class ThornsToStrengthAction extends AbstractGameAction {

    private int StrengthPerThorns;

    public ThornsToStrengthAction(AbstractCreature target, int StrengthPerThorns) {
        this.target = target;
        this.duration = this.startDuration;
        this.StrengthPerThorns = StrengthPerThorns;

    }

    public void update() {
        if(this.duration == startDuration){
            if (target.hasPower(ThornsPower.POWER_ID)) {
                addToTop(new ApplyPowerAction(target, target, new StrengthPower(target, target.getPower(ThornsPower.POWER_ID).amount * StrengthPerThorns)));
                addToTop(new RemoveSpecificPowerAction(target, target, ThornsPower.POWER_ID));
            }

            if (target.hasPower(TempThornsPower.POWER_ID)) {
                addToTop(new ApplyPowerAction(target, target, new StrengthPower(target, target.getPower(TempThornsPower.POWER_ID).amount * StrengthPerThorns)));
                addToTop(new RemoveSpecificPowerAction(target, target, TempThornsPower.POWER_ID));
            }

            tickDuration();
        }
        else{ this.isDone = true; }
    }
}
