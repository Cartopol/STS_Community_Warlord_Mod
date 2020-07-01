package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.GuardianFormPower;
import the_warlord.powers.PosturePower;


public class GuardianForm extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(GuardianForm.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 3;

    private static final int POSTURE = 15;
    private static final int DIZZY = 1;


    public GuardianForm() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POSTURE;
        urMagicNumber = baseUrMagicNumber = DIZZY;
        this.isEthereal = true;
        cardsToPreview = new Dizzy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PosturePower(p, magicNumber)));

        addToBot(new ApplyPowerAction(p, p, new GuardianFormPower(p, urMagicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }
}