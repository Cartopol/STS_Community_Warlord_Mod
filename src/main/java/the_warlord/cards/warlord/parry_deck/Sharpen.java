package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class Sharpen extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Sharpen.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int STRENGTH = 1;
    private static final int UPGRADE_PLUS_STRENGTH = 1;


    public Sharpen() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH;

    }

    @Override
    public void onChoseThisOption() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_STRENGTH);
            upgradeName();
            upgradeDescription();
        }
    }
}