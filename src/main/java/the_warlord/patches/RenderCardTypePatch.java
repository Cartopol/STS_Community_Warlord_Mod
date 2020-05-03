package the_warlord.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import the_warlord.cards.warlord.parry_deck.CustomParryCard;

import java.util.ArrayList;

@SpirePatch(clz = AbstractCard.class, method = "renderType")
public class RenderCardTypePatch {
    @SpireInsertPatch(localvars = {"text"}, locator = Locator.class)
    public static void Insert(AbstractCard __instance, SpriteBatch sb, @ByRef String[] text) {
        boolean isParryCard = __instance instanceof CustomParryCard;
        if (isParryCard) {
            text[0] = "Parry";
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderRotatedText");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher);
        }
    }
}
