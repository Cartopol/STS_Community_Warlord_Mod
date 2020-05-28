package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.AnticipationPower;


public class Anticipation extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Anticipation.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int SEEK = 1;

    public Anticipation() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = SEEK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new AnticipationPower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBaseCost(UPGRADED_COST);
            upgradeName();
            upgradeDescription();
        }
    }
}