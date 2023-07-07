package thelost.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thelost.character.TheLost;
import thelost.powers.DeliriousPower;

import java.util.Iterator;

import static thelost.BasicMod.makeID;

public class EyeOfTheOccult extends BaseRelic{
    private static final String NAME = "EyeOfTheOccult";
    public static final String ID = makeID(NAME);

    public EyeOfTheOccult() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, false), 1));
    }

    public void atBattleStart() {
        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            this.addToTop(new RelicAboveCreatureAction(m, this));
            m.addPower(new DeliriousPower(m, -1));
        }

        AbstractDungeon.onModifyPower();
    }

    public void onSpawnMonster(AbstractMonster monster) {
        monster.addPower(new DeliriousPower(monster, -1));
        AbstractDungeon.onModifyPower();
    }

}
