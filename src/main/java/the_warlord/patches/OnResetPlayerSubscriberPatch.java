package the_warlord.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.characters.OnResetPlayerSubscriber;

public class OnResetPlayerSubscriberPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "resetPlayer"
    )
    public static class AbstractDungeon_resetPlayer
    {
        @SpirePostfixPatch
        public static void patch() {
            if (AbstractDungeon.player instanceof OnResetPlayerSubscriber) {
                ((OnResetPlayerSubscriber) AbstractDungeon.player).onResetPlayer();
            }
        }
    }
}