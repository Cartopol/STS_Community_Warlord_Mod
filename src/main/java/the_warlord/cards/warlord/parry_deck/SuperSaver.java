package the_warlord.cards.warlord.parry_deck;

import the_warlord.WarlordMod;
import the_warlord.actions.SuperSaverAction;
import the_warlord.characters.Warlord;

public class SuperSaver extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(SuperSaver.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    public SuperSaver() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new SuperSaverAction(upgraded));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            upgradeName();
            upgradeDescription();
        }
    }
}
