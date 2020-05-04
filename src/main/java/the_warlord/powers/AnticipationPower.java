package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AnticipationPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(AnticipationPower.class);
    public static final String POWER_ID = STATIC.ID;

    public AnticipationPower(AbstractCreature owner, int amount) {
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
    public void atStartOfTurn() {
        addToBot(new BetterDrawPileToHandAction(amount));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof AnticipationPower) {
            --AbstractDungeon.player.gameHandSize;
        }
    }


    @Override
    public void onInitialApplication() {
            --AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.gameHandSize += amount;
    }


    @Override
    public AbstractPower makeCopy() {
        return new AnticipationPower(owner, amount);
    }
}
