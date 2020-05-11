package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;

public class Stretch extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Stretch.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int POSTURE = 3;
    private static final int UPGRADE_PLUS_REACTION_TIME = 2;


    public Stretch() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POSTURE;

    }

    @Override
    public void onChoseThisOption() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PosturePower(AbstractDungeon.player, magicNumber)));
        CustomParryCard.removeFromMasterParryDeck(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_REACTION_TIME);

            upgradeName();
            upgradeDescription();
        }
    }
}
