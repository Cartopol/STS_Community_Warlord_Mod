package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import the_warlord.powers.BleedPower;

public class DoubleBleedAction extends AbstractGameAction {

    private boolean triple;
    public DoubleBleedAction(AbstractCreature target, AbstractCreature source, boolean triple) {
        this.target = target;
        this.source = source;
        this.triple = triple;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    public void update() {
        if (this.target.hasPower(BleedPower.POWER_ID)) {
            if (triple){
                addToTop(new ApplyPowerAction(this.target, this.source, new BleedPower(this.target,
                        (this.target.getPower(BleedPower.POWER_ID)).amount * 2),
                        (this.target.getPower(BleedPower.POWER_ID)).amount * 2));
            }
            else{
                addToTop(new ApplyPowerAction(this.target, this.source, new BleedPower(this.target,
                        (this.target.getPower(BleedPower.POWER_ID)).amount),
                        (this.target.getPower(BleedPower.POWER_ID)).amount));
            }

        }
        this.isDone = true;
    }
}
