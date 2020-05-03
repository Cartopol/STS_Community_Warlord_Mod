package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;
import the_warlord.powers.BleedPower;
import the_warlord.powers.GushPower;

public class XSlice extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(XSlice.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int BLEED = 3;
    private static final int GUSH = 1;

    private static final int UPGRADE_PLUS_BLEED = 2;


    public XSlice() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLEED;
        urMagicNumber = baseUrMagicNumber = GUSH;
    }

    @Override
    public void onChoseThisOption() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GushPower(m, urMagicNumber)));
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new BleedPower(m, magicNumber)));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_BLEED);

            upgradeName();
            upgradeDescription();
        }
    }
}
