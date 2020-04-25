package the_warlord.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import the_warlord.cards.warlord.parry_deck.ParryDeck;

import java.util.ArrayList;
public class ParryPower extends CustomWarlordModPower implements InvisiblePower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ParryPower.class);
    public static final String POWER_ID = STATIC.ID;

    public static boolean isParrying = false;
    public static boolean isFullParrying = false;

    public ParryPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        //this resets the isFullparrying flag in case there's another attack incoming. If the last attack is parried, we no longer reset this flag.
        isFullParrying = false;

        if (damageAmount == owner.currentBlock) {
            addToBot(new VFXAction(new FlameBarrierEffect(owner.dialogX, owner.dialogY)));
            isFullParrying = true;
            isParrying = true;
        }

        return damageAmount;
    }


    @Override
    public void atStartOfTurn() {
        if (isParrying) {
            isParrying = false;

            ArrayList<AbstractCard> parryOptions = ParryDeck.getParryOptions();

            if (isFullParrying) {
                isFullParrying = false;
                for (AbstractCard c : parryOptions) {
                    c.upgrade();
                }
            }

            if (parryOptions.size() > 0) {
                this.addToBot(new ChooseOneAction(parryOptions));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ParryPower(owner, amount);
    }
}
