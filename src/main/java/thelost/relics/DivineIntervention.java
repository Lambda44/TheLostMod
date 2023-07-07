package thelost.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import thelost.character.TheLost;
import thelost.powers.EvilPower;

import static thelost.BasicMod.makeID;

public class DivineIntervention extends BaseRelic{
    private static final String NAME = "DivineIntervention";
    public static final String ID = makeID(NAME);

    public DivineIntervention() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 40;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 40 + DESCRIPTIONS[1];
    }

    public void onEquip() {
        this.counter = 40;
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof EventRoom) {
            this.flash();
            this.pulse = true;
            this.grayscale = false;
        } else {
            this.pulse = false;
        }
    }

    public int onLoseHpLast(int damageAmount) {
        boolean hmActive = false;
        if (AbstractDungeon.player.relics.contains(HolyMantle.ID))
        {
            int pos = AbstractDungeon.player.relics.indexOf(HolyMantle.ID);
            int hmCount = AbstractDungeon.player.relics.get(pos).counter;
            if (hmCount == -1) { hmActive = true; }
        }

        if (damageAmount > 0 && this.counter > 0 && hmActive == false) {
            this.flash();
            this.counter -= damageAmount;
            return 0;
        } else if(hmActive == true) {
            return 0;
        } else {
            //this.grayscale = true;
            return damageAmount;
        }
    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 45;
    }

    public void setCounter(int counter) {
        super.setCounter(counter);
    }
}
