package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SelfDrawReductionPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(SelfDrawReductionPower.class);
    public static final String POWER_ID = STATIC.ID;

    public SelfDrawReductionPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.DEBUFF;

        this.owner = owner;
        this.amount = amount;

        this.isTurnBased = true;
        updateDescription();
        this.loadRegion("lessdraw");
    }

    @Override
    public void onInitialApplication() {
        --AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

    @Override
    public void onRemove() {
        ++AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }


    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public AbstractPower makeCopy() {
        return new SelfDrawReductionPower(owner, amount);
    }
}
