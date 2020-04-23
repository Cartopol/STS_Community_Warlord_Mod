package the_warlord.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import the_warlord.characters.OnAfterPlayerHpLossSubscriber;

public class OnAfterPlayerHpLossSubscriberPatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class AbstractPlayer_damage
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "damageAmount" }
        )
        public static void patch(AbstractPlayer __this, DamageInfo info, int damageAmount)
        {
            if (__this instanceof OnAfterPlayerHpLossSubscriber) {
                if (damageAmount > 0) {
                    ((OnAfterPlayerHpLossSubscriber)__this).onAfterPlayerHpLoss(damageAmount);
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "damageReceivedThisTurn");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}