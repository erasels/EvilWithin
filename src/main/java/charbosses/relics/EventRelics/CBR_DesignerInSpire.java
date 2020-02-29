package charbosses.relics.EventRelics;

import charbosses.bosses.AbstractBossDeckArchetype;
import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import charbosses.cards.colorless.EnBite;
import charbosses.relics.AbstractCharbossRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import evilWithin.EvilWithinMod;

import java.util.ArrayList;


public class CBR_DesignerInSpire extends AbstractCharbossRelic {
    public static String ID = EvilWithinMod.makeID("DesignerInSpire");
    private static RelicTier tier = RelicTier.SPECIAL;
    private static LandingSound sound = LandingSound.MAGICAL;

    public CBR_DesignerInSpire() {
        super(ID, tier, sound, new Texture(EvilWithinMod.assetPath("images/relics/designerinspire.png")));
    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> list, int actIndex) {
        AbstractCharBoss.boss.chosenArchetype.removeBasicCard("Designer in Spire");
        AbstractCharBoss.boss.chosenArchetype.upgradeRandomCard("Designer in Spire");
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_DesignerInSpire();
    }
}
