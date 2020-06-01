package the_warlord.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.actions.SwapTensionPostureAction;

public class GuardianFormPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(GuardianFormPower.class);
    public static final String POWER_ID = STATIC.ID;

    public GuardianFormPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    /*
    Functionality implemented in PosturePower and TensionPower classes: start of turn decay removed if you have
    GuardianFormPower
     */

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0]);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new SwapTensionPostureAction());
    }

    @Override
    public AbstractPower makeCopy() {
        return new GuardianFormPower(owner, amount);
    }


}
