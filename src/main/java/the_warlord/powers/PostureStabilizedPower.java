package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PostureStabilizedPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(PostureStabilizedPower.class);
    public static final String POWER_ID = STATIC.ID;

    public PostureStabilizedPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        int baseDescIndex = 1;
        if (amount == 1) {baseDescIndex = 0;}
        description = String.format(DESCRIPTIONS[baseDescIndex], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new PostureStabilizedPower(owner, amount);
    }

}
