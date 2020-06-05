package the_warlord.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class AblutionCleanseDebuffsAction extends AbstractGameAction {
    private AbstractCreature c;
    private int block;

    public AblutionCleanseDebuffsAction(AbstractCreature c, int block) {
        this.c = c;
        this.duration = 0.5F;
        this.block = block;
    }

    public void update() {
        for (AbstractPower p: c.powers){
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
                addToTop(new GainBlockAction(this.c, this.block));
            }
        }
        this.isDone = true;
    }
}