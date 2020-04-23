package the_warlord.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import the_warlord.cards.CustomWarlordModCard;

public class LoadMiscValuePatch {
    @SpirePatch(clz = CardLibrary.class, method = "getCopy", paramtypez = {String.class, int.class, int.class})
    public static class CardLibrary_getCopyPatch {
        @SpirePostfixPatch
        public static AbstractCard patch(AbstractCard __result, String key, int upgradeTime, int misc) {
            if(misc != 0 && __result instanceof CustomWarlordModCard) {
                ((CustomWarlordModCard) __result).applyLoadedMiscValue(misc);
            }
            return __result;
        }
    }
}
