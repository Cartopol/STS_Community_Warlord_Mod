package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class Diversion extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Diversion.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int BONUS_BLOCK = 3;
    private static final int UPGRADE_PLUS_BONUS_BLOCK = 1;

    public Diversion() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = BONUS_BLOCK;
    }

    @Override
    public int calculateBonusMagicNumber() {
        return calculateMagicNumberAfterMods(magicNumber) - magicNumber;
    }

    private int calculateMagicNumberAfterMods(int block) {
        int bonusBlock = block;

        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(DexterityPower.POWER_ID)) {
            bonusBlock += p.getPower(DexterityPower.POWER_ID).amount;
        }
        if (p.hasPower(FrailPower.POWER_ID)) {
            bonusBlock *= 0.75F;
        }

        return bonusBlock;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_BONUS_BLOCK);
            upgradeDescription();
        }
    }
}
