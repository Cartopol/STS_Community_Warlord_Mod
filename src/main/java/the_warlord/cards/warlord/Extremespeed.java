package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class Extremespeed extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Extremespeed.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int DRAW = 1;
    private static final int UPGRADE_PLUS_DRAW = 1;
    private static final int TENSION = 2;

    public Extremespeed() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = TENSION;
        urMagicNumber = baseUrMagicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TensionPower(p, this.magicNumber)));
        p.gameHandSize += urMagicNumber;
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