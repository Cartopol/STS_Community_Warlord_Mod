package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class Safeguard extends CustomWarlordModCard {

    public static final String ID = WarlordMod.makeID(Safeguard.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 13;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int TENSION_LOSS = 2;
    private static final int UPGRADE_TENSION_LOSS = 1;

    public Safeguard() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = TENSION_LOSS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        if(p.hasPower(TensionPower.POWER_ID)){
            addToBot(new ReducePowerAction(p, p, TensionPower.POWER_ID, magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_TENSION_LOSS);
            upgradeName();
            upgradeDescription();
        }
    }
}