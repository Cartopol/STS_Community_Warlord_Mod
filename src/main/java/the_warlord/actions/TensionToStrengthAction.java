package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import the_warlord.powers.TensionPower;

public class TensionToStrengthAction extends AbstractGameAction {

    private int StrengthPerThorns;

    public TensionToStrengthAction(AbstractCreature target, int StrengthPerThorns) {
        this.target = target;
        this.duration = this.startDuration;
        this.StrengthPerThorns = StrengthPerThorns;

    }

    public void update() {
        if(this.duration == startDuration){
            if (target.hasPower(TensionPower.POWER_ID)) {
                addToTop(new ApplyPowerAction(target, target, new StrengthPower(target, target.getPower(TensionPower.POWER_ID).amount * StrengthPerThorns)));
                addToTop(new RemoveSpecificPowerAction(target, target, TensionPower.POWER_ID));
            }
//
//            if (target.hasPower(TempThornsPower.POWER_ID)) {
//                addToTop(new ApplyPowerAction(target, target, new StrengthPower(target, target.getPower(TempThornsPower.POWER_ID).amount * StrengthPerThorns)));
//                addToTop(new RemoveSpecificPowerAction(target, target, TempThornsPower.POWER_ID));
//            }

            tickDuration();
        }
        else{ this.isDone = true; }
    }
}
