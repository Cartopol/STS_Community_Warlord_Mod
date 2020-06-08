package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;
import the_warlord.powers.TensionPerTurnPower;

public class HeadStart extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(HeadStart.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;
//    private static final int DRAW = 3;
//    private static final int POSTURE = 1;
//    private static final int UPGRADE_PLUS_POSTURE = 2;
    private static final int POSTURE = 5;
    private static final int UPGRADE_PLUS_POSTURE = 2;
    private static final int TENSION_PT = 1;


    public HeadStart() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
//        magicNumber = baseMagicNumber = DRAW;
//        urMagicNumber = baseUrMagicNumber = POSTURE;
        urMagicNumber = baseUrMagicNumber = POSTURE;
        magicNumber = baseMagicNumber = TENSION_PT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new DrawCardAction(magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PosturePower(p, urMagicNumber), urMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TensionPerTurnPower(p, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
//            upgradeUrMagicNumber(UPGRADE_PLUS_POSTURE);
            upgradeUrMagicNumber(UPGRADE_PLUS_POSTURE);
            upgradeDescription();
        }
    }
}