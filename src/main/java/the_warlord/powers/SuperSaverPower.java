package the_warlord.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.WarlordMod;
import the_warlord.actions.ReduceCostRandomCardAction;

public class SuperSaverPower extends CustomWarlordModPower implements InvisiblePower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(SuperSaverPower.class);
    public static final String POWER_ID = STATIC.ID;

    public static boolean untilEndOfCombat = false;

    public SuperSaverPower(AbstractCreature owner, boolean untilEndOfCombat) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;

        this.untilEndOfCombat = untilEndOfCombat;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        WarlordMod.logger.info("SuperSaverPower atStartOfTurn");
        addToBot(new ReduceCostRandomCardAction(untilEndOfCombat));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SuperSaverPower(owner, untilEndOfCombat);
    }
}
