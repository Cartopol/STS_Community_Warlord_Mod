package the_warlord.cards.warlord.parry_deck;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import the_warlord.WarlordMod;
import the_warlord.actions.AblutionCleanseDebuffsAction;
import the_warlord.characters.Warlord;
import the_warlord.util.IntentUtils;

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
        this.magicNumber = this.baseMagicNumber = 0;
    }

    @Override
    public void useParry() {
        addToBot(new AblutionCleanseDebuffsAction(AbstractDungeon.player, this.block));
    }

    @Override
    public int calculateBonusMagicNumber() {
        int debuffCleansed = 0;
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if(p.type == AbstractPower.PowerType.DEBUFF){
                ++debuffCleansed;
            }
        }
        return debuffCleansed;
    }

    @Override
    public String getRawDynamicDescriptionSuffix() {
        return magicNumber == 1 ? EXTENDED_DESCRIPTION[0] : magicNumber > 1 ? EXTENDED_DESCRIPTION[1] : "";
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
