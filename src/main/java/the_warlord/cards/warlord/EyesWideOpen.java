package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.ReactionTimePower;

public class EyesWideOpen extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(EyesWideOpen.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;

    private static final int REACTION_TIME = 2;
    private static final int UPGRADE_PLUS_REACTION_TIME = 2;

    public EyesWideOpen() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = REACTION_TIME;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
//        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new ReactionTimePower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_REACTION_TIME);
            upgradeDescription();
        }
    }
}
