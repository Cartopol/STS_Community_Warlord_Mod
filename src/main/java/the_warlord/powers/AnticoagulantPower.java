package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AnticoagulantPower extends CustomWarlordModPower implements OnParrySubscriber {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(AnticoagulantPower.class);
    public static final String POWER_ID = STATIC.ID;

    public AnticoagulantPower(AbstractCreature owner, int amount) {
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
    public AbstractPower makeCopy() {
        return new PunishmentPower(owner, amount);
    }

    @Override
    public void onParry(boolean fullParry) {
        flash();
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDead && !monster.isDying) {
                addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new GushPower(monster, amount)));
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            }
        }

    }
}
