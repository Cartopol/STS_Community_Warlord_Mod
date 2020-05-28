package the_warlord.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import the_warlord.WarlordMod;
import the_warlord.powers.GushPower;

public class GushPotion extends AbstractPotion {
    public static final String POTION_ID = WarlordMod.makeID(GushPotion.class);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private static final int BASE_POTENCY = 2;

    public GushPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPIKY, PotionColor.FIRE);
        this.isThrown = true;
        this.targetRequired = true;
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = String.format(DESCRIPTIONS[0], this.potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle("the_warlord:gush"), BaseMod.getKeywordDescription("the_warlord:gush")));
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(target, target, new GushPower(target, potency)));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new GushPotion();
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return BASE_POTENCY;
    }
}
