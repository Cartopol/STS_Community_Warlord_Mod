package the_warlord.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import the_warlord.WarlordMod;
import the_warlord.powers.PosturePower;

public class PosturePotion extends AbstractPotion {
    public static final String POTION_ID = WarlordMod.makeID(PosturePotion.class);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private static final int BASE_POTENCY = 5;

    public PosturePotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOLT, PotionColor.BLUE);
        this.isThrown = false;
        this.targetRequired = false;
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = String.format(DESCRIPTIONS[0], this.potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle("the_warlord:posture"), BaseMod.getKeywordDescription("the_warlord:posture")));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new PosturePower(p, potency)));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PosturePotion();
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return BASE_POTENCY;
    }
}
