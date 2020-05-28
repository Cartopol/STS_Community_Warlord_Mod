package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.powers.PosturePower;

public class Dizzy extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Dizzy.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = COST_UNPLAYABLE;
    private static final int POSTURE_REDUCTION = 3;

    public Dizzy() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = baseMagicNumber = POSTURE_REDUCTION;
        this.isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn(){
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(PosturePower.POWER_ID)) {
            addToBot(new ReducePowerAction(p, p, PosturePower.POWER_ID, magicNumber));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
    }
}