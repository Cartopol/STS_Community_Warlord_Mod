package the_warlord.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.characters.Warlord;

public class RemoveUnwantedBaseRelicsPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
    public static class Implementation
    {
        @SpirePrefixPatch
        public static void patch(AbstractDungeon __instance) {
            boolean warlord = __instance.player instanceof Warlord;

            if (warlord) {
                //use this to remove specific relics from pool if player character is Warlord
//                AbstractDungeon.relicsToRemoveOnStart.add(PrismaticShard.ID);
            }

        }
    }
}