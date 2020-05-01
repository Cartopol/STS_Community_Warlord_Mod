package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.DoubleTeamPower;

public class DoubleTeam extends CustomWarlordModCard {

    public static final String ID = WarlordMod.makeID(DoubleTeam.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int POWER_AMOUNT = 1;
    private static final int DRAW = 2;

    public DoubleTeam() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POWER_AMOUNT;
        urMagicNumber = baseUrMagicNumber = DRAW;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){ addToBot(new DrawCardAction(DRAW));}
        addToBot(new ApplyPowerAction(p, p, new DoubleTeamPower(p, POWER_AMOUNT)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}