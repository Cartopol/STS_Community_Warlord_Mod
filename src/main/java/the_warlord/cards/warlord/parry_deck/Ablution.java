package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import the_warlord.WarlordMod;
import the_warlord.actions.AblutionCleanseDebuffsAction;
import the_warlord.characters.Warlord;

public class Ablution extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Ablution.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 1;


    public Ablution() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
    }

    @Override
    public void useParry() {
        addToBot(new AblutionCleanseDebuffsAction(AbstractDungeon.player, this.block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeDescription();
        }
    }
}
