package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BoilingBloodPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(BoilingBloodPower.class);
    public static final String POWER_ID = STATIC.ID;

    public BoilingBloodPower(AbstractCreature owner, int amount, int bleedAmount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;
        this.amount2 = bleedAmount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        int baseStringIndex = amount2 > 0 ? 1 : 0;
        this.description =  String.format(DESCRIPTIONS[baseStringIndex], this.amount, this.amount2);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GushPower(m, amount)));
        if (amount2 > 0) {
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new BleedPower(m, amount2)));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BoilingBloodPower(owner, amount, amount2);
    }
}
