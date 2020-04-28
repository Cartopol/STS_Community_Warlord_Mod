package the_warlord.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.WarlordMod;

public class FocusPunchPower extends AbstractPower {

    public static final String POWER_ID = WarlordMod.makeID(FocusPunchPower.class);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static AbstractMonster target;

    public FocusPunchPower(AbstractCreature owner, int damage, AbstractMonster target) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.target = target;
        this.amount = damage;
        updateDescription();
        loadRegion("flameBarrier");
    }


    public void stackPower(int stackAmount) {
        if (this.amount == -1) { return; }
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner && damageAmount > 0) {
            flash();
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        return damageAmount;
    }

    public void atStartOfTurn () {
        addToBot(new DamageAction(target, new DamageInfo(this.owner, amount), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }


    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }
}