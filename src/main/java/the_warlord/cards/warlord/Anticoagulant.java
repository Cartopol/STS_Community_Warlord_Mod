package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.DoubleBleedAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class Anticoagulant extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Anticoagulant.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;

    public Anticoagulant() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){ addToBot(new DoubleBleedAction(m, p, true)); }
        else { addToBot(new DoubleBleedAction(m, p, false)); }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}