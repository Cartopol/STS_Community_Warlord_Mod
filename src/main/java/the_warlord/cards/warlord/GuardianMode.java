package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.ReactionTimePower;


public class GuardianMode extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(GuardianMode.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 3;

    private static final int DEXTERITY = 1;

    public GuardianMode() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEXTERITY;
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(ReactionTimePower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, p.getPower(ReactionTimePower.POWER_ID).amount)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }
}