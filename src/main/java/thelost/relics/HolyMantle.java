package thelost.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import thelost.character.TheLost;

import static thelost.BasicMod.makeID;

public class HolyMantle extends BaseRelic{
    private static final String NAME = "HolyMantle";
    public static final String ID = makeID(NAME);

    public HolyMantle() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.counter = -1;

    }

    public void atBattleStart() { //gain 1 Buffer on combat start
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BufferPower(AbstractDungeon.player, 1), 1));
        this.counter = -2;
        this.pulse = false;
        this.grayscale = true;
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof EventRoom) {
            this.flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }
        this.grayscale = false;
        this.counter = -1;
    }

    public int onLoseHpLast(int damageAmount) {
        if (damageAmount > 0 && this.counter == -1) {
            this.flash();
            this.counter = -2;
            this.grayscale = true;
            return 0;
        } else {
            return damageAmount;
        }
    }

    public void setCounter(int counter) {
        super.setCounter(counter);
    }
}
