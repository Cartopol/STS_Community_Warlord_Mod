package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QuickDrawPower extends CustomWarlordModPower implements OnParrySubscriber {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(QuickDrawPower.class);
    public static final String POWER_ID = STATIC.ID;

    public QuickDrawPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        String baseString = amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1];

        description = String.format(baseString, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new QuickDrawPower(owner, amount);
    }

    @Override
    public void onParry(boolean fullParry) {
        flash();
        addToBot(new DrawCardAction(owner, amount));
    }
}
