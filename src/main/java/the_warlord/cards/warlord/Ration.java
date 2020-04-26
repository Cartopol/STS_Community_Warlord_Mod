package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class Ration extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Ration.class);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;
    private static final int SYRETTE_AMOUNT = 1;
    private static final int UPGRADE_PLUS_SYRETTE_AMOUNT = 1;

    public Ration() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new Syrette();
        this.magicNumber = this.baseMagicNumber = SYRETTE_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Syrette(), this.magicNumber, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_SYRETTE_AMOUNT);

            upgradeName();
            upgradeDescription();
        }
    }
}