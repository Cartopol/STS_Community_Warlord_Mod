package the_warlord.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static the_warlord.WarlordMod.makeID;

// For use with cards like "Gain !B! Block (!the_warlord:BaseBlock! + !M! block per something)".
// Should generally only be used *in addition to* !B!, not *instead of* !B!.
public class BaseBlockNumber extends DynamicVariable {
    // Reference as "!the_warlord:BaseBlock!" in card strings to actually display the number.
    @Override
    public String key() {
        return makeID("BaseBlock");
    }

    @Override
    public boolean isModified(AbstractCard card) { return false; }

    @Override
    public int value(AbstractCard card) {
        return card.baseBlock;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return card.baseBlock;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
