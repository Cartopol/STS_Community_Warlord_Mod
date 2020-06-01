package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RefugePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(RefugePower.class);
    public static final String POWER_ID = STATIC.ID;

    public RefugePower(AbstractCreature owner, int amount) {
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
        if (owner.hasPower(PosturePower.POWER_ID)) {
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
        }
        if (owner.hasPower(TensionPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount)));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new RefugePower(owner, amount);
    }
}