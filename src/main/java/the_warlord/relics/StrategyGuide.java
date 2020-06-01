package the_warlord.relics;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.parry_deck.CustomParryCard;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class StrategyGuide extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(StrategyGuide.class);
    private boolean pickCard;

    public StrategyGuide() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {

        this.pickCard = true;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard card : BaseMod.getCustomCardsToAdd()) {
            if (card instanceof CustomParryCard && !card.rarity.equals(AbstractCard.CardRarity.SPECIAL)) {
                group.addToBottom(card);
            }
            AbstractDungeon.gridSelectScreen.open(group, 1, "Choose a Parry card", false);
        }
    }

    @Override
    public void update() {
        super.update();
        if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeCopy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }
}
