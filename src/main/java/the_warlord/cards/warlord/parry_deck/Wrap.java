package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class Wrap extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Wrap.class);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int PLATED_ARMOR = 3;
    private static final int UPGRADE_PLUS_PLATED_ARMOR = 1;

    public Wrap() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = PLATED_ARMOR;

    }

    @Override
    public void useParry() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_PLATED_ARMOR);

            upgradeName();
            upgradeDescription();
        }
    }
}
