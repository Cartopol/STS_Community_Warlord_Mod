package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import the_warlord.powers.GushPower;

public class DoubleGushAction extends AbstractGameAction {

    public DoubleGushAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    public void update() {
        if (this.target.hasPower(GushPower.POWER_ID)) {
            addToTop(new ApplyPowerAction(this.target, this.source, new GushPower(this.target,
                    (this.target.getPower(GushPower.POWER_ID)).amount),
                    (this.target.getPower(GushPower.POWER_ID)).amount));
        }
        this.isDone = true;
    }
}
