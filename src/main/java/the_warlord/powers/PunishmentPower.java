package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PunishmentPower extends CustomWarlordModPower implements OnParrySubscriber {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(PunishmentPower.class);
    public static final String POWER_ID = STATIC.ID;

    public PunishmentPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    //Behaviour implemented in ParryPower::onAttacked: additional parries equal to this.amount

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new PunishmentPower(owner, amount);
    }

    @Override
    public void onParry(boolean fullParry) {
        flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));

    }
}
