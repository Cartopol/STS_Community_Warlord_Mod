package the_warlord.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
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
    public void onRemove() {
        addToTop(new LoseHPAction(this.owner, this.owner, this.amount));
    }

    @Override
    public int getHealthBarAmount() {
        int amount = 0;
        if (owner.hasPower(GushPower.POWER_ID)) {
            amount = this.amount;
        }
        return Math.max(amount, 0);
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
