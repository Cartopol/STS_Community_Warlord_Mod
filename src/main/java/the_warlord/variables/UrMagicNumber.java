package the_warlord.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_warlord.cards.CustomWarlordModCard;

import static the_warlord.WarlordMod.makeID;

// A second "magic" number for highlighting and display on cards, with possibly different upgraded values.
public class UrMagicNumber extends DynamicVariable {

    // Reference as "!the_warlord:UrMagic!" in card strings to actually display the number.
    @Override
    public String key() {
        return makeID("UrMagic");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((CustomWarlordModCard) card).isUrMagicNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((CustomWarlordModCard) card).urMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((CustomWarlordModCard) card).baseUrMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((CustomWarlordModCard) card).upgradedUrMagicNumber;
    }
}
