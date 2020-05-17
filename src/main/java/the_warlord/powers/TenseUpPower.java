package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TenseUpPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(TenseUpPower.class);
    public static final String POWER_ID = STATIC.ID;

    public TenseUpPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(TensionPower.POWER_ID)) {
            flash();
            addToBot(new GainBlockAction(owner, amount));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new TenseUpPower(owner, amount);
    }
}