package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.AddToParryDeckAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.cards.warlord.parry_deck.HighReward;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class HighRisk extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(HighRisk.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int TENSION = 5;

    public HighRisk() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        magicNumber = baseMagicNumber = TENSION;
        AbstractCard c = new HighReward();
        if (upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TensionPower(p, this.magicNumber)));
        AbstractCard c = new HighReward();
        if (upgraded) {
            c.upgrade();
        }
        addToBot(new AddToParryDeckAction(c, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}