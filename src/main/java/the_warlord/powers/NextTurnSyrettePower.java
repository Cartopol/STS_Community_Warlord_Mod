package the_warlord.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.Syrette;

public class NextTurnSyrettePower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = WarlordMod.makeID("NextTurnSyrettePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextTurnSyrettePower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        isTurnBased = true;
        // TODO: add proper art
        loadRegion("explosive");
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
    public void stackPower(int i) {
        super.stackPower(i);
        updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new NextTurnSyrettePower(owner, amount);
    }


}