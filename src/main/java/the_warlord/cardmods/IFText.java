package the_warlord.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_warlord.cards.warlord.parry_deck.InvertForce;

import static the_warlord.WarlordMod.makeID;

public class IFText extends AbstractCardModifier {
    private String desc;
    private static String ID = makeID(IFText.class.getSimpleName());
    public IFText(String desc) { this.desc = desc; }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) { return rawDescription + this.desc; }
    @Override
    public AbstractCardModifier makeCopy() {
        return new IFText(desc);
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof InvertForce){
            try {
                ((InvertForce) card).calculateBonusMagicNumber();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static String fetchID() { return ID; }
    public String identifier(AbstractCard card) {
        return ID;
    }
}