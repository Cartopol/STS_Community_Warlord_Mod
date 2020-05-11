package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PositioningPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(PositioningPower.class);
    public static final String POWER_ID = STATIC.ID;

    public PositioningPower(AbstractCreature owner, int amount) {
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
        return new PositioningPower(owner, amount);
    }

    @Override
    public void atStartOfTurn() {
            addToBot(new ApplyPowerAction(owner, owner, new PosturePower(owner, amount)));

    }

//    @Override
//    public void onParry(boolean fullParry) {
//        flash();
//        this.addToBot(new GainBlockAction(owner, amount));
//    }
}
