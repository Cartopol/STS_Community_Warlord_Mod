package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

    private static final int COST = 2;
    private static final int BLOCK = 10;

    public BuildingFortress() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        AbstractCard c = new Fortress();
        if (upgraded) {
            c.upgrade();
        }
        cardsToPreview = c;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        AbstractCard c = new Fortress();
        if (upgraded) {
            c.upgrade();
        }
        addToBot(new AddToParryDeckAction(c, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}
