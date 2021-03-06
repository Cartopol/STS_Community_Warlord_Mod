package the_warlord.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import the_warlord.util.CardMetaUtils;

public class ImprovisePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ImprovisePower.class);
    public static final String POWER_ID = STATIC.ID;
    private int cardsDoubledThisTurn = 0;


    public ImprovisePower(AbstractCreature owner, int amount) {
        super(STATIC);
        this.type = PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        loadRegion("flight");

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = amount == 1 ?  String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount);
    }


    @Override
    public void atStartOfTurnPostDraw() {
        if (!this.owner.hasPower(PosturePower.POWER_ID)) {
            flash();
            this.cardsDoubledThisTurn = 0;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - this.cardsDoubledThisTurn <= amount && (!this.owner.hasPower(PosturePower.POWER_ID))) {
            {
                ++cardsDoubledThisTurn;
                flash();
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }

                CardMetaUtils.playCardAdditionalTime(card, m);


            }
        }
    }
    @Override
    public AbstractPower makeCopy () {
        return new ImprovisePower(owner, amount);
    }
}
