package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.EndlessNextTurnReactionTimePower;
import the_warlord.powers.EndlessNextTurnSyrettePower;

public class Extremespeed extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Extremespeed.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int REACTION_TIME = 1;

    public Extremespeed() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = REACTION_TIME;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EndlessNextTurnReactionTimePower(p, this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBaseCost(UPGRADED_COST);
            upgradeName();
            upgradeDescription();
        }
    }
}