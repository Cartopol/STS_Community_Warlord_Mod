package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;

public class WardOff extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(WardOff.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int BLOCK_PER_POSTURE = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;


    public WardOff() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = 0;
        magicNumber = baseMagicNumber = BLOCK_PER_POSTURE;
    }

    @Override
    public int calculateBonusBaseBlock() {
        AbstractPlayer p = AbstractDungeon.player;
        int bonusBlock = 0;
        if (p.hasPower(PosturePower.POWER_ID)) {
            bonusBlock = p.getPower(PosturePower.POWER_ID).amount * magicNumber;
        }
        return bonusBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(PosturePower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(p, p, PosturePower.POWER_ID));
        }
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public String getRawDynamicDescriptionSuffix(){
        if (block > 0) {
            return EXTENDED_DESCRIPTION[0];
        } else return "";
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BLOCK);
            upgradeDescription();
        }
    }
}
