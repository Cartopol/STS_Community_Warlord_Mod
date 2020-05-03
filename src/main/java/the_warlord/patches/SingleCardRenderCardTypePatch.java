package the_warlord.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import the_warlord.cards.warlord.parry_deck.CustomParryCard;

import java.util.ArrayList;

@SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
public class SingleCardRenderCardTypePatch {
    @SpireInsertPatch(localvars = {"label"}, locator = Locator.class)
    public static void Insert(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef String[] label) {
        AbstractCard reflectedCard = (AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
        boolean isParryCard = reflectedCard instanceof CustomParryCard;
        if (isParryCard) {
            label[0] = "Parry";
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher);
        }
    }
}
