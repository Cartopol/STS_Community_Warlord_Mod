package the_warlord.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import the_warlord.cards.CustomWarlordModCard;

public class CardBannerImageRarityPatch {
    public static AbstractCard.CardRarity getBannerRarity(AbstractCard c) {
        return (c instanceof CustomWarlordModCard) ? ((CustomWarlordModCard)c).bannerImageRarity : c.rarity;
    }

    public static class ReplaceRarityWithBannerRarityExprEditor extends ExprEditor {
        @Override
        public void edit(FieldAccess fieldAccess) throws CannotCompileException {
            if (fieldAccess.getClassName().equals(AbstractCard.class.getName()) && fieldAccess.getFieldName().equals("rarity")) {
                fieldAccess.replace(String.format("{ $_ = %1$s.getBannerRarity($0); }", CardBannerImageRarityPatch.class.getName()));
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderBannerImage"
    )
    public static class AbstractCard_renderBannerImage {
        public static ExprEditor Instrument() {
            return new ReplaceRarityWithBannerRarityExprEditor();
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderCardBanner"
    )
    public static class SingleCardViewPopup_renderCardBanner {
        public static ExprEditor Instrument() {
            return new ReplaceRarityWithBannerRarityExprEditor();
        }
    }
}