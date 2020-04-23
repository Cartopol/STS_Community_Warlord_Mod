package the_warlord.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import the_warlord.WarlordMod;
import the_warlord.util.TextureLoader;

public abstract class CustomWarlordModRelic extends CustomRelic {
    public final CardColor relicColor;

    private static Texture relicTextureFromId(String id) {
        String unprefixedId = id.replace(WarlordMod.MOD_ID + ":","");
        String path = String.format("%1$sResources/images/relics/%2$s.png", WarlordMod.MOD_ID, unprefixedId);
        return TextureLoader.getTexture(path);
    }

    private static Texture relicOutlineTextureFromId(String id) {
        String unprefixedId = id.replace(WarlordMod.MOD_ID + ":","");
        String path = String.format("%1$sResources/images/relics/generated/%2$s_outline.png", WarlordMod.MOD_ID, unprefixedId);
        return TextureLoader.getTexture(path);
    }

    public CustomWarlordModRelic(final String id, final CardColor relicColor, final RelicTier relicTier, final LandingSound landingSound) {
        super(id, relicTextureFromId(id), relicOutlineTextureFromId(id), relicTier, landingSound);
        this.relicColor = relicColor;
    }


    @Override
    public void initializeTips() {
        this.description = DESCRIPTIONS[0];
        super.initializeTips();
        this.description = getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0].replaceAll(WarlordMod.MOD_ID + ":", "#y");
    }
}