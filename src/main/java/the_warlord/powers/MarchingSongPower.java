package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MarchingSongPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(MarchingSongPower.class);
    public static final String POWER_ID = STATIC.ID;

    private static final int THRESHOLD = 4;

    public MarchingSongPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;
        this.amount2 = 0;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1){ this.description = DESCRIPTIONS[0]; }
        else{ this.description = String.format(DESCRIPTIONS[1], this.amount); }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        ++amount2;
        if (amount2 == THRESHOLD) {
            this.flash();
            amount2 = 0;
            addToBot(new DrawCardAction(owner, amount));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new MarchingSongPower(owner, amount);
    }
}