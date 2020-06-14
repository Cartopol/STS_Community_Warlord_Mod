package the_warlord.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CorneredPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(CorneredPower.class);
    public static final String POWER_ID = STATIC.ID;

    public CorneredPower(AbstractCreature owner, int amount) {
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
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        int increase = 0;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(TensionPower.POWER_ID)) {
            increase = amount * p.getPower(TensionPower.POWER_ID).amount;
        }

        return type == DamageInfo.DamageType.NORMAL ? damage + increase : damage;
    }


    @Override
    public AbstractPower makeCopy() {
        return new CorneredPower(owner, amount);
    }
}
