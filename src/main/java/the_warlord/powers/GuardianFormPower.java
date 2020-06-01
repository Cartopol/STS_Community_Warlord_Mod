package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.cards.warlord.Dizzy;

public class GuardianFormPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(GuardianFormPower.class);
    public static final String POWER_ID = STATIC.ID;

    public GuardianFormPower(AbstractCreature owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new MakeTempCardInDrawPileAction(new Dizzy(), 1, true, true));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new GuardianFormPower(owner, amount);
    }


}
