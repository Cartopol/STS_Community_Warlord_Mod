package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class GuardianFormPower extends CustomWarlordModPower implements OnPostureBrokenSubscriber {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(GuardianFormPower.class);
    public static final String POWER_ID = STATIC.ID;

    public GuardianFormPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        int baseStringIndex = amount == 1 ? 0 : 1;
        description = String.format(DESCRIPTIONS[baseStringIndex], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new GuardianFormPower(owner, amount);
    }

    @Override
    public void onPostureBroken(int postureAmount) {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, postureAmount * amount)));
    }
}
