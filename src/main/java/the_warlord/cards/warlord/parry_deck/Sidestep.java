package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class Sidestep extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Sidestep.class);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_DRAW = 1;


    public Sidestep() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;

    }

    @Override
    public void useParry() {
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);

            upgradeName();
            upgradeDescription();
        }
    }
}
