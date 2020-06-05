package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;
import the_warlord.powers.PostureStabilizedPower;

public class EyesWideOpen extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(EyesWideOpen.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;

    private static final int POSTURE = 2;
    private static final int POSTURE_STABILIZED = 1;

    private static final int UPGRADE_PLUS_POSTURE = 1;

    public EyesWideOpen() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = POSTURE;
        urMagicNumber = baseUrMagicNumber = POSTURE_STABILIZED;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PosturePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new PostureStabilizedPower(p, urMagicNumber)));

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_POSTURE);
            upgradeDescription();
        }
    }
}
