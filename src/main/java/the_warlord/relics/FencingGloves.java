package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.Syrette;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class FencingGloves extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(FencingGloves.class);
    public FencingGloves() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }
}