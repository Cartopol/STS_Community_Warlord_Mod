package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;
import the_warlord.powers.TensionPower;

public class Diversion extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Diversion.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;

    private static final int POSTURE = 1;


    public Diversion() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POSTURE;
        this.exhaust = true;
        cardsToPreview = new Dizzy();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int currentPosture = 0;
        int currentTension = 0;
        if (p.hasPower(PosturePower.POWER_ID)) {
            currentPosture += p.getPower(PosturePower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, PosturePower.POWER_ID));
        }
        if (p.hasPower(TensionPower.POWER_ID)) {
            currentTension += p.getPower(TensionPower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, TensionPower.POWER_ID));
        }
        if (currentPosture > 0) {
            addToBot(new ApplyPowerAction(p, p, new TensionPower(p, currentPosture)));
        }
        if (currentTension > 0) {
            addToBot(new ApplyPowerAction(p, p, new PosturePower(p, currentTension)));
        }
        addToBot(new MakeTempCardInDrawPileAction(new Dizzy(), 1, true, true));

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.exhaust = false;
            upgradeName();
            upgradeDescription();
        }
    }
}
