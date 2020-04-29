package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.cards.warlord.Syrette;

public class EndlessNextTurnSyrettePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(EndlessNextTurnSyrettePower.class);
    public static final String POWER_ID = STATIC.ID;

    public EndlessNextTurnSyrettePower(AbstractCreature owner, int amount) {
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
    public void atStartOfTurn() {
        flash();
        addToBot(new MakeTempCardInHandAction(new Syrette(), amount, false));
    }

    @Override
    public AbstractPower makeCopy() {
        return new EndlessNextTurnSyrettePower(owner, amount);
    }
}
