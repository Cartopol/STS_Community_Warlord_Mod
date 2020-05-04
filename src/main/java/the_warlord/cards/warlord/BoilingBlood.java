package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.BoilingBloodPower;

public class BoilingBlood extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(BoilingBlood.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int GUSH = 1;
    private static final int BLEED = 0;
    private static final int UPGRADE_PLUS_BLEED = 2;

    public BoilingBlood() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = GUSH;
        urMagicNumber = baseUrMagicNumber = BLEED;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BoilingBloodPower(p, this.magicNumber, urMagicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeUrMagicNumber(UPGRADE_PLUS_BLEED);

            upgradeName();
            upgradeDescription();
        }
    }
}