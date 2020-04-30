package the_warlord.actions;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ClearAllDebuffsAction extends AbstractGameAction {

    public ClearAllDebuffsAction(AbstractCreature target) { this.target = target; }

    public void update() {
        for (AbstractPower power : target.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF && !(power instanceof InvisiblePower)) {
                addToTop(new RemoveSpecificPowerAction(target, target, power));
            }
        }
        isDone = true;
    }
}
