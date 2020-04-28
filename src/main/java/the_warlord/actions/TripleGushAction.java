package the_warlord.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import the_warlord.powers.GushPower;

public class TripleGushAction extends AbstractGameAction {

    public TripleGushAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    public void update() {
        if (this.target.hasPower(GushPower.POWER_ID)) {
            addToTop(new ApplyPowerAction(this.target, this.source, new GushPower(this.target,
                    (this.target.getPower(GushPower.POWER_ID)).amount * 2),
                    (this.target.getPower(GushPower.POWER_ID)).amount * 2));
        }
        this.isDone = true;
    }
}
