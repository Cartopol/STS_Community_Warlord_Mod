package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PosturePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(PosturePower.class);
    public static final String POWER_ID = STATIC.ID;

    public PosturePower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        int damageTaken = damageAmount - amount;
        //only trigger if damageAmount > 0 and is Attack Damage
        if (damageTaken > 0) {
            flash();
            addToBot(new ApplyPowerAction(owner, owner, new TensionPower(owner, amount)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
        return damageTaken < 0 ? 0 : damageTaken;
    }

    @Override
    public AbstractPower makeCopy() {
        return new PosturePower(owner, amount);
    }
}
