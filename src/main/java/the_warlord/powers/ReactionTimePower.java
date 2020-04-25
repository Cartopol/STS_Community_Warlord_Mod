package the_warlord.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReactionTimePower extends CustomWarlordModPower{
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ReactionTimePower.class);
    public static final String POWER_ID = STATIC.ID;

    public ReactionTimePower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new ReactionTimePower(owner, amount);
    }
}
