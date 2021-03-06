package the_warlord.cards.warlord.parry_deck;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

@AutoAdd.Ignore

public class PrickHoles extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(PrickHoles.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int THORNS = 3;
    private static final int UPGRADE_PLUS_THORNS = 2;

    public PrickHoles() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = THORNS;

    }

    @Override
    public void useParry() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, magicNumber)));
        removeFromMasterParryDeck(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_THORNS);
            upgradeName();
            upgradeDescription();
        }
    }
}