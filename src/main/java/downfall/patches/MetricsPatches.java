package downfall.patches;

import champ.ChampChar;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import guardian.patches.GuardianEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.patches.SlimeboundEnum;
import theHexaghost.TheHexaghost;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* Copied from The Mystic Mod:
   https://github.com/JohnnyDevo/The-Mystic-Project/blob/master/src/main/java/mysticmod/patches/MysticMetricsPatch.java
 */
public class MetricsPatches {

    private static final Logger logger = LogManager.getLogger(MetricsPatches.class);

    @SpirePatch(clz = Metrics.class, method = "sendPost", paramtypez = {String.class, String.class})
    public static class SendPostPatch {

        public static void Prefix(Metrics metrics, @ByRef String[] url, String fileName) {
            if (AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP || AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND || AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN || AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
                url[0] = "http://downfallstats.atwebpages.com/";
            }
        }

    }

    @SpirePatch(clz = GameOverScreen.class, method = "shouldUploadMetricData")
    public static class ShouldUploadMetricData {

        public static boolean Postfix(boolean returnValue) {
            if (AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP || AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND || AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN || AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
                returnValue = Settings.UPLOAD_DATA;
            }
            return returnValue;
        }

    }

    @SpirePatch(clz = Metrics.class, method = "run")
    public static class RunPatch {

        public static void Postfix(Metrics metrics) {
            if (metrics.type == Metrics.MetricRequestType.UPLOAD_METRICS && (AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP || AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND || AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN || AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT)) {
                try {
                    Method m = Metrics.class.getDeclaredMethod("gatherAllDataAndSend", boolean.class, boolean.class, MonsterGroup.class);
                    m.setAccessible(true);
                    m.invoke(metrics, metrics.death, metrics.trueVictory, metrics.monsters);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    logger.error("Exception while sending metrics", e);
                }
            }
        }

    }
}