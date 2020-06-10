package the_warlord.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.VFX.BloodSmokeEffect;

public class BloodMagicPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(BloodMagicPower.class);
    public static final String POWER_ID = STATIC.ID;

    public BloodMagicPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

//    @Override
//    public void atStartOfTurn() {
//        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
//        if (target != null) {
//            addToBot(new ApplyPowerAction(target, owner, new BleedPower(target, amount)));
//        }
//    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(GushPower.POWER_ID) && target.hasPower(BleedPower.POWER_ID)) {
            addToBot(new GainBlockAction(owner, target.getPower(BleedPower.POWER_ID).amount * amount));
            addToBot(new VFXAction(new BloodSmokeEffect(target.drawX, target.drawY)));
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new BloodMagicPower(owner, amount);
    }
}
