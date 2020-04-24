package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.NextTurnSyrettePower;

public class Ration extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Ration.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int DRAW = 1;
    private static final int SYRETTE_AMOUNT = 1;

    public Ration() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new Syrette();
        this.urMagicNumber = this.baseUrMagicNumber = DRAW;
        this.magicNumber = this.baseMagicNumber = SYRETTE_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.urMagicNumber));


        if(upgraded){ addToBot(new MakeTempCardInHandAction(new Syrette(), this.magicNumber, false)); }
        else { addToBot(new ApplyPowerAction(p, p, new NextTurnSyrettePower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE)); }



    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}