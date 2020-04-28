package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import the_warlord.powers.GushPower;

public class GushAction extends AbstractGameAction {

    private boolean triple;
    public GushAction(AbstractCreature target, AbstractCreature source, boolean triple) {
        this.target = target;
        this.source = source;
        this.triple = triple;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    public void update() {
        if (this.target.hasPower(GushPower.POWER_ID)) {
            if (triple){
                addToTop(new ApplyPowerAction(this.target, this.source, new GushPower(this.target,
                        (this.target.getPower(GushPower.POWER_ID)).amount * 2),
                        (this.target.getPower(GushPower.POWER_ID)).amount * 2));
            }
            else{
                addToTop(new ApplyPowerAction(this.target, this.source, new GushPower(this.target,
                        (this.target.getPower(GushPower.POWER_ID)).amount),
                        (this.target.getPower(GushPower.POWER_ID)).amount));
            }

        }
        this.isDone = true;
    }
}
