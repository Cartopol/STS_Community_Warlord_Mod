package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.BloodMagicPower;

public class BloodMagic extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(BloodMagic.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    private static final int BLEED = 4;
    private static final int UPGRADE_PLUS_BLEED = 1;
    private static final int BLOCK_PER_BLEED = 1;


    public BloodMagic() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLOCK_PER_BLEED;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BloodMagicPower(p, this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
//            upgradeMagicNumber(UPGRADE_PLUS_BLEED);
            upgradeDescription();
        }
    }
}