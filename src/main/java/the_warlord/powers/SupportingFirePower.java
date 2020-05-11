package the_warlord.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SupportingFirePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(SupportingFirePower.class);
    public static final String POWER_ID = STATIC.ID;

    public SupportingFirePower(AbstractCreature owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                int reactionBonus = 0;
                if (owner.hasPower(PosturePower.POWER_ID)) {
                    reactionBonus = owner.getPower(PosturePower.POWER_ID).amount;
                }

                this.flash();
                this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount + reactionBonus, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new SupportingFirePower(owner, amount);
    }
}
