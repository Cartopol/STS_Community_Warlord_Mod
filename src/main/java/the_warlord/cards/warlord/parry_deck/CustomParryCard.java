package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.cards.CustomWarlordModCard;

public abstract class CustomParryCard extends CustomWarlordModCard {

    public CustomParryCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, cost, type, color, rarity, target);
    }

    @Override
    public final void use(AbstractPlayer p, AbstractMonster m) {
        ParryDeck.playedThisCombatCount++;
        onChoseThisOption();
    }
}
