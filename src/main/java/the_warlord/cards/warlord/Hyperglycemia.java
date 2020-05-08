package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;


public class Hyperglycemia extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Hyperglycemia.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int TENSION = 1;
    private static final int ENERGY = 2;

    public Hyperglycemia() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENERGY;
        urMagicNumber = baseUrMagicNumber = TENSION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(ENERGY));
//        if(p.hasPower(ReactionTimePower.POWER_ID)){
//            addToBot(new RemoveSpecificPowerAction(p, p, ReactionTimePower.POWER_ID));
//        }
        addToBot(new ApplyPowerAction(p, p, new TensionPower(p, urMagicNumber)));
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