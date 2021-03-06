package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.ApplyBurnAtTurnStartOncePower;

public class FlamesFromBeyond extends AbstractHexaCard {

    public final static String ID = makeID("FlamesFromBeyond");

    //stupid intellij stuff SKILL, NONE, COMMON

    private static final int MAGIC = 12;
    private static final int UPG_MAGIC = 6;

    public FlamesFromBeyond() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBurn = burn = MAGIC;
        isEthereal = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        applyToSelf(new ApplyBurnAtTurnStartOncePower(burn));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(UPG_MAGIC);
            upgradeBurn(UPG_MAGIC);
        }
    }
}