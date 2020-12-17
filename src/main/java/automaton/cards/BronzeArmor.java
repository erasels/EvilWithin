package automaton.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class BronzeArmor extends AbstractBronzeCard {

    public final static String ID = makeID("BronzeArmor");

    //stupid intellij stuff skill, self, uncommon

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public BronzeArmor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        thisEncodes();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ArtifactPower(p, 1));
    }

    @Override
    public void onCompile(AbstractCard function, boolean forGameplay) {
        super.onCompile(function, forGameplay);
        if (forGameplay) {
            for (AbstractMonster q : monsterList()) {
                applyToEnemy(q, new ArtifactPower(q, magicNumber));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}