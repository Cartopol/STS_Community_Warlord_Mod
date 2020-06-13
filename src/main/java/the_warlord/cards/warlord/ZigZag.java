package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class ZigZag extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(ZigZag.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int BLOCK_PER_TENSION = 3;
    private static final int UPGRADE_PLUS_BLOCK_PER_TENSION = 1;


    public ZigZag() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = BLOCK_PER_TENSION;
    }

    @Override
    public int calculateBonusBaseBlock() {
        AbstractPlayer p = AbstractDungeon.player;
        int bonusBlock = 0;
        if (p.hasPower(TensionPower.POWER_ID)) {
            bonusBlock = p.getPower(TensionPower.POWER_ID).amount * magicNumber;
        }
        return bonusBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_BLOCK_PER_TENSION);
            upgradeDescription();
        }
    }
}
