package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.AddToParryDeckAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.cards.warlord.parry_deck.Fortress;
import the_warlord.characters.Warlord;

public class BuildingFortress extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(BuildingFortress.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    public BuildingFortress() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        cardsToPreview = new Fortress();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddToParryDeckAction(new Fortress(), false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeDescription();
        }
    }
}
