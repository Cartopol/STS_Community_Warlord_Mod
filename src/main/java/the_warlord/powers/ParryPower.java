package the_warlord.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import the_warlord.WarlordMod;
import the_warlord.actions.ParryAction;
import the_warlord.cards.warlord.parry_deck.ParryDeck;
import the_warlord.patches.squeenyInvertForceField;
import the_warlord.relics.RelicParrySubscriber;

public class ParryPower extends CustomWarlordModPower { //implements InvisiblePower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ParryPower.class);
    public static final String POWER_ID = STATIC.ID;

    public static boolean isParrying = false;
    public static boolean isFullParrying = false;

    public int damageParriedThisTurn = 0;

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

        if (damageAmount == 0) {
            this.damageParriedThisTurn += info.output;
            WarlordMod.logger.info("DamageParriedThisTurn = " + damageParriedThisTurn);
        }

        if (damageAmount < info.output && damageAmount == 0 && owner.currentBlock == 0) {
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
    public void atStartOfTurnPostDraw() {
        WarlordMod.logger.info("Amount of damage parried " + damageParriedThisTurn);
        ParryDeck.setParried(false);
        if (isParrying) {
            squeenyInvertForceField.parriedDamageThisTurn.set(AbstractDungeon.player, damageParriedThisTurn);
            ParryDeck.setParried(true);
            AbstractPlayer p = AbstractDungeon.player;

            //check for number of parries to perform from PunishmentPower
            int additionalParries = p.hasPower(PunishmentPower.POWER_ID) ? p.getPower(PunishmentPower.POWER_ID).amount : 0;

            //this calls onParry for all powers that implement onParrySubsciber
            for (AbstractPower pow : p.powers) {
                if (pow instanceof OnParrySubscriber) {
                    ((OnParrySubscriber) pow).onParry(isFullParrying);
                }
            }
            //this calls onParry for all relics that implement onParrySubsciber
            for (AbstractRelic r : p.relics) {
                if (r instanceof RelicParrySubscriber) {
                    ((RelicParrySubscriber) r).onParry(isFullParrying);
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
            isFullParrying = false;

//            ArrayList<AbstractCard> parryOptions = ParryDeck.getParryOptions();
//            WarlordMod.logger.info("parryOptions : " + parryOptions);


//            if (parryOptions.size() > 0) {
//                this.addToBot(new ChooseOneAction(parryOptions));

            addToBot(new ParryAction());
                for (int i = 0; i < additionalParries; ++i) {
//                    ArrayList<AbstractCard> additionalParryOptions = ParryDeck.getParryOptions();
//                    WarlordMod.logger.info("additionalParryOptions : " + additionalParryOptions);
//
//                    this.addToBot(new ChooseOneAction(additionalParryOptions));
                    addToBot(new ParryAction());

                }
            }
        }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            damageParriedThisTurn = 0;
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
