package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.TensionPower;

public class Safeguard extends CustomWarlordModCard {

    public static final String ID = WarlordMod.makeID(Safeguard.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 21;
    private static final int UPGRADE_PLUS_BLOCK = 4;


    public Safeguard() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        if (p.hasPower(TensionPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(p, p, TensionPower.POWER_ID));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeName();
            upgradeDescription();
        }
    }
}