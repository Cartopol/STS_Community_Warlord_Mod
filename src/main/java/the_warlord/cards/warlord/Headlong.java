package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class Headlong extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Headlong.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int DRAW = 4;
    private static final int UPGRADE_DRAW = 1;
    private static final int DEBUFF_DRAW = 1;
    private static final int TENSION = 3;


    public Headlong() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
//        this.urMagicNumber = this.baseUrMagicNumber = DEBUFF_DRAW;
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.urMagicNumber = this.baseUrMagicNumber = TENSION;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
//        addToBot(new ApplyPowerAction(p, p, new SelfDrawReductionPower(p, urMagicNumber)));
        addToBot(new ApplyPowerAction(p, p, new TensionPower(p, urMagicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_DRAW);

            upgradeName();
            upgradeDescription();
        }
    }
}