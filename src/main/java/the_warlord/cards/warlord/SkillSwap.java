package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.ClearAllDebuffsAction;
import the_warlord.actions.ThornsToStrengthAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;


public class SkillSwap extends CustomWarlordModCard {

    public static final String ID = WarlordMod.makeID(SkillSwap.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int STRENGTH_PER_THORNS = 1;
    private static final int UPGRADE_PLUS_STRENGTH_PER_THORNS = 1;


    public SkillSwap() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH_PER_THORNS;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ThornsToStrengthAction(p, upgraded));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STRENGTH_PER_THORNS);
            upgradeDescription();
        }
    }
}