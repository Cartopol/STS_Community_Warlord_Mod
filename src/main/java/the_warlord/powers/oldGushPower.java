package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class oldGushPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(oldGushPower.class);
    public static final String POWER_ID = STATIC.ID;

    public oldGushPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.DEBUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0) {
            if(this.owner.hasPower(BleedPower.POWER_ID)) {
                int x = this.owner.getPower(BleedPower.POWER_ID).amount;
                flash();
                addToTop(new ApplyPowerAction(this.owner, this.owner, new BleedPower(this.owner, x), x));
            }
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
        if (owner.hasPower(BleedPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, BleedPower.POWER_ID));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new oldGushPower(owner, amount);
    }
}
