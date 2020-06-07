package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.defect.SeekAction;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;


public class Anticipation extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Anticipation.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int SEEK = 1;

    public Anticipation() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = SEEK;
    }

    @Override
    public void useParry() {
            addToBot(new SeekAction(magicNumber));
            if (!upgraded) {
                removeFromMasterParryDeck(this);
            }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}