package the_warlord.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.WarlordMod;

public class FocusPunchPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(FocusPunchPower.class);
    public static final String POWER_ID = STATIC.ID;
    private AbstractMonster target;
    private Boolean damageTaken;

    public FocusPunchPower(AbstractCreature owner, int amount, AbstractMonster target) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.target = target;
        this.owner = owner;
        this.amount = amount;

        this.damageTaken = false;

        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!damageTaken && info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner && damageAmount > 0) {
            flash();
            damageTaken = true;
            addToBot(new ApplyPowerAction(owner, owner, new TensionPower(owner, 5)));
//            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void atStartOfTurn () {
        WarlordMod.logger.info("target of FocusPunch " + this.target);
        addToBot(new DamageAction(target, new DamageInfo(this.owner, amount), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public AbstractPower makeCopy() {
        return new FocusPunchPower(owner, amount, target);
    }
}
