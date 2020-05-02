package the_warlord.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.parry_deck.ParryDeck;

import java.util.ArrayList;

public class ParryPower extends CustomWarlordModPower implements InvisiblePower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ParryPower.class);
    public static final String POWER_ID = STATIC.ID;

    public static boolean isParrying = false;
    public static boolean isFullParrying = false;

    public ParryPower(AbstractCreature owner) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;

        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        //make sure parry logic only works on normal attacks
        if (!info.type.equals(DamageInfo.DamageType.NORMAL)) {
            return damageAmount;
        }

        //this resets the isFullparrying flag in case there's another attack incoming. If the last attack is parried, we no longer reset this flag.
        isFullParrying = false;
        WarlordMod.logger.info("onAttacked called");

        int tolerance = 0;
        if (owner.hasPower(ReactionTimePower.POWER_ID)) {
            tolerance = owner.getPower(ReactionTimePower.POWER_ID).amount;
        }

        WarlordMod.logger.info("Damage " + damageAmount);
        WarlordMod.logger.info("DamageInfo.output " + info.output);

//        if (damageAmount > 0 && owner.currentBlock > 0 && damageAmount <= upperDamageBound && damageAmount >= lowerDamageBound) {

        if (damageAmount < info.output && damageAmount <= tolerance && owner.currentBlock <= tolerance) {
            WarlordMod.logger.info("Parrying");

            addToBot(new VFXAction(new FlameBarrierEffect(owner.dialogX, owner.dialogY)));
            isFullParrying = true;
            isParrying = true;
        }

        return damageAmount;
    }

    @Override
    public void onInitialApplication() {
        isParrying = false;
        isFullParrying = false;
    }


    @Override
    public void atStartOfTurn() {
        WarlordMod.logger.info(this.ID + " isParrying: " + isParrying + ". isFullParrying: " + isFullParrying);
        if (isParrying) {
            AbstractPlayer p = AbstractDungeon.player;

            //this calls onParry for all powers that implement onParrySubsciber
            for (AbstractPower pow : p.powers) {
                if (pow instanceof OnParrySubscriber) {
                    ((OnParrySubscriber) pow).onParry(isFullParrying);
                }
            }
            //this calls onParry for all cards in draw pile, hand, and discard pile that implement onParrySubscriber
            for (AbstractCard c : p.drawPile.group) {
                if (c instanceof OnParrySubscriber) {
                    ((OnParrySubscriber) c).onParry(isFullParrying);
                }
            }
            for (AbstractCard c : p.hand.group) {
                if (c instanceof OnParrySubscriber) {
                    ((OnParrySubscriber) c).onParry(isFullParrying);
                }
            }
            for (AbstractCard c : p.discardPile.group) {
                if (c instanceof OnParrySubscriber) {
                    ((OnParrySubscriber) c).onParry(isFullParrying);
                }
            }

            isParrying = false;

            ArrayList<AbstractCard> parryOptions = ParryDeck.getParryOptions();

            if (isFullParrying) {
                isFullParrying = false;
                for (AbstractCard c : parryOptions) {
//                    c.upgrade();
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
        return new ParryPower(owner);
    }
}
