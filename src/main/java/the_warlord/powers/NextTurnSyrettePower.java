package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.cards.warlord.Syrette;

public class NextTurnSyrettePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(NextTurnSyrettePower.class);
    public static final String POWER_ID = STATIC.ID;

    public NextTurnSyrettePower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.isTurnBased = true;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = amount == 0 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new MakeTempCardInHandAction(new Syrette(), 1, false));
        addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

    @Override
    public AbstractPower makeCopy() {
        return new NextTurnSyrettePower(owner, amount);
    }
}
