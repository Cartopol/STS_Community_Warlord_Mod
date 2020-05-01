package the_warlord.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BleedPower extends CustomWarlordModPower implements HealthBarRenderPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(BleedPower.class);
    public static final String POWER_ID = STATIC.ID;

    public BleedPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.DEBUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public int getHealthBarAmount() {
        int amount = 0;
            amount = this.amount;
        return Math.max(amount, 0);
    }


    @Override
    public void atStartOfTurn() {
        flash();
        addToTop(new LoseHPAction(this.owner, this.owner, this.amount));
        if (owner.hasPower(GushPower.POWER_ID)) {
            owner.getPower(GushPower.POWER_ID).flash();
            // Apply bleed equal to half the current Bleed amount, rounded up.
            addToBot(new ApplyPowerAction(owner, owner, new BleedPower(owner, (amount + 1) / 2)));
        } else {
            //remove if amount is 1, since halving 1 doesn't work
            if (amount == 1) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            } else {
                addToBot(new ReducePowerAction(owner, owner, this, amount / 2));
            }
        }
    }

    @Override
    public Color getColor() {
        return Color.PINK.cpy();
    }

    @Override
    public AbstractPower makeCopy() {
        return new BleedPower(owner, amount);
    }
}
