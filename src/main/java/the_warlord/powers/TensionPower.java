package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TensionPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(TensionPower.class);
    public static final String POWER_ID = STATIC.ID;

    public TensionPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.DEBUFF;

        this.owner = owner;
        this.amount = amount;

        this.isTurnBased = true;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS)));
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }


    @Override
    public AbstractPower makeCopy() {
        return new TensionPower(owner, amount);
    }

//    @Override
//    public void onParry(boolean fullParry) {
//        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
//    }
}
