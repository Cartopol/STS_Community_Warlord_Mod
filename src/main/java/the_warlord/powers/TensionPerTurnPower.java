package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;

public class TensionPerTurnPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(TensionPerTurnPower.class);
    public static final String POWER_ID = STATIC.ID;

    public TensionPerTurnPower(AbstractCreature owner, int amount) {
        super(STATIC);
        this.type = PowerType.DEBUFF;
        this.owner = owner;
        this.amount = amount;
        loadRegion("flight");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new TensionPower(owner, amount)));
    }

    @Override
    public AbstractPower makeCopy() {
        return new TensionPerTurnPower(owner, amount);
    }
}
