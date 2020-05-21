package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.powers.PosturePower;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class FeatherBoots extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(FeatherBoots.class);
    private static final int REACTION_TIME = 5;

    public FeatherBoots() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new PosturePower(p, REACTION_TIME)));
        this.flash();
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(FencingBoots.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(FencingBoots.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else { super.obtain(); }
    }
    public boolean canSpawn() { return AbstractDungeon.player.hasRelic(FencingBoots.ID); }
}
