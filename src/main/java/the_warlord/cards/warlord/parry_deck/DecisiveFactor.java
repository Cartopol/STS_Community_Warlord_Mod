package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class DecisiveFactor extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(DecisiveFactor.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int DRAW_AMOUNT = 1;

    public DecisiveFactor() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW_AMOUNT;
    }

    @Override
    public void useParry() {
        if (this.upgraded) {
            addToBot(new ApotheosisAction());
            removeFromMasterParryDeck(this);
        }
        else { addToBot(new ArmamentsAction(true)); }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            upgradeName();
            upgradeDescription();
        }
    }
}
