package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.DiscardHandAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class Extremespeed extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Extremespeed.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;
    private static final int DRAW = 3;
    private static final int UPGRADE_PLUS_DRAW = 1;
    private static final int DAZED = 1;

    public Extremespeed() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAZED;
        urMagicNumber = baseUrMagicNumber = DRAW;
        cardsToPreview = new Dizzy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new DiscardHandAction());
        addToBot(new DrawCardAction(urMagicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new Dizzy(), magicNumber, true, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeUrMagicNumber(UPGRADE_PLUS_DRAW);
            upgradeName();
            upgradeDescription();
        }
    }
}