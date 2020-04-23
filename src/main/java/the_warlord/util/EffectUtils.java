package the_warlord.util;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

public class EffectUtils {
    public static void addPermanentCardUpgradeEffect(AbstractCard cardToShowForVfx) {
        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cardToShowForVfx.makeStatEquivalentCopy()));
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
    }

    public static void showDowngradeEffect(AbstractCard card) {
        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy()));
        float x = (float) Settings.WIDTH / 2.0F;
        float y = (float) Settings.HEIGHT / 2.0F;
        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_LONG));
    }

    public static void showDestroyEffect(AbstractCard card) {
        CardCrawlGame.sound.play("CARD_EXHAUST");
        PurgeCardEffect purgeCardEffect = new PurgeCardEffect(card, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2));
        float duration = Settings.FAST_MODE ? 0.75F : purgeCardEffect.startingDuration;
        purgeCardEffect.duration = purgeCardEffect.startingDuration = duration;
        AbstractDungeon.topLevelEffects.add(purgeCardEffect);
        AbstractDungeon.actionManager.addToTop(new WaitAction(duration));
    }
}
